package MyLibrary.FnA1sz;

import MyLibrary.FnList.*;

import java.util.Arrays;
import java.util.function.ToIntBiFunction;

public class FnA1szUtil<T> {
    public FnList<T> listize(FnA1sz<T> xs) {
	FnList<T> res = FnListSUtil.nil();
	for (int i = xs.length() - 1; i >= 0; i -= 1) {
	    res = FnListSUtil.cons(xs.getAt(i), res);
	}
	return res;
    }

    public FnList<T> rlistize(FnA1sz<T> xs) {
	FnList<T> res = FnListSUtil.nil();
	for (int i = 0; i < xs.length(); i += 1) {
	    res = FnListSUtil.cons(xs.getAt(i), res);
	}
	return res;
    }

    public FnA1sz<T> mergeSort(FnA1sz<T> xs, ToIntBiFunction<T, T> cmp) {
	final int n = xs.length();
	final T[] res = (T[]) new Object[n];
	for (int i = 0; i < n; i += 1) {
	    res[i] = xs.getAt(i);
	}
	Arrays.sort(res, (a, b) -> cmp.applyAsInt(a, b));
	return new FnA1sz<T>(res);
    }

    public FnA1sz<T> quickSort(FnA1sz<T> xs, ToIntBiFunction<T, T> cmp) {
	final int n = xs.length();
	final T[] res = (T[]) new Object[n];
	for (int i = 0; i < n; i += 1) {
	    res[i] = xs.getAt(i);
	}
	quickSort0(res, 0, n-1, cmp);
	return new FnA1sz<T>(res);
    }

    private void quickSort0(T[] A, int lo, int hi, ToIntBiFunction<T, T> cmp) {
	if (lo >= hi) {
	    return;
	}
	int mid = lo + (hi - lo) / 2;
	T pivot = A[mid];
	int lt = lo;
	int i = lo;
	int gt = hi;
	while (i <= gt) {
	    int sgn = cmp.applyAsInt(A[i], pivot);
	    if (sgn < 0) {
		swap(A, lt, i);
		lt += 1;
		i += 1;
	    } else if (sgn > 0) {
		swap(A, i, gt);
		gt -= 1;
	    } else {
		i += 1;
	    }
	}
	quickSort0(A, lo, lt-1, cmp);
	quickSort0(A, gt+1, hi, cmp);
    }

    private void swap(T[] A, int i, int j) {
	T tmp = A[i];
	A[i] = A[j];
	A[j] = tmp;
    }

    public FnA1sz<T> insertSort(FnA1sz<T> xs, ToIntBiFunction<T, T> cmp) {
	final int n = xs.length();
	final T[] res = (T[]) new Object[n];
	for (int i = 0; i < n; i += 1) {
	    res[i] = xs.getAt(i);
	}
	for (int i = 1; i < n; i += 1) {
	    final T key = res[i];
	    int j = i - 1;
	    while (j >= 0 && cmp.applyAsInt(key, res[j]) < 0) {
		res[j + 1] = res[j];
		j -= 1;
	    }
	    res[j + 1] = key;
	}
	return new FnA1sz<T>(res);
    }

    public int z2forcmp(FnA1sz<T> xs, FnA1sz<T> ys, ToIntBiFunction<T, T> cmp) {
	final int nx = xs.length();
	final int ny = ys.length();
	final int n0 = (nx < ny ? nx : ny);
	for (int i = 0; i < n0; i += 1) {
	    final int sgn = cmp.applyAsInt(xs.getAt(i), ys.getAt(i));
	    if (sgn != 0) return sgn;
	}
	if (nx == ny) return 0;
	return (nx < ny ? -1 : 1);
    }
}
