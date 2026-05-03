import MyLibrary.LnStrm.*;

import java.util.function.ToIntBiFunction;

public class Assign07_01 {
    public static <T>
    LnStrm<T> mergeLnStrm(LnStrm<LnStrm<T>> fxss, ToIntBiFunction<T, T> cmpr) {
        return new LnStrm<T>(
            () -> {
                LnStcn<LnStrm<T>> cxss = fxss.eval0();
                while (cxss.consq()) {
                    LnStrm<T> fxs = cxss.hd();
                    LnStcn<T> cxs = fxs.eval0();
                    if (cxs.consq()) {
                        return new LnStcn<T>(
                            cxs.hd(),
                            LnStrmSUtil.m2erge0(cxs.tl(), mergeLnStrm(cxss.tl(), cmpr), cmpr)
                        );
                    }
                    cxss = cxss.tl().eval0();
                }
                return new LnStcn<T>();
            }
        );
    }
}
