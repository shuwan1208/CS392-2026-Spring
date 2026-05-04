import MyLibrary.FnA1sz.*;
import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.LnStrm.*;

public class Final_02 {
    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize2() {
        LnStrm<FnList<Character>> ws = Final_01.pg2701_word$strmize();
        FnList<FnList<Character>> revWords = FnListSUtil.nil();
        int n = 0;
        while (true) {
            LnStcn<FnList<Character>> cell = ws.eval0();
            if (cell.nilq()) {
                break;
            }
            revWords = FnListSUtil.cons(cell.hd(), revWords);
            ws = cell.tl();
            n += 1;
        }

        FnList<Character>[] words = (FnList<Character>[]) new FnList[n];
        FnList<FnList<Character>> xs = FnListSUtil.reverse(revWords);
        for (int i = 0; i < n; i += 1) {
            words[i] = xs.hd();
            xs = xs.tl();
        }

        FnA1sz<FnList<Character>> a1 = new FnA1sz<FnList<Character>>(words);
        FnA1sz<FnList<Character>> sorted = a1.quickSort(Final_01::wordCompare);

        FnList<FnTupl2<FnList<Character>, Integer>> revPairs = FnListSUtil.nil();
        int i = 0;
        while (i < n) {
            FnList<Character> word = sorted.getAt(i);
            int count = 1;
            i += 1;
            while (i < n && Final_01.wordCompare(word, sorted.getAt(i)) == 0) {
                count += 1;
                i += 1;
            }
            revPairs = FnListSUtil.cons(new FnTupl2<FnList<Character>, Integer>(word, count), revPairs);
        }

        return FnListSUtil.mergeSort(FnListSUtil.reverse(revPairs), Final_02::pairCompare);
    }

    private static int pairCompare(FnTupl2<FnList<Character>, Integer> p1, FnTupl2<FnList<Character>, Integer> p2) {
        int c1 = p1.sub1;
        int c2 = p2.sub1;
        if (c1 != c2) {
            return c2 - c1;
        }
        return Final_01.wordCompare(p1.sub0, p2.sub0);
    }

    public static void main(String[] args) {
        FnList<FnTupl2<FnList<Character>, Integer>> xs = pg2701_word$count$listize2();
        int i = 0;
        while (i < 100 && xs.consq()) {
            System.out.println(Final_01.word2string(xs.hd().sub0) + " " + xs.hd().sub1);
            xs = xs.tl();
            i += 1;
        }
    }
}
