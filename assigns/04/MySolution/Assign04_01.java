/*
HX-2026-02-13: 10 points
*/
import MyLibrary.FnList.*;
import MyLibrary.FnStrn.*;

import java.util.concurrent.atomic.AtomicReference;

public class Assign04_01 {
    static boolean balencedq(String text) {
	//
	// There are only '(', ')', '[', ']', '{', and '}'
	// appearing in [text]. This method should return
	// true if and only if the parentheses/brackets/braces
	// in [text] are balenced.
	// Your solution must make proper use of FnList (as a stack)!
	//
	FnStrn cs = new FnStrn(text);
	AtomicReference<FnList<Character>> stackRef =
	    new AtomicReference<>(FnListSUtil.nil());
	boolean ok =
	    FnStrnSUtil.forall(cs, ch0 -> {
		char ch = ch0.charValue();
		FnList<Character> stack = stackRef.get();
		if (ch == '(' || ch == '[' || ch == '{') {
		    stackRef.set(FnListSUtil.cons(ch, stack));
		    return true;
		}
		if (ch == ')' || ch == ']' || ch == '}') {
		    if (stack.nilq()) return false;
		    char top = stack.hd();
		    boolean match =
			(ch == ')' && top == '(') ||
			(ch == ']' && top == '[') ||
			(ch == '}' && top == '{');
		    if (!match) return false;
		    stackRef.set(stack.tl());
		    return true;
		}
		return false;
	    });
	return ok && stackRef.get().nilq();
    }

    public static void main(String[] argv) {
	// Please write some testing code for your 'balencedq"
	FnList<String> tests =
	    FnListSUtil.cons("()",
		FnListSUtil.cons("({[]})",
		    FnListSUtil.cons("()[]{}",
			FnListSUtil.cons("(]",
			    FnListSUtil.cons("([)]",
				FnListSUtil.cons("((())",
				    FnListSUtil.cons("(){",
					FnListSUtil.cons("", FnListSUtil.nil())
				    )
				)
			    )
			)
		    )
		)
	    );
	FnListSUtil.foritm(tests,
	    s -> System.out.println("Text: " + s + " -> Balanced: " + balencedq(s))
	);
    }
} // end of [public class Assign04_01{...}]

