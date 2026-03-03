import MyLibrary.FnList.*;
    
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign05_01 {

    public static
	<T extends Comparable<T>>
	FnList<T> insertSort(FnList<T> xs) {
	return insertSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
//
    public static<T> FnList<T>
	insertSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2026-02-26:
	// Loop-based insertion sort (stable) without recursion.
	//
	// We sort from right-to-left (like the recursive reference
	// implementation) by first reversing [xs]. With this order,
	// stability is achieved by inserting [x0] BEFORE equal items.
	FnList<T> ys = FnListSUtil.nil();
	FnList<T> xs_r = FnListSUtil.reverse(xs);
	while (xs_r.consq()) {
	    ys = insertSort_insert(ys, xs_r.hd(), cmp);
	    xs_r = xs_r.tl();
	}
	return ys;
    }

    private static<T> FnList<T>
	insertSort_insert(FnList<T> xs, T x0, ToIntBiFunction<T,T> cmp) {
	FnList<T> prefix_rev = FnListSUtil.nil();
	FnList<T> rest = xs;
	while (rest.consq()) {
	    final T hd = rest.hd();
	    final int sgn = cmp.applyAsInt(x0, hd);
	    if (sgn <= 0) { 
		break;
	    }
	    prefix_rev = FnListSUtil.cons(hd, prefix_rev);
	    rest = rest.tl();
	}
	FnList<T> res = FnListSUtil.cons(x0, rest);
	while (prefix_rev.consq()) {
	    res = FnListSUtil.cons(prefix_rev.hd(), res);
	    prefix_rev = prefix_rev.tl();
	}
	return res;
    }

   
	public static void main(String[] args) {
		// Please write some testing code that applies
		// insertSort to the following list of 1M numbers:
		// 1, 0, 3, 2, 5, 4, 7, 6, 9, 8, 11, 10, ..., 999999, 999998.
		FnList<Integer> list = FnListSUtil.nil();
		System.out.println("Building the 1M element test list...");
		for (int i = 1_000_000 - 2; i >= 0; i -= 2) {
			list = FnListSUtil.cons(i + 1, FnListSUtil.cons(i, list));
		}
			System.out.println("List built successfully.");
			System.out.println("Starting insertSort...");
			long startTime = System.currentTimeMillis();
			
			FnList<Integer> sortedList = insertSort(list);
			
			long endTime = System.currentTimeMillis();
			System.out.println("Sorting completed! Time taken: " + (endTime - startTime) + " ms");
			
			if (sortedList.consq() && sortedList.tl().consq()) {
				System.out.println("First element (should be 0): " + sortedList.hd());
				System.out.println("Second element (should be 1): " + sortedList.tl().hd());
			}
		}
} // end of [public class Assign05_01{...}]