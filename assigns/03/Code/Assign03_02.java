/*
HX-2026-02-05: 10 points
*/
import Library00.FnList.FnList;
import static Library00.FnList.FnListSUtil.*;

public class Assign03_02 {
    static boolean balencedq(String text) {
	//
	// There are only '(', ')', '[', ']', '{', and '}'p
	// appearing in [text]. This method should return
	// true if and only if the parentheses/brackets/braces
	// in [text] are balenced.
	// Your solution must make proper use of FnList (as a stack)!
	//
	FnList<Character> stack = nil();
	for(int i=0;i<text.length();i++){
		char c = text.charAt(i);
		if (c == '(' || c == '[' || c == '{'){
			stack = cons(c, stack);
		}
		else if (c == ')' || c == ']' || c == '}'){
			if (stack.nilq()) {
				return false;
			}
			char top = stack.hd();
			if ((c == ')' && top == '(') ||
                    (c == ']' && top == '[') ||
                    (c == '}' && top == '{')) {
                    stack = stack.tl();
                } else {
                    return false;
                }

		}
	}
    return stack.nilq();
}
    public static void main(String[] argv) {
		// Please write some testing code for your 'balencedq"
	String[] tests = {
		"()",          // true
		"({[]})",      // true
		"()[]{}",      // true
		"(]",          // false
		"([)]",        // false
		"((())",       // false
		"()}"          // false
	};

	for (String s : tests) {
		System.out.println("Text: " + s + " -> Balanced: " + balencedq(s));
	}

    }
}
