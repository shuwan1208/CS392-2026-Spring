//
// HX: 50 points
// Here we revisit a question on quiz01 (Quiz01_03).
// Instead of sorting 10 elements without recursion,
// you are asked to insertion-sort up to 1 million
// elements without recursion.
// Attention:
// You are suppose to do insertion-sort. If you do
// bubble-sort, you can receive up to 60%, that is
// 30 points of 50.
//
public class Quiz02_02 {
    public static
	<T extends Comparable<T>>
	void sort1000WithNoRecursion(T[] A) {
	for (int i0 = 1; i0 < A.length; i0 += 1) {
	    T key = A[i0];
	    int j0 = i0 - 1;
	    while (j0 >= 0 && key.compareTo(A[j0]) < 0) {
		A[j0 + 1] = A[j0];
		j0 -= 1;
	    }
	    A[j0 + 1] = key;
	}
    }
    public static void main (String[] args) {
	Integer[] A = new Integer[]{9, 3, 7, 1, 8, 2, 5, 4, 6, 0};
	sort1000WithNoRecursion(A);
	for (int i0 = 0; i0 < A.length; i0 += 1) {
	    if (i0 > 0) System.out.print(" ");
	    System.out.print(A[i0]);
	}
	System.out.println();
	return /*void*/;
    }
} // end of [public class Quiz02_02{...}]
