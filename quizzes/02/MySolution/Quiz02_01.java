//
// HX-2026-04-28: 50 points
//
// This question tests your understanding
// of recursion and time analysis involving
// recursion.
// Given a sequence xs, a subsequence of xs
// can be represented as a list of integers
// (representing indices). For instance, given
// xs = "Hello", (0, 2, 4) refers to the subeqence
// "Hlo" (since xs[0] = 'H', xs[2] = 'l', and
// xs[4] = 'o'); (0, 3, 4) also refers to "Hlo".
// The subsequece (0, 2, 4) is to the left of
// the subsequece (0, 3, 4) as (0, 2, 4) is less
// than (0, 3, 4) according to the lexicographic
// ordering.
//
// Here you are asked to implement a function that
// finds the longest leftmost ascending subsequence
// of a given sequence.
// For instance, suppose xs = [1,2,1,2,3,1,2,3,4],
// the longest leftmost ascending subsequence of xs
// is represented by (0, 1, 3, 4, 7, 8) (which refers
// to [1,2,2,3,3,4] in xs).
//
// In order to receive 50 points, your implementation
// should be quadratic time, that is, O(n^2) time and
// you MUST give a brief explanation as to why it is so.
// Otherwise, a working solution receives at most 60%, that
// is, 30 points out of 50 points.
//
import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;
public class Quiz02_01 {
    private static <T extends Comparable<T>>
    boolean leq(T x1, T x2) {
	return x1.compareTo(x2) <= 0;
    }

    public static
	<T extends Comparable<T>>
	FnList<Integer> FnA1szLongestMonoSubsequence(FnA1sz<T> xs) {
	// O(n^2): for each i, scan all j>i once to compute the best
	// nondecreasing subsequence starting at i, then reconstruct it.
	int n0 = xs.length();
	if (n0 <= 0) return new FnList<Integer>();
	int[] lens = new int[n0];
	int[] next = new int[n0];
	for (int i0 = n0 - 1; i0 >= 0; i0 -= 1) {
	    lens[i0] = 1;
	    next[i0] = -1;
	    for (int j0 = i0 + 1; j0 < n0; j0 += 1) {
		if (!leq(xs.getAt(i0), xs.getAt(j0))) continue;
		int cand = 1 + lens[j0];
		if (cand > lens[i0]) {
		    lens[i0] = cand;
		    next[i0] = j0;
		} else if (cand == lens[i0] && next[i0] > j0) {
		    next[i0] = j0;
		}
	    }
	}
	int start = 0;
	for (int i0 = 1; i0 < n0; i0 += 1) {
	    if (lens[i0] > lens[start]) start = i0;
	}
	FnList<Integer> res = new FnList<Integer>();
	for (int i0 = start; i0 >= 0; i0 = next[i0]) {
	    res = new FnList<Integer>(i0, res);
	    if (next[i0] < 0) break;
	}
	return FnListSUtil.reverse(res);
    }
    public static void main (String[] args) {
	FnA1sz<Integer> xs =
	    new FnA1sz<Integer>(new Integer[]{1, 2, 1, 2, 3, 1, 2, 3, 4});
	FnList<Integer> ans = FnA1szLongestMonoSubsequence(xs);
	FnListSUtil.System$out$print(ans);
	System.out.println();
	return /*void*/;
    }
} // end of [public class Quiz02_01{...}]
