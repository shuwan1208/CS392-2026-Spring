//
// HX: 20 points
//
/*
import Library00.FnA1sz.*;
*/
import MyLibrary.FnA1sz.*;
public class Quiz01_01 {
    public static
	<T extends Comparable<T>>
	int FnA1szBinarySearch(FnA1sz<T> A, T key) {
	// HX-2026-03-03:
	// Please implement binary search on a sorted functional array (FnA1sz)
	// that returns the largest index i such that key >= A[i] if such i exists,
	// or the method returns -1. The comparison function should be the compareTo
	// method implemented by the class T.
	final int n = A.length();
	int lo = 0;
	int hi = n - 1;
	int ans = -1;
	while (lo <= hi) {
	    final int mid = lo + ((hi - lo) >>> 1);
	    final T v = A.getAt(mid);
	    // key >= v  <=>  v.compareTo(key) <= 0
	    if (v.compareTo(key) <= 0) {
		ans = mid;
		lo = mid + 1;
	    } else {
		hi = mid - 1;
	    }
	}
	return ans;
    }

    private static void assertEq(int actual, int expected, String msg) {
	if (actual != expected) {
	    throw new RuntimeException(msg + ": expected " + expected + ", got " + actual);
	}
    }

    public static void main (String[] args) {
	// HX-2026-03-04:
	// Please write minimal testing code for FnA1szBinarySearch
	// Should test for cases T = Integer and T = String
	{
	    FnA1sz<Integer> A = new FnA1sz<Integer>(new Integer[] { 1, 3, 3, 7, 9 });
	    assertEq(FnA1szBinarySearch(A, 0), -1, "Int: key smaller than min");
	    assertEq(FnA1szBinarySearch(A, 1), 0, "Int: key equals min");
	    assertEq(FnA1szBinarySearch(A, 2), 0, "Int: key between 1 and 3");
	    assertEq(FnA1szBinarySearch(A, 3), 2, "Int: key equals dup 3");
	    assertEq(FnA1szBinarySearch(A, 8), 3, "Int: key between 7 and 9");
	    assertEq(FnA1szBinarySearch(A, 100), 4, "Int: key larger than max");
	}
	{
	    FnA1sz<String> A = new FnA1sz<String>(new String[] { "a", "b", "b", "d" });
	    assertEq(FnA1szBinarySearch(A, "A"), -1, "Str: key smaller than min");
	    assertEq(FnA1szBinarySearch(A, "a"), 0, "Str: key equals min");
	    assertEq(FnA1szBinarySearch(A, "b"), 2, "Str: key equals dup b");
	    assertEq(FnA1szBinarySearch(A, "c"), 2, "Str: key between b and d");
	    assertEq(FnA1szBinarySearch(A, "d"), 3, "Str: key equals max");
	    assertEq(FnA1szBinarySearch(A, "z"), 3, "Str: key larger than max");
	}
	System.out.println("Quiz01_01: FnA1szBinarySearch tests passed.");
	return /*void*/ ;
    }
}
