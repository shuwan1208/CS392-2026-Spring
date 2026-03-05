//
// HX: For testing Quiz01_05
//
import MyLibrary.FnList.FnList;
import MyLibrary.FnList.FnListSUtil;
import java.util.function.ToIntBiFunction;

abstract public class Quiz01_05_test {
    private static void assertTrue(boolean cond, String msg) {
	if (!cond) throw new RuntimeException(msg);
    }
    public static void main (String args[]) {
	// Your testing code for Quiz01_05
	// Build: 0, 1, 2, ..., 999
	FnList<Integer> xs = FnListSUtil.int1$make(1000);

	// Parity comparator (evens first; equal parity returns 0).
	ToIntBiFunction<Integer, Integer> parityCmp =
	    (a, b) -> (a & 1) - (b & 1);

	FnList<Integer> ys = Quiz01_05.someRevStableSort(xs, parityCmp);

	// Expected reverse-stable parity sort:
	// evens: 998, 996, ..., 0  then odds: 999, 997, ..., 1
	int expect = 998;
	int phase = 0; // 0: evens, 1: odds
	while (ys.consq()) {
	    int v = ys.hd();
	    if (phase == 0) {
		assertTrue((v & 1) == 0, "expected even, got " + v);
		assertTrue(v == expect, "even order mismatch: expected " + expect + ", got " + v);
		expect -= 2;
		if (expect < 0) {
		    phase = 1;
		    expect = 999;
		}
	    } else {
		assertTrue((v & 1) == 1, "expected odd, got " + v);
		assertTrue(v == expect, "odd order mismatch: expected " + expect + ", got " + v);
		expect -= 2;
	    }
	    ys = ys.tl();
	}
	assertTrue(expect == -1, "did not consume all expected odds");
	System.out.println("Quiz01_05_test: reverse-stable parity sort passed.");
    }
}
