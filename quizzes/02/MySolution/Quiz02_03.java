//
// HX-2026-04-28: 50 points
//
/*
A description on Game-of-24 and an accompanying
demo can be found by visiting the following link:
https://github.com/githwxi/XATSHOME/tree/main/contrib/githwxi/pground/proj002%40250507/misc004
Please give a high-level description in English as to
how Game-of-24 can be solved using either DFS or BFS.
Your description should be given in a README file for
this assignment.
1. Please give a DFS-based implementation according to your
   description that should directly use the DFirstEnumerate method.
2. Please give a BFS-based implementation according to your
   description that should directly use the BFirstEnumerate method.
*/
//
import MyLibrary.LnStrm.*;
import MyLibrary.LnStrm.LnStrmSUtil;
import MyLibrary.FnList.*;
import MyLibrary.FnGtree.*;
import MyLibrary.FnGtree.FnGtreeSUtil;

class UnsupportedOpr
    extends RuntimeException {
    String opr;
    public UnsupportedOpr(String opr) {
	this.opr = opr;
    }
}

abstract class Term {
    public String tag = "Term";
    public abstract double eval();
    // eval() returns the value of the term
}

class TermInt extends Term {
    public int val;
    public TermInt(int val) {
	this.tag = "TermInt"; this.val = val;
    }
    public double eval() { return val; }
}

class TermOpr extends Term {
    public String opr;
    public Term arg1, arg2;
    public TermOpr(String opr0, Term arg1, Term arg2) {
	this.tag = "TermOpr";
	this.opr = opr0; this.arg1 = arg1; this.arg2 = arg2;
    }
    public double eval() {
	switch (opr) {
	  case "+":
	      return arg1.eval() + arg2.eval();
	  case "-":
	      return arg1.eval() - arg2.eval();
	  case "*":
	      return arg1.eval() * arg2.eval();
	  case "/":
	      return arg1.eval() / arg2.eval();
	}
	throw new UnsupportedOpr(     opr     );
    }
}

public class Quiz02_03 {
    private static final double EPSILON = 1E-9;

    private static class SearchState implements FnGtree<SearchState> {
	private final FnList<Term> terms;

	SearchState(FnList<Term> terms) {
	    this.terms = terms;
	}

	public SearchState value() {
	    return this;
	}

	public FnList<FnGtree<SearchState>> children() {
	    Term[] A = toTermArray(terms);
	    int n0 = A.length;
	    FnList<FnGtree<SearchState>> res = new FnList<FnGtree<SearchState>>();
	    if (n0 <= 1) return res;
	    for (int i0 = n0 - 1; i0 >= 0; i0 -= 1) {
		for (int j0 = n0 - 1; j0 > i0; j0 -= 1) {
		    res = prependChild(res, A, i0, j0, new TermOpr("+", A[i0], A[j0]));
		    res = prependChild(res, A, i0, j0, new TermOpr("*", A[i0], A[j0]));
		    res = prependChild(res, A, i0, j0, new TermOpr("-", A[i0], A[j0]));
		    res = prependChild(res, A, i0, j0, new TermOpr("-", A[j0], A[i0]));
		    if (Math.abs(A[j0].eval()) > EPSILON) {
			res = prependChild(res, A, i0, j0, new TermOpr("/", A[i0], A[j0]));
		    }
		    if (Math.abs(A[i0].eval()) > EPSILON) {
			res = prependChild(res, A, i0, j0, new TermOpr("/", A[j0], A[i0]));
		    }
		}
	    }
	    return res;
	}

	boolean solvedq() {
	    return terms.consq()
		&& terms.tl().nilq()
		&& Math.abs(terms.hd().eval() - 24.0) < EPSILON;
	}

	Term solvedTerm() {
	    return terms.hd();
	}
    }

    private static FnList<FnGtree<SearchState>>
    prependChild(FnList<FnGtree<SearchState>> res, Term[] A, int i0, int j0, Term t0) {
	return new FnList<FnGtree<SearchState>>(
	    new SearchState(makeNextTerms(A, i0, j0, t0)),
	    res
	);
    }

    private static FnList<Term>
    makeNextTerms(Term[] A, int i0, int j0, Term t0) {
	FnList<Term> res = new FnList<Term>();
	for (int k0 = A.length - 1; k0 >= 0; k0 -= 1) {
	    if (k0 != i0 && k0 != j0) {
		res = new FnList<Term>(A[k0], res);
	    }
	}
	return new FnList<Term>(t0, res);
    }

    private static Term[]
    toTermArray(FnList<Term> xs) {
	int n0 = xs.length();
	Term[] A = new Term[n0];
	int i0 = 0;
	while (xs.consq()) {
	    A[i0] = xs.hd();
	    i0 += 1;
	    xs = xs.tl();
	}
	return A;
    }

    private static SearchState
    initialState(int n1, int n2, int n3, int n4) {
	return new SearchState(
	    new FnList<Term>(
		new TermInt(n1),
		new FnList<Term>(
		    new TermInt(n2),
		    new FnList<Term>(
			new TermInt(n3),
			new FnList<Term>(new TermInt(n4), new FnList<Term>())
		    )
		)
	    )
	);
    }

    private static LnStrm<Term>
    extractSolutions(LnStrm<SearchState> states) {
	return LnStrmSUtil.map0(
	    LnStrmSUtil.filter0(states, (st) -> st.solvedq()),
	    (st) -> st.solvedTerm()
	);
    }

    private static Term
    firstSolution(LnStrm<Term> sols) {
	LnStcn<Term> cxs = sols.eval0();
	return cxs.nilq() ? null : cxs.hd();
    }

//
    public LnStrm<Term> GameOf24_bfs_solve
	(int n1, int n2, int n3, int n4) {
	return extractSolutions(
	    FnGtreeSUtil.BFirstEnumerate(initialState(n1, n2, n3, n4))
	);
    }
//
    public LnStrm<Term> GameOf24_dfs_solve
	(int n1, int n2, int n3, int n4) {
	return extractSolutions(
	    FnGtreeSUtil.DFirstEnumerate(initialState(n1, n2, n3, n4))
	);
    }
//
    // Please add minimal testing code for GameOf24_bfs_solve
    // Please add minimal testing code for GameOf24_dfs_solve
//
    public static void main(String[] args) {
	Quiz02_03 solver = new Quiz02_03();
	Term bfs = firstSolution(solver.GameOf24_bfs_solve(3, 3, 8, 8));
	Term dfs = firstSolution(solver.GameOf24_dfs_solve(3, 3, 8, 8));
	System.out.println(bfs == null ? "no bfs solution" : bfs.eval());
	System.out.println(dfs == null ? "no dfs solution" : dfs.eval());
    }
} // end of [public class Quiz02_03{...}]
