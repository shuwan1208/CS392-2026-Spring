/*
HX-2026-02-13: 20 points
*/
import MyLibrary.FnList.*;
import MyLibrary.FnStrn.*;

public class Assign04_02 {
    static FnStrn
	FnList$FnStrn_concate(FnList<FnStrn> xs) {
	// Given a list of strings, this method return the
	// concatenation of these string. For instance, given
	// ("a", "bc", "def"), the returned string is "abcdef"
	// You implementation is NOT allowed to use loops or
	// reccursion. Try to use the 'foritm' method in FnList
	// and FnStrn to accomplish this task.
	final int[] totalLen = new int[] { 0 };
	FnListSUtil.foritm(xs, s -> totalLen[0] += FnStrnSUtil.length(s));

	final char[] out = new char[totalLen[0]];
	final int[] offset = new int[] { 0 };
	FnListSUtil.foritm(xs, s -> {
	    int base = offset[0];
	    FnStrnSUtil.iforitm(s, (i, ch) -> out[base + i] = ch.charValue());
	    offset[0] = base + FnStrnSUtil.length(s);
	});
	return new FnStrn(out);
    }

    static String FnStrn_toString(FnStrn cs) {
	StringBuilder sb = new StringBuilder();
	FnStrnSUtil.foritm(cs, ch -> sb.append(ch.charValue()));
	return sb.toString();
    }

    public static void main(String[] argv) {
	// Please write some testing code for your 'FnList$FnStrn_concate"
	FnList<FnStrn> xs =
	    FnListSUtil.cons(new FnStrn("a"),
		FnListSUtil.cons(new FnStrn("bc"),
		    FnListSUtil.cons(new FnStrn("def"), FnListSUtil.nil())
		)
	    );
	FnStrn res = FnList$FnStrn_concate(xs);
	System.out.println("concat = " + FnStrn_toString(res));
    }
} // end of [public class Assign04_02{...}]

