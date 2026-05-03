package MyLibrary.LnList;

import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class LnListSUtil {
//
    public static<T>
	LnList<T> nil() {
	return new LnList<T>();
    }
    public static<T>
	LnList<T>
	cons(T x0, LnList<T> xs) {
	return new LnList<T>(x0, xs);
    }
//
    public static<T>
	boolean nilq1(LnList<T> xs) {
	return xs.nilq1();
    }
    public static<T>
	boolean consq1(LnList<T> xs) {
	return xs.consq1();
    }
//
    public static<T>
	LnList<T> reverse0(LnList<T> xs) {
	return xs.reverse0();
    }

    public static<T>
	int length1(LnList<T> xs) {
	return xs.length1();
    }

    public static<T>
	LnList<T>
	insertSort(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
	Object[] arr = toArray(xs);
	for (int i0 = 1; i0 < arr.length; i0 += 1) {
	    Object key = arr[i0];
	    int j0 = i0 - 1;
	    while (j0 >= 0 && cmp.applyAsInt((T) key, (T) arr[j0]) < 0) {
		arr[j0 + 1] = arr[j0];
		j0 -= 1;
	    }
	    arr[j0 + 1] = key;
	}
	return fromArray(arr);
    }

    public static<T>
	LnList<T>
	mergeSort(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
	Object[] arr = toArray(xs);
	Arrays.sort(arr, (x1, x2) -> cmp.applyAsInt((T) x1, (T) x2));
	return fromArray(arr);
    }

    private static<T>
	Object[] toArray(LnList<T> xs) {
	Object[] arr = new Object[xs.length1()];
	int i0 = 0;
	while (!xs.nilq1()) {
	    arr[i0] = xs.hd1();
	    i0 += 1;
	    xs = xs.tl1();
	}
	return arr;
    }

    private static<T>
	LnList<T> fromArray(Object[] arr) {
	LnList<T> res = new LnList<T>();
	for (int i0 = arr.length - 1; i0 >= 0; i0 -= 1) {
	    res = new LnList<T>((T) arr[i0], res);
	}
	return res;
    }
//
} // end of [public class LnListSUtil{...}]
