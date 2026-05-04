import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.LnStrm.*;
import MyLibrary.MyRefer.*;

public class Final_03 {
    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize3() {
        LnStrm<FnList<Character>> ws = Final_01.pg2701_word$strmize();
        Assign08_02<Integer> map = new Assign08_02<Integer>();
        while (true) {
            LnStcn<FnList<Character>> cell = ws.eval0();
            if (cell.nilq()) {
                break;
            }
            String word = Final_01.word2string(cell.hd());
            Integer old = map.search$opt(word);
            if (old == null) {
                map.insert$opt(word, 1);
            } else {
                map.insert$opt(word, old + 1);
            }
            ws = cell.tl();
        }

        MyRefer<FnList<FnTupl2<FnList<Character>, Integer>>> ref =
            new MyRefer<FnList<FnTupl2<FnList<Character>, Integer>>>(FnListSUtil.nil());
        map.foritm((word, count) -> {
            FnTupl2<FnList<Character>, Integer> pair =
                new FnTupl2<FnList<Character>, Integer>(Final_01.string2word(word), count);
            ref.set$raw(FnListSUtil.cons(pair, ref.get$raw()));
        });
        return FnListSUtil.mergeSort(ref.get$raw(), Final_03::pairCompare);
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
        FnList<FnTupl2<FnList<Character>, Integer>> xs = pg2701_word$count$listize3();
        int i = 0;
        while (i < 100 && xs.consq()) {
            System.out.println(Final_01.word2string(xs.hd().sub0) + " " + xs.hd().sub1);
            xs = xs.tl();
            i += 1;
        }
    }
}
