import java.util.function.ToIntBiFunction;
import MyLibrary.FnList.*;
//
// HX: 30 points
//
/*
//
 Reverse-stable sorting is similar to stable sorting:
 The ordering of the equals are reversed in the sorted
 version. For instance, 1^1, 2^1, 3^1, 2^2, 3^2, 1^2
 becomes 1^2, 1^1, 2^2, 2^1, 3^2, 3^1 after sorted in
 the reverse-stable manner. If this is unclear to you,
 please seek clarification on Piazza.
//
 No use of external methods (e.g., those from Arrays)
 is allowed here.
//
*/

abstract public class Quiz01_05 {

    private static class Tagged<T> {
        final T val;
        final int idx;
        Tagged(T v, int i) { val = v; idx = i; }
    }

    public abstract <T> FnList<T> someSort(FnList<T> xs, ToIntBiFunction<T,T> cmp);

    public <T> FnList<T> someRevStableSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
        if (xs == null || xs.nilq()) return xs;

        FnList<Tagged<T>> taggedRev = new FnList<>();
        FnList<T> cur = xs;
        int i = 0;
        
        while (cur.consq()) {
            taggedRev = new FnList<>(new Tagged<>(cur.hd(), i), taggedRev);
            cur = cur.tl();
            i++;
        }
        
        FnList<Tagged<T>> tagged = reverseList(taggedRev);

        FnList<Tagged<T>> sortedTagged = someSort(tagged, (a, b) -> {
            int c = cmp.applyAsInt(a.val, b.val);
            if (c != 0) return c;
            return Integer.compare(b.idx, a.idx); 
        });

        FnList<T> resultRev = new FnList<>();
        FnList<Tagged<T>> cur2 = sortedTagged;
        
        while (cur2.consq()) {
            resultRev = new FnList<>(cur2.hd().val, resultRev);
            cur2 = cur2.tl();
        }
        
        return reverseList(resultRev);
    }

    private <T> FnList<T> reverseList(FnList<T> list) {
        FnList<T> rev = new FnList<>();
        while (list.consq()) {
            rev = new FnList<>(list.hd(), rev);
            list = list.tl();
        }
        return rev;
    }
}
////////////////////////////////////////////////////////////////////////.
//
// HX-2026-03-04:
//
// Please find a way to test someRevStableSort by
// implementing someSort as insertion-sort on FnList
// and then use someReStableSort to parity-sort the following
// list of 1K integers:
// 0, 1, 2, 3, 4, ..., 999
//
// Your testing code should be inside Quiz01_05_test.java!
//
// Note that you should not add a 'main' method into Quiz01_05
// directly; instead, try to create another class to test Quiz01_05
//
// Note that you should be able to call the insertion sort
// you did (Assign05_01); should not do another implementation of it
//
////////////////////////////////////////////////////////////////////////.