import java.util.HashMap;
import java.util.Map;

public class Quiz01_02 {
    public static boolean solve_3prod(Integer[] A) {
	// Please give a soft quadratic time implementation
	// that solves the 3-prod problem. The function call
	// solve_3prod(A) returns true if and only if there exist
	// distinct indices i, j, and k satisfying A[i]*A[j] = A[k].
	// Why is your implementation soft O(n^2)? Please give a
	// BRIEF explanation
	//
	// Brief complexity argument:
	// - We build a hash-table of value -> frequency in O(n) time/space.
	// - We scan all (i,j) pairs: O(n^2) products, and each hash lookup is
	//   O(1) expected. Thus the total is soft O(n^2).
	//
	final int n = A.length;
	final Map<Integer, Integer> counts = new HashMap<Integer, Integer>(n * 2);
	for (int i = 0; i < n; i += 1) {
	    final Integer v = A[i];
	    counts.put(v, counts.getOrDefault(v, 0) + 1);
	}
	for (int i = 0; i < n; i += 1) {
	    for (int j = i + 1; j < n; j += 1) {
		final long prodL = (long) A[i] * (long) A[j];
		if (prodL < Integer.MIN_VALUE || prodL > Integer.MAX_VALUE) {
		    continue; // cannot match any Integer in A
		}
		final Integer prod = (int) prodL;
		Integer freq = counts.get(prod);
		if (freq == null || freq <= 0) continue;

		int avail = freq.intValue();
		if (prod.equals(A[i])) avail -= 1;
		if (prod.equals(A[j])) avail -= 1;
		if (avail > 0) return true;
	    }
	}
	return false;
    }

    private static void assertEq(boolean actual, boolean expected, String msg) {
	if (actual != expected) {
	    throw new RuntimeException(msg + ": expected " + expected + ", got " + actual);
	}
    }

    public static void main(String[] argv) {
	// Please write some code here for testing solve_3prod
	assertEq(solve_3prod(new Integer[] {}), false, "empty");
	assertEq(solve_3prod(new Integer[] { 1 }), false, "single");
	assertEq(solve_3prod(new Integer[] { 2, 3 }), false, "two");
	assertEq(solve_3prod(new Integer[] { 2, 3, 6 }), true, "2*3=6");
	assertEq(solve_3prod(new Integer[] { 2, 2, 4 }), true, "2*2=4");
	assertEq(solve_3prod(new Integer[] { 2, 2, 2, 4 }), true, "dup handling");
	assertEq(solve_3prod(new Integer[] { -2, 3, -6 }), true, "-2*3=-6");
	assertEq(solve_3prod(new Integer[] { 0, 5, 0 }), true, "0*5=0 with k distinct");
	assertEq(solve_3prod(new Integer[] { 0, 5 }), false, "missing k");
	System.out.println("Quiz01_02: solve_3prod tests passed.");
    }
}
