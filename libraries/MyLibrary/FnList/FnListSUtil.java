package MyLibrary.FnList;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.ToIntBiFunction;

public class FnListSUtil {
//
    public static<T>
	FnList<T> nil() {
	return new FnList<T>();
    }
    public static<T>
	FnList<T> sing(T x0) {
	return new FnList<T>(x0, nil());
    }
    public static<T>
	FnList<T>
	cons(T x0, FnList<T> xs) {
	return new FnList<T>(x0, xs);
    }
//
    public static
	FnList<Integer>
	int1$make(int n0) {
	FnList<Integer> res = nil();
	for (int i = n0 - 1; i >= 0; i -= 1) {
	    res = cons(i, res);
	}
	return res;
    }
//
    // HX: [length] is O(n)
    public static<T>
	int length(FnList<T> xs) {
	int res = 0;
	while (true) {
	    if (xs.nilq()) break;
	    res += 1; xs = xs.tl();
	}
	return res;
    }
//
    public static<T>
	FnList<T> reverse(FnList<T> xs) {
	FnList<T> ys;
	ys = nil();
	while (!xs.nilq()) {
	    ys = cons(xs.hd(), ys); xs = xs.tl();
	}
	return ys;
    }    

    public static<T>
	FnList<T> append(FnList<T> xs, FnList<T> ys) {
	if (xs.nilq()) {
	    return ys;
	} else {
	    return cons(xs.hd(), append(xs.tl(), ys));
	}
    }

    public static<T>
	void foritm(FnList<T> xs, Consumer<? super T> work) {
	while (xs.consq()) {
	    work.accept(xs.hd()); xs = xs.tl();
	}
	return;
    }

    public static<T>
	boolean forall(FnList<T> xs, Predicate<? super T> pred) {
	while (true) {
	    if (xs.nilq()) {
		break;
	    } else {
		if (pred.test(xs.hd())) {
		    xs = xs.tl(); continue;
		} else {
		    return false; // HX: counterexample found!
		}
	    }
	}
	return true;
    }

    public static<T>
	void rforitm(FnList<T> xs, Consumer<? super T> work) {
	foritm(reverse(xs), work);
    }

    public static<T>
	void iforitm(FnList<T> xs, BiConsumer<Integer, ? super T> work) {
	int i = 0;
	while (xs.consq()) {
	    work.accept(i, xs.hd()); i += 1; xs = xs.tl();
	}
	return;
    }

    public static<T>
	boolean iforall(FnList<T> xs, BiPredicate<Integer, ? super T> pred) {
	int i = 0;
	while (true) {
	    if (xs.nilq()) {
		break;
	    } else {
		if (pred.test(i, xs.hd())) {
		    i += 1; xs = xs.tl(); continue;
		} else {
		    return false; // HX: counterexample found!
		}
	    }
	}
	return true;
    }

    public static<T>
	void irforitm(FnList<T> xs, BiConsumer<Integer, ? super T> work) {
	iforitm(reverse(xs), work);
    }

    public static<T>
	void System$out$print(FnList<T> xs) {
    	System.out.print("FnList(");
	iforitm(xs,
          (i, itm) ->
	  {
	      if (i > 0) {
		  System.out.print(",");
	      }
	      System.out.print(itm.toString());
	  }
	);
	System.out.print(")");
    }
//
    public static<T>
	FnList<T>
	insertSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// Loop-based insertion sort (stable) without recursion.
	FnList<T> ys = nil();
	while (xs.consq()) {
	    ys = insertSort_insert(ys, xs.hd(), cmp);
	    xs = xs.tl();
	}
	return ys;
    }
    private static<T>
	FnList<T>
	insertSort_insert(FnList<T> xs, T x0, ToIntBiFunction<T,T> cmp) {
	// Maintain stability by inserting [x0] AFTER existing equal items.
	FnList<T> prefix_rev = nil();
	FnList<T> rest = xs;
	while (rest.consq()) {
	    final T hd = rest.hd();
	    final int sgn = cmp.applyAsInt(x0, hd);
	    if (sgn < 0) {
		break;
	    }
	    prefix_rev = cons(hd, prefix_rev);
	    rest = rest.tl();
	}
	FnList<T> res = cons(x0, rest);
	while (prefix_rev.consq()) {
	    res = cons(prefix_rev.hd(), res);
	    prefix_rev = prefix_rev.tl();
	}
	return res;
    }
//
    public static<T>
	FnList<T>
	mergeSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	final int n = xs.length();
	final T[] arr = (T[]) new Object[n];
	int i = 0;
	while (xs.consq()) {
	    arr[i] = xs.hd();
	    i += 1;
	    xs = xs.tl();
	}
	Arrays.sort(arr, (a, b) -> cmp.applyAsInt(a, b));
	FnList<T> res = nil();
	for (i = n - 1; i >= 0; i -= 1) {
	    res = cons(arr[i], res);
	}
	return res;
    }

    public static<T>
	FnList<T> fwork$make(Consumer<Consumer<? super T>> fwork) {
	final AtomicReference<FnList<T>> rf =
	    new AtomicReference<FnList<T>>(nil());
	fwork.accept((T x0) -> rf.set(cons(x0, rf.get())));
	return reverse(rf.get());
    }

    public static<T,R>
	FnList<R> map_list
	(FnList<T> xs, Function<? super T, R> fopr) {
	FnList<R> rs = nil();
	while (xs.consq()) {
	    rs = cons(fopr.apply(xs.hd()), rs);
	    xs = xs.tl();
	}
	return reverse(rs);
    }
//
} // end of [public class FnListSUtil{...}]
