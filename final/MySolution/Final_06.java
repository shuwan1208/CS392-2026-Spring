import MyLibrary.FnList.*;
import MyLibrary.MyRefer.*;

public class Final_06 {
    public static <T extends Comparable<T>> void sort1000WithNoRecursion(T[] A) {
        FnList<Integer> ids = FnListSUtil.int1$make(A.length);
        FnListSUtil.foritm(ids, (Integer i0) -> {
            if (i0 > 0) {
                insertByShifting(A, i0);
            }
        });
    }

    private static <T extends Comparable<T>> void insertByShifting(T[] A, int i0) {
        final T key = A[i0];
        final MyRefer<Integer> lo = new MyRefer<Integer>(0);
        final MyRefer<Integer> hi = new MyRefer<Integer>(i0);
        FnListSUtil.foritm(FnListSUtil.int1$make(21), (Integer ignored) -> {
            int l0 = lo.get$raw();
            int h0 = hi.get$raw();
            if (l0 < h0) {
                int m0 = l0 + (h0 - l0) / 2;
                if (key.compareTo(A[m0]) < 0) {
                    hi.set$raw(m0);
                } else {
                    lo.set$raw(m0 + 1);
                }
            }
        });
        int j0 = lo.get$raw();
        if (j0 < i0) {
            System.arraycopy(A, j0, A, j0 + 1, i0 - j0);
            A[j0] = key;
        }
    }

    public static void main(String[] args) {
        Integer[] A = new Integer[]{9, 3, 7, 1, 8, 2, 5, 4, 6, 0};
        sort1000WithNoRecursion(A);
        FnListSUtil.foritm(FnListSUtil.int1$make(A.length), (Integer i0) -> System.out.println(A[i0]));
    }
}
