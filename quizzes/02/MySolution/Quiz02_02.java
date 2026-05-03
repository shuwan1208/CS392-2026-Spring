import java.util.Arrays;
import java.util.stream.IntStream;

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
	IntStream.range(1, A.length).forEach(i0 -> insertByShifting(A, i0));
    }

    private static
	<T extends Comparable<T>>
	void insertByShifting(T[] A, int i0) {
	T key = A[i0];
	int j0 = Arrays.binarySearch(A, 0, i0, key);
	if (j0 < 0) j0 = -(j0 + 1);
	if (j0 < i0) {
	    System.arraycopy(A, j0, A, j0 + 1, i0 - j0);
	    A[j0] = key;
	}
    }
    public static void main (String[] args) {
	Integer[] A = new Integer[]{9, 3, 7, 1, 8, 2, 5, 4, 6, 0};
	sort1000WithNoRecursion(A);
	System.out.println(Arrays.toString(A));
	return /*void*/;
    }
} // end of [public class Quiz02_02{...}]
