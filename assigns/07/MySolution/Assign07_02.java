import MyLibrary.LnStrm.*;
import MyLibrary.FnTuple.*;

public class Assign07_02 {
    private static long cubeSum(FnTupl2<Integer, Integer> xy) {
        return cubeSum(xy.sub0, xy.sub1);
    }

    private static long cubeSum(Integer x, Integer y) {
        long lx = x.longValue();
        long ly = y.longValue();
        return lx * lx * lx + ly * ly * ly;
    }

    private static LnStrm<Integer> intFrom(int start) {
        return new LnStrm<Integer>(
            () -> new LnStcn<Integer>(start, intFrom(start + 1))
        );
    }

    private static LnStrm<Integer> ramanujanNumbersFrom(LnStrm<FnTupl2<Integer, Integer>> pairs) {
        return new LnStrm<Integer>(
            () -> {
                LnStcn<FnTupl2<Integer, Integer>> cpairs = pairs.eval0();
                if (cpairs.nilq()) {
                    return new LnStcn<Integer>();
                }

                FnTupl2<Integer, Integer> first = cpairs.hd();
                long sum = cubeSum(first);
                LnStrm<FnTupl2<Integer, Integer>> rest = cpairs.tl();
                boolean repeated = false;

                while (true) {
                    LnStcn<FnTupl2<Integer, Integer>> crest = rest.eval0();
                    if (crest.nilq()) {
                        return repeated ? new LnStcn<Integer>((int) sum, new LnStrm<Integer>()) : new LnStcn<Integer>();
                    }

                    FnTupl2<Integer, Integer> next = crest.hd();
                    long nextSum = cubeSum(next);
                    if (nextSum == sum) {
                        repeated = true;
                        rest = crest.tl();
                    } else {
                        if (repeated) {
                            return new LnStcn<Integer>((int) sum, ramanujanNumbersFrom(new LnStrm<FnTupl2<Integer, Integer>>(() -> crest)));
                        } else {
                            first = next;
                            sum = nextSum;
                            rest = crest.tl();
                        }
                    }
                }
            }
        );
    }

    private static <T> void printFirstN(LnStrm<T> fxs, int n) {
        LnStrm<T> cur = fxs;
        int i = 0;
        while (i < n) {
            LnStcn<T> cxs = cur.eval0();
            if (cxs.nilq()) {
                break;
            }
            System.out.println(cxs.hd());
            cur = cxs.tl();
            i += 1;
        }
    }

    public static
    LnStrm<Integer>
    ramanujanNumbers() {
        return ramanujanNumbersFrom(cubeSumOrderedIntegerPairs());
    }

    public static
    LnStrm<FnTupl2<Integer, Integer>>
    cubeSumOrderedIntegerPairs() {
        return Assign07_01.mergeLnStrm(
            LnStrmSUtil.map0(
                intFrom(1),
                (x) -> LnStrmSUtil.map0(
                    intFrom(x),
                    (y) -> new FnTupl2<Integer, Integer>(x, y)
                )
            ),
            (p1, p2) -> Long.compare(cubeSum(p1), cubeSum(p2))
        );
    }

    public static void main(String[] args) {
        System.out.println("First 15 cube-sum ordered pairs:");
        printFirstN(cubeSumOrderedIntegerPairs(), 15);

        System.out.println("First 5 Ramanujan numbers:");
        printFirstN(ramanujanNumbers(), 5);
    }
}
