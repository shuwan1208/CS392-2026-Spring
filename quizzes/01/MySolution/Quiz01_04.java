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
	if (xs.nilq1()) return xs;

	// Make an empty list-wrapper without calling any LnList constructor:
	// take a tail-wrapper and free it (does not affect the original nodes).
	LnList<T> ys = xs.tl1();
	ys.free();

	while (xs.consq1()) {
	    // Detach the head node of [xs] as a single-node list [x0s].
	    LnList<T> rest = xs.unlink(); // xs becomes 1-node; rest is the tail
	    LnList<T> x0s = xs; // single-node list (tail == null)
	    xs = rest;

	    // Insert [x0s] into [ys] in a stable manner (after all equals).
	    if (ys.nilq1()) {
		ys = x0s;
		continue;
	    }
	    final T x0 = x0s.hd1();
	    if (x0.compareTo(ys.hd1()) < 0) {
		x0s.link(ys);
		ys = x0s;
		continue;
	    }

	    LnList<T> pre = ys;
	    while (pre.consq1()) {
		LnList<T> nxt = pre.tl1();
		if (nxt.nilq1()) break;
		// Move forward while nxt.head <= x0 (stability: insert after equals).
		if (nxt.hd1().compareTo(x0) <= 0) {
		    pre = nxt;
		} else {
		    break;
		}
	    }
	    LnList<T> suffix = pre.unlink();
	    pre.link(x0s);
	    x0s.link(suffix);
	}

	return ys;
    }

    private static void assertTrue(boolean cond, String msg) {
	if (!cond) throw new RuntimeException(msg);
    }

    public static void main (String[] args) {
	// HX-2026-03-04:
	// Here you can use constructors in LnList.
	// Please write minimal testing code for LnListInsertSort
	// 1. Please sort a nearly sorted list of 1M elements
	// 2. Please do parity-sorting to test that LnListInsertSort is stable

	// 2) Stability check via "parity sort"
	final class ParityItem implements Comparable<ParityItem> {
	    final int value;
	    final int id; // original position within parity group
	    ParityItem(int value, int id) { this.value = value; this.id = id; }
	    public int compareTo(ParityItem that) { return (this.value & 1) - (that.value & 1); }
	}
	{
	    LnList<ParityItem> xs =
		new LnList<ParityItem>(new ParityItem(0, 0),
		new LnList<ParityItem>(new ParityItem(1, 0),
		new LnList<ParityItem>(new ParityItem(0, 1),
		new LnList<ParityItem>(new ParityItem(1, 1),
		new LnList<ParityItem>(new ParityItem(0, 2),
		new LnList<ParityItem>(new ParityItem(1, 2),
		new LnList<ParityItem>()))))));
	    LnList<ParityItem> ys = LnListInsertSort(xs);
	    // Expect all evens first, preserving relative id order: 0,1,2; then odds 0,1,2.
	    int phase = 0;
	    int nextEvenId = 0;
	    int nextOddId = 0;
	    while (ys.consq1()) {
		ParityItem itm = ys.hd1();
		if ((itm.value & 1) == 0) {
		    assertTrue(phase == 0, "even appears after odds (unstable/incorrect)");
		    assertTrue(itm.id == nextEvenId, "even relative order changed (not stable)");
		    nextEvenId += 1;
		} else {
		    phase = 1;
		    assertTrue(itm.id == nextOddId, "odd relative order changed (not stable)");
		    nextOddId += 1;
		}
		ys = ys.tl1();
	    }
	    System.out.println("Quiz01_04: stability test passed.");
	}

	// 1) Nearly-sorted 1M elements (guarded so it doesn't run accidentally)
	if (args.length >= 1 && args[0].equals("1m")) {
	    final int n = 1_000_000;
	    LnList<Integer> xs = new LnList<Integer>();
	    for (int i = n - 1; i >= 0; i -= 1) {
		xs = new LnList<Integer>(i, xs);
	    }
	    // Introduce a tiny disorder near the front:
	    // swap first two elements by rebuilding just the prefix.
	    xs = new LnList<Integer>(1, new LnList<Integer>(0, xs.tl1().tl1()));

	    long t0 = System.currentTimeMillis();
	    LnList<Integer> ys = LnListInsertSort(xs);
	    long t1 = System.currentTimeMillis();

	    // Sanity check: non-decreasing order
	    if (ys.consq1()) {
		int prev = ys.hd1();
		ys = ys.tl1();
		while (ys.consq1()) {
		    int cur = ys.hd1();
		    if (prev > cur) {
			throw new RuntimeException("Quiz01_04: 1m sort not ordered");
		    }
		    prev = cur;
		    ys = ys.tl1();
		}
	    }
	    System.out.println("Quiz01_04: 1m nearly-sorted list sorted in " + (t1 - t0) + " ms");
	}

	return /*void*/ ;
    }
}
