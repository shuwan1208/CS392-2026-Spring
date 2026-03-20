//
// HX: 50 points
//
public class Quiz01_04 {
    public static 
	<T extends Comparable<T>> 
	LnList<T> LnListInsertSort(LnList<T> xs) {
		// HX-2025-10-12:
	// Please implement (stable) insertion sort on a
	// linked list (LnList).
	// Note that you are not allowed to modify the definition
	// of the LnList class. You can only use the public methods
	// provided by the LnList class; you cannot use any constructors
	// in LnList
        if (xs == null || xs.nilq1()) return xs;
        LnList<T> rev = xs;
        LnList<T> rem = rev.unlink();
        while (rem.consq1()) {
            LnList<T> nd = rem;
            rem = rem.unlink();
            nd.link(rev);
            rev = nd;
        }
        LnList<T> sorted = rev;
        rem = sorted.unlink();
        while (rem.consq1()) {
            LnList<T> cur = rem;
            rem = rem.unlink();
            if (cur.hd1().compareTo(sorted.hd1()) <= 0) {
                cur.link(sorted);
                sorted = cur;
            } else {
                LnList<T> prev = sorted;
                LnList<T> nxt = prev.tl1();
                while (nxt.consq1() && cur.hd1().compareTo(nxt.hd1()) > 0) {
                    prev = nxt;
                    nxt = prev.tl1();
                }
				LnList<T> tail = prev.unlink();
                cur.link(tail);
                prev.link(cur);
            }
        }
        return sorted;
    }
    static class Parity implements Comparable<Parity> {
        final int val;
        Parity(int v) { this.val = v; }
        
        @Override
        public int compareTo(Parity o) {
            return Integer.compare(this.val % 2, o.val % 2);
        }
    }

   
	public static void main(String[] args) {
		// HX-2026-03-04:
	 // Here you can use constructors in LnList.
	 // Please write minimal testing code for LnListInsertSort
	 // 1. Please sort a nearly sorted list of 1M elements
	 // 2. Please do parity-sorting to test that LnListInsertSort is stable
		 int N = 1000000;
		 LnList<Integer> largeList = new LnList<>();
		 for (int i = N; i >= 1; i -= 2) {
			 largeList = new LnList<>(i, largeList);
			 largeList = new LnList<>(i - 1, largeList);
		 }
		 long start = System.currentTimeMillis();
		 LnList<Integer> sortedLarge = LnListInsertSort(largeList);
		 long end = System.currentTimeMillis();
		 System.out.println("1. 1M nearly-sorted elements took: " + (end - start) + " ms (Requirement: < 5000 ms)");
		 LnList<Parity> parityList = new LnList<>();
		 int[] vals = {0, 5, 4, 1, 2, 3}; 
		 for (int v : vals) {
			 parityList = new LnList<>(new Parity(v), parityList);
		 }
		 System.out.print("2. Before parity sort: ");
		 parityList.foritm1(p -> System.out.print(p.val + " "));
		 System.out.println();
		 LnList<Parity> sortedParity = LnListInsertSort(parityList);
		 System.out.print("   After parity sort:  ");
		 sortedParity.foritm1(p -> System.out.print(p.val + " "));
		 System.out.println();
		 System.out.println("   (Expected sequence: 2 4 0 3 1 5 - Evens first, then odds. Order preserved)");
	 }
}