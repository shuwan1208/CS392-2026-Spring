//
// HX-2026-03-04: 30 points
// This one may seem easy but can be time-consuming
// if you use a brute-force approach.
// Hint: Try to think about implementing bubble-sort
// without recursion
//
public class Quiz01_03 {
    private static
	<T extends Comparable<T>>
	void cswap(T[] a, int i, int j) {
	if (a[i].compareTo(a[j]) > 0) {
	    final T tmp = a[i];
	    a[i] = a[j];
	    a[j] = tmp;
	}
	return /*void*/ ;
    }

    public static
	<T extends Comparable<T>>
	T[] sort20WithNoRecursion
	(T x00, T x01, T x02, T x03, T x04, T x05, T x06, T x07, T x08, T x09,
	 T x10, T x11, T x12, T x13, T x14, T x15, T x16, T x17, T x18, T x19) {
	// HX-2026-03-03:
	// Given 30 arguments,
	// please return an array of size 20 containing the
	// 20 arguments sorted according to the order implemented by
	// compareTo on T.
	// HX: No recursion is allowed for this one
	// HX: No loops (either while-loop or for-loop) is allowed.
	// HX: Yes, you can use functions (but not recursive functions)
	// HX: Please do not try to write a HUGE if-then-else mumble jumble!
	//
	// Implementation: unrolled bubble-sort network (190 compare-swaps),
	// using only straight-line code and a helper compare-swap.
	//
	@SuppressWarnings("unchecked")
	final T[] a = (T[]) new Comparable[] {
	    x00, x01, x02, x03, x04, x05, x06, x07, x08, x09,
	    x10, x11, x12, x13, x14, x15, x16, x17, x18, x19
	};

	// pass 1 (0..18)
	cswap(a, 0, 1);  cswap(a, 1, 2);  cswap(a, 2, 3);  cswap(a, 3, 4);  cswap(a, 4, 5);
	cswap(a, 5, 6);  cswap(a, 6, 7);  cswap(a, 7, 8);  cswap(a, 8, 9);  cswap(a, 9, 10);
	cswap(a, 10, 11); cswap(a, 11, 12); cswap(a, 12, 13); cswap(a, 13, 14); cswap(a, 14, 15);
	cswap(a, 15, 16); cswap(a, 16, 17); cswap(a, 17, 18); cswap(a, 18, 19);
	// pass 2 (0..17)
	cswap(a, 0, 1);  cswap(a, 1, 2);  cswap(a, 2, 3);  cswap(a, 3, 4);  cswap(a, 4, 5);
	cswap(a, 5, 6);  cswap(a, 6, 7);  cswap(a, 7, 8);  cswap(a, 8, 9);  cswap(a, 9, 10);
	cswap(a, 10, 11); cswap(a, 11, 12); cswap(a, 12, 13); cswap(a, 13, 14); cswap(a, 14, 15);
	cswap(a, 15, 16); cswap(a, 16, 17); cswap(a, 17, 18);
	// pass 3 (0..16)
	cswap(a, 0, 1);  cswap(a, 1, 2);  cswap(a, 2, 3);  cswap(a, 3, 4);  cswap(a, 4, 5);
	cswap(a, 5, 6);  cswap(a, 6, 7);  cswap(a, 7, 8);  cswap(a, 8, 9);  cswap(a, 9, 10);
	cswap(a, 10, 11); cswap(a, 11, 12); cswap(a, 12, 13); cswap(a, 13, 14); cswap(a, 14, 15);
	cswap(a, 15, 16); cswap(a, 16, 17);
	// pass 4 (0..15)
	cswap(a, 0, 1);  cswap(a, 1, 2);  cswap(a, 2, 3);  cswap(a, 3, 4);  cswap(a, 4, 5);
	cswap(a, 5, 6);  cswap(a, 6, 7);  cswap(a, 7, 8);  cswap(a, 8, 9);  cswap(a, 9, 10);
	cswap(a, 10, 11); cswap(a, 11, 12); cswap(a, 12, 13); cswap(a, 13, 14); cswap(a, 14, 15);
	cswap(a, 15, 16);
	// pass 5 (0..14)
	cswap(a, 0, 1);  cswap(a, 1, 2);  cswap(a, 2, 3);  cswap(a, 3, 4);  cswap(a, 4, 5);
	cswap(a, 5, 6);  cswap(a, 6, 7);  cswap(a, 7, 8);  cswap(a, 8, 9);  cswap(a, 9, 10);
	cswap(a, 10, 11); cswap(a, 11, 12); cswap(a, 12, 13); cswap(a, 13, 14); cswap(a, 14, 15);
	// pass 6 (0..13)
	cswap(a, 0, 1);  cswap(a, 1, 2);  cswap(a, 2, 3);  cswap(a, 3, 4);  cswap(a, 4, 5);
	cswap(a, 5, 6);  cswap(a, 6, 7);  cswap(a, 7, 8);  cswap(a, 8, 9);  cswap(a, 9, 10);
	cswap(a, 10, 11); cswap(a, 11, 12); cswap(a, 12, 13); cswap(a, 13, 14);
	// pass 7 (0..12)
	cswap(a, 0, 1);  cswap(a, 1, 2);  cswap(a, 2, 3);  cswap(a, 3, 4);  cswap(a, 4, 5);
	cswap(a, 5, 6);  cswap(a, 6, 7);  cswap(a, 7, 8);  cswap(a, 8, 9);  cswap(a, 9, 10);
	cswap(a, 10, 11); cswap(a, 11, 12); cswap(a, 12, 13);
	// pass 8 (0..11)
	cswap(a, 0, 1);  cswap(a, 1, 2);  cswap(a, 2, 3);  cswap(a, 3, 4);  cswap(a, 4, 5);
	cswap(a, 5, 6);  cswap(a, 6, 7);  cswap(a, 7, 8);  cswap(a, 8, 9);  cswap(a, 9, 10);
	cswap(a, 10, 11); cswap(a, 11, 12);
	// pass 9 (0..10)
	cswap(a, 0, 1);  cswap(a, 1, 2);  cswap(a, 2, 3);  cswap(a, 3, 4);  cswap(a, 4, 5);
	cswap(a, 5, 6);  cswap(a, 6, 7);  cswap(a, 7, 8);  cswap(a, 8, 9);  cswap(a, 9, 10);
	cswap(a, 10, 11);
	// pass 10 (0..9)
	cswap(a, 0, 1);  cswap(a, 1, 2);  cswap(a, 2, 3);  cswap(a, 3, 4);  cswap(a, 4, 5);
	cswap(a, 5, 6);  cswap(a, 6, 7);  cswap(a, 7, 8);  cswap(a, 8, 9);  cswap(a, 9, 10);
	// pass 11 (0..8)
	cswap(a, 0, 1);  cswap(a, 1, 2);  cswap(a, 2, 3);  cswap(a, 3, 4);  cswap(a, 4, 5);
	cswap(a, 5, 6);  cswap(a, 6, 7);  cswap(a, 7, 8);  cswap(a, 8, 9);
	// pass 12 (0..7)
	cswap(a, 0, 1);  cswap(a, 1, 2);  cswap(a, 2, 3);  cswap(a, 3, 4);  cswap(a, 4, 5);
	cswap(a, 5, 6);  cswap(a, 6, 7);  cswap(a, 7, 8);
	// pass 13 (0..6)
	cswap(a, 0, 1);  cswap(a, 1, 2);  cswap(a, 2, 3);  cswap(a, 3, 4);  cswap(a, 4, 5);
	cswap(a, 5, 6);  cswap(a, 6, 7);
	// pass 14 (0..5)
	cswap(a, 0, 1);  cswap(a, 1, 2);  cswap(a, 2, 3);  cswap(a, 3, 4);  cswap(a, 4, 5);
	cswap(a, 5, 6);
	// pass 15 (0..4)
	cswap(a, 0, 1);  cswap(a, 1, 2);  cswap(a, 2, 3);  cswap(a, 3, 4);  cswap(a, 4, 5);
	// pass 16 (0..3)
	cswap(a, 0, 1);  cswap(a, 1, 2);  cswap(a, 2, 3);  cswap(a, 3, 4);
	// pass 17 (0..2)
	cswap(a, 0, 1);  cswap(a, 1, 2);  cswap(a, 2, 3);
	// pass 18 (0..1)
	cswap(a, 0, 1);  cswap(a, 1, 2);
	// pass 19 (0..0)
	cswap(a, 0, 1);

	return a;
    }
    public static void main (String[] args) {
	// HX-2025-10-12:
	// Please write minimal testing code for sort20WithNoRecursion.
	Comparable[] xs =
	    sort20WithNoRecursion
	    ( 5, 4, 3, 2, 1, 0, 9, 8, 7, 6,
	      15, 14, 13, 12, 11, 10, 19, 18, 17, 16 );
	for (int i = 1; i < xs.length; i += 1) {
	    if (((Integer) xs[i - 1]) > ((Integer) xs[i])) {
		throw new RuntimeException("Quiz01_03: sorting failed at i=" + i);
	    }
	}
	System.out.println("Quiz01_03: sort20WithNoRecursion tests passed.");
    }
}
