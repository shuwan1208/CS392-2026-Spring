package MyLibrary.LnStrm;

import MyLibrary.FnList.*;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;

public class LnStrmSUtil {
    public static<T>
	LnStcn<T> eval0(LnStrm<T> fxs) {
	return fxs.eval0();
    }

    public static<T>
	LnStrm<T> cons0(T x0, LnStrm<T> fxs) {
	return new LnStrm<T>(() -> new LnStcn<T>(x0, fxs));
    }

    public static<T>
	LnStrm<T> append0(LnStrm<T> fxs, LnStrm<T> fys) {
	return new LnStrm<T>(
	  () -> {
	      final LnStcn<T> cxs = fxs.eval0();
	      if (cxs.nilq()) {
		  return fys.eval0();
	      } else {
		  return new LnStcn<T>(cxs.hd(), append0(cxs.tl(), fys));
	      }
	  }
	);
    }

    public static<T>
	void foritm0(LnStrm<T> fxs, Consumer<? super T> work) {
	LnStcn<T> cxs = fxs.eval0();
	while (cxs.consq()) {
	    work.accept(cxs.hd());
	    cxs = cxs.tl().eval0();
	}
    }

    public static<T>
	boolean forall0(LnStrm<T> fxs, Predicate<? super T> pred) {
	LnStcn<T> cxs = fxs.eval0();
	while (cxs.consq()) {
	    if (!pred.test(cxs.hd())) return false;
	    cxs = cxs.tl().eval0();
	}
	return true;
    }

    public static<T,R>
	LnStrm<R> map0(LnStrm<T> fxs, Function<? super T, R> fopr) {
	return new LnStrm<R>(
	  () -> {
	      LnStcn<T> cxs = fxs.eval0();
	      if (cxs.nilq()) {
		  return new LnStcn<R>();
	      } else {
		  return new LnStcn<R>(fopr.apply(cxs.hd()), map0(cxs.tl(), fopr));
	      }
	  }
	);
    }

    public static<T>
	LnStrm<T> filter0(LnStrm<T> fxs, Predicate<? super T> pred) {
	return new LnStrm<T>(
	  () -> {
	      LnStcn<T> cxs = fxs.eval0();
	      while (cxs.consq()) {
		  final T hd = cxs.hd();
		  final LnStrm<T> tl = cxs.tl();
		  if (pred.test(hd)) {
		      return new LnStcn<T>(hd, filter0(tl, pred));
		  }
		  cxs = tl.eval0();
	      }
	      return new LnStcn<T>();
	  }
	);
    }

    public static<T>
	LnStrm<T>
	m2erge0
	(LnStrm<T> fxs, LnStrm<T> fys, ToIntBiFunction<T,T> cmpr) {
	return new LnStrm<T>(
	  () -> {
	      LnStcn<T> cxs = fxs.eval0();
	      if (cxs.nilq()) return fys.eval0();
	      LnStcn<T> cys = fys.eval0();
	      if (cys.nilq()) return cxs;
	      T x0 = cxs.hd();
	      T y0 = cys.hd();
	      if (cmpr.applyAsInt(x0, y0) <= 0) {
		  return new LnStcn<T>(x0, m2erge0(cxs.tl(), fys, cmpr));
	      } else {
		  return new LnStcn<T>(y0, m2erge0(fxs, cys.tl(), cmpr));
	      }
	  }
	);
    }

    public static<T>
	FnList<T> toFnList0(LnStrm<T> fxs) {
	return FnListSUtil.fwork$make((work) -> fxs.foritm0(work));
    }
}
