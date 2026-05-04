import MyLibrary.FnList.*;
import MyLibrary.LnList.*;

import java.util.function.ToIntBiFunction;

public class Final_05 {
    private static final class HeadWrap<T> implements Comparable<HeadWrap<T>> {
        final LnList<T> list;
        final int src;
        final ToIntBiFunction<T, T> cmp;

        HeadWrap(LnList<T> list, int src, ToIntBiFunction<T, T> cmp) {
            this.list = list;
            this.src = src;
            this.cmp = cmp;
        }

        public int compareTo(HeadWrap<T> other) {
            int sgn = cmp.applyAsInt(this.list.hd1(), other.list.hd1());
            if (sgn != 0) {
                return -sgn;
            }
            if (this.src < other.src) {
                return 1;
            }
            if (this.src > other.src) {
                return -1;
            }
            return 0;
        }
    }

    public static<T> LnList<T>
    LnList_n$way$merge(LnList<T> xss[], ToIntBiFunction<T,T> cmp) {
        MyPQueueArray<HeadWrap<T>> pq = new MyPQueueArray<HeadWrap<T>>(xss.length + 1);
        for (int i = 0; i < xss.length; i += 1) {
            if (xss[i] != null && xss[i].consq1()) {
                pq.enque$raw(new HeadWrap<T>(xss[i], i, cmp));
            }
        }
        LnList<T> res = new LnList<T>();
        LnList<T> tail = new LnList<T>();
        while (!pq.isEmpty()) {
            HeadWrap<T> top = pq.deque$raw();
            LnList<T> node = top.list;
            LnList<T> rest = node.unlink1();
            if (res.nilq1()) {
                res = node;
                tail = node;
            } else {
                tail.link1(node);
                tail = node;
            }
            if (rest.consq1()) {
                pq.enque$raw(new HeadWrap<T>(rest, top.src, cmp));
            }
        }
        return res;
    }

    public static<T>
    FnList<T>
    LnList_mergeSort$100way(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
        return toFnList(mergeSort$100way_ln(xs, cmp));
    }

    private static<T>
    LnList<T>
    mergeSort$100way_ln(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
        int n = xs.length1();
        if (n <= 1) {
            return xs;
        }
        LnList<T>[] parts = split100(xs, n);
        for (int i = 0; i < 100; i += 1) {
            if (parts[i].consq1() && parts[i].tl1().consq1()) {
                parts[i] = mergeSort$100way_ln(parts[i], cmp);
            }
        }
        return LnList_n$way$merge(parts, cmp);
    }

    private static<T>
    LnList<T>[]
    split100(LnList<T> xs, int n) {
        LnList<T>[] parts = (LnList<T>[]) new LnList[100];
        LnList<T> rest = xs;
        int q = n / 100;
        int r = n % 100;
        for (int i = 0; i < 100; i += 1) {
            int len = q + (i < r ? 1 : 0);
            if (len <= 0 || rest.nilq1()) {
                parts[i] = new LnList<T>();
            } else {
                parts[i] = rest;
                LnList<T> last = rest;
                for (int j = 1; j < len; j += 1) {
                    last = last.tl1();
                }
                rest = last.unlink1();
            }
        }
        return parts;
    }

    private static<T>
    FnList<T>
    toFnList(LnList<T> xs) {
        FnList<T> rev = FnListSUtil.nil();
        while (xs.consq1()) {
            rev = FnListSUtil.cons(xs.hd1(), rev);
            xs = xs.tl1();
        }
        return FnListSUtil.reverse(rev);
    }

    public static void main(String[] args) {
        LnList<Integer> xs = new LnList<Integer>();
        for (int i = 999999; i >= 0; i -= 1) {
            xs = new LnList<Integer>(i, xs);
        }
        long t0 = System.currentTimeMillis();
        FnList<Integer> ys =
            LnList_mergeSort$100way
            (
                xs,
                (x1, x2) -> {
                    int p1 = x1 % 2;
                    int p2 = x2 % 2;
                    if (p1 != p2) {
                        return p1 - p2;
                    }
                    return 0;
                }
            );
        long t1 = System.currentTimeMillis();
        System.out.println("time(ms) = " + (t1 - t0));
        int i = 0;
        while (i < 20 && ys.consq()) {
            System.out.println(ys.hd());
            ys = ys.tl();
            i += 1;
        }
    }
}
