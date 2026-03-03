package Library00.FnA1sz;

import Library00.FnList.*;

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
