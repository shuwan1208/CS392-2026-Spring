//
// HX-2026-04-21: 50 points
//
// Please see lectures/lecture-04-21 for an
// example using DFirstEnumerate/BFirstEnumerate
//
// Some "hard" Sudoku puzzles can be
// found here: https://sudoku.com/hard/.
// You are asked to use DFirstEnumerate and BFirstEnumerate
// in FnGtree to solve Sudoku puzzles. Your solution should
// be able to solve "hard" Sudoku puzzles effectively.
//
import Library00.FnList.*;
import Library00.LnStrm.*;
import Library00.FnGtree.*;

class Sudoku implements FnGtree<Sudoku> {
    private final int[] cells;

    public Sudoku(String text) {
	int[] cells = new int[81];
	int j0 = 0;
	for (int i0 = 0; i0 < text.length() && j0 < 81; i0 += 1) {
	    char c0 = text.charAt(i0);
	    if (c0 >= '1' && c0 <= '9') {
		cells[j0] = c0 - '0';
		j0 += 1;
	    } else if (c0 == '0' || c0 == '.') {
		cells[j0] = 0;
		j0 += 1;
	    }
	}
	if (j0 != 81) throw new IllegalArgumentException();
	this.cells = normalize(cells);
    }

    private Sudoku(int[] cells) {
	this.cells = normalize(cells);
    }

    @Override
    public Sudoku value() {
	return this;
    }

    @Override
    public FnList<FnGtree<Sudoku>> children() {
	if (!validq() || solvedq()) return new FnList<FnGtree<Sudoku>>();
	int p0 = bestEmptyPos();
	if (p0 < 0) return new FnList<FnGtree<Sudoku>>();
	FnList<FnGtree<Sudoku>> res = new FnList<FnGtree<Sudoku>>();
	for (int d0 = 9; d0 >= 1; d0 -= 1) {
	    if (candidateq(p0, d0)) {
		int[] copy = cells.clone();
		copy[p0] = d0;
		res = new FnList<FnGtree<Sudoku>>(new Sudoku(copy), res);
	    }
	}
	return res;
    }

    public boolean solvedq() {
	if (!validq()) return false;
	for (int i0 = 0; i0 < 81; i0 += 1) {
	    if (cells[i0] == 0) return false;
	}
	return true;
    }

    public boolean validq() {
	for (int i0 = 0; i0 < 81; i0 += 1) {
	    int d0 = cells[i0];
	    if (d0 != 0 && !candidateqSkipSelf(i0, d0)) return false;
	}
	return true;
    }

    private int bestEmptyPos() {
	int p0 = -1;
	int n0 = 10;
	for (int i0 = 0; i0 < 81; i0 += 1) {
	    if (cells[i0] != 0) continue;
	    int c0 = candidateCount(i0);
	    if (c0 == 0) return i0;
	    if (c0 < n0) {
		n0 = c0;
		p0 = i0;
		if (n0 == 1) break;
	    }
	}
	return p0;
    }

    private static int[] normalize(int[] cells) {
	int[] copy = cells.clone();
	while (true) {
	    boolean changed = false;
	    for (int p0 = 0; p0 < 81; p0 += 1) {
		if (copy[p0] != 0) continue;
		int d1 = onlyCandidate(copy, p0);
		if (d1 != 0) {
		    copy[p0] = d1;
		    changed = true;
		}
	    }
	    for (int r0 = 0; r0 < 9; r0 += 1) {
		for (int d0 = 1; d0 <= 9; d0 += 1) {
		    int p0 = uniquePosInRow(copy, r0, d0);
		    if (p0 >= 0) {
			copy[p0] = d0;
			changed = true;
		    }
		}
	    }
	    for (int c0 = 0; c0 < 9; c0 += 1) {
		for (int d0 = 1; d0 <= 9; d0 += 1) {
		    int p0 = uniquePosInCol(copy, c0, d0);
		    if (p0 >= 0) {
			copy[p0] = d0;
			changed = true;
		    }
		}
	    }
	    for (int b0 = 0; b0 < 9; b0 += 1) {
		for (int d0 = 1; d0 <= 9; d0 += 1) {
		    int p0 = uniquePosInBox(copy, b0, d0);
		    if (p0 >= 0) {
			copy[p0] = d0;
			changed = true;
		    }
		}
	    }
	    if (!changed) break;
	}
	return copy;
    }

    private int candidateCount(int p0) {
	int n0 = 0;
	for (int d0 = 1; d0 <= 9; d0 += 1) {
	    if (candidateq(p0, d0)) n0 += 1;
	}
	return n0;
    }

    private boolean candidateq(int p0, int d0) {
	if (cells[p0] != 0) return false;
	return candidateqSkipSelf(p0, d0);
    }

    private boolean candidateqSkipSelf(int p0, int d0) {
	int r0 = p0 / 9;
	int c0 = p0 % 9;
	for (int i0 = 0; i0 < 9; i0 += 1) {
	    int p1 = 9 * r0 + i0;
	    if (p1 != p0 && cells[p1] == d0) return false;
	}
	for (int i0 = 0; i0 < 9; i0 += 1) {
	    int p1 = 9 * i0 + c0;
	    if (p1 != p0 && cells[p1] == d0) return false;
	}
	for (int i0 = 3 * (r0 / 3); i0 < 3 * (r0 / 3) + 3; i0 += 1) {
	    for (int j0 = 3 * (c0 / 3); j0 < 3 * (c0 / 3) + 3; j0 += 1) {
		int p1 = 9 * i0 + j0;
		if (p1 != p0 && cells[p1] == d0) return false;
	    }
	}
	return true;
    }

    private static int onlyCandidate(int[] cells, int p0) {
	int d1 = 0;
	for (int d0 = 1; d0 <= 9; d0 += 1) {
	    if (candidateq(cells, p0, d0)) {
		if (d1 != 0) return 0;
		d1 = d0;
	    }
	}
	return d1;
    }

    private static int uniquePosInRow(int[] cells, int r0, int d0) {
	int p0 = -1;
	for (int c0 = 0; c0 < 9; c0 += 1) {
	    int p1 = 9 * r0 + c0;
	    if (!candidateq(cells, p1, d0)) continue;
	    if (p0 >= 0) return -2;
	    p0 = p1;
	}
	return p0;
    }

    private static int uniquePosInCol(int[] cells, int c0, int d0) {
	int p0 = -1;
	for (int r0 = 0; r0 < 9; r0 += 1) {
	    int p1 = 9 * r0 + c0;
	    if (!candidateq(cells, p1, d0)) continue;
	    if (p0 >= 0) return -2;
	    p0 = p1;
	}
	return p0;
    }

    private static int uniquePosInBox(int[] cells, int b0, int d0) {
	int r1 = 3 * (b0 / 3);
	int c1 = 3 * (b0 % 3);
	int p0 = -1;
	for (int i0 = r1; i0 < r1 + 3; i0 += 1) {
	    for (int j0 = c1; j0 < c1 + 3; j0 += 1) {
		int p1 = 9 * i0 + j0;
		if (!candidateq(cells, p1, d0)) continue;
		if (p0 >= 0) return -2;
		p0 = p1;
	    }
	}
	return p0;
    }

    private static boolean candidateq(int[] cells, int p0, int d0) {
	if (cells[p0] != 0) return false;
	int r0 = p0 / 9;
	int c0 = p0 % 9;
	for (int i0 = 0; i0 < 9; i0 += 1) {
	    int p1 = 9 * r0 + i0;
	    if (p1 != p0 && cells[p1] == d0) return false;
	}
	for (int i0 = 0; i0 < 9; i0 += 1) {
	    int p1 = 9 * i0 + c0;
	    if (p1 != p0 && cells[p1] == d0) return false;
	}
	for (int i0 = 3 * (r0 / 3); i0 < 3 * (r0 / 3) + 3; i0 += 1) {
	    for (int j0 = 3 * (c0 / 3); j0 < 3 * (c0 / 3) + 3; j0 += 1) {
		int p1 = 9 * i0 + j0;
		if (p1 != p0 && cells[p1] == d0) return false;
	    }
	}
	return true;
    }

    public void print() {
	for (int r0 = 0; r0 < 9; r0 += 1) {
	    for (int c0 = 0; c0 < 9; c0 += 1) {
		int d0 = cells[9 * r0 + c0];
		System.out.print(d0 == 0 ? ". " : Integer.toString(d0) + " ");
	    }
	    System.out.println();
	}
    }
}

public class Assign11_01 {
    private static void printPuzzleText(String text) {
	int j0 = 0;
	for (int i0 = 0; i0 < text.length() && j0 < 81; i0 += 1) {
	    char c0 = text.charAt(i0);
	    if (c0 >= '1' && c0 <= '9') {
		System.out.print(c0 + " ");
		j0 += 1;
	    } else if (c0 == '0' || c0 == '.') {
		System.out.print(". ");
		j0 += 1;
	    } else {
		continue;
	    }
	    if (j0 % 9 == 0) System.out.println();
	}
    }

    public LnStrm<Sudoku> Soduku_dfs_solve(Sudoku puzzle) {
	return FnGtreeSUtil.DFirstEnumerate(puzzle).filter0((bd) -> bd.solvedq());
    }
    public LnStrm<Sudoku> Soduku_bfs_solve(Sudoku puzzle) {
	return FnGtreeSUtil.BFirstEnumerate(puzzle).filter0((bd) -> bd.solvedq());
    }

    private static Sudoku firstSolution(LnStrm<Sudoku> sols) {
	LnStcn<Sudoku> cxs = sols.eval0();
	if (cxs.nilq()) return null;
	return cxs.hd();
    }
//
    public static void main (String[] args) {
	Assign11_01 solver = new Assign11_01();
	String text =
	    "034678912"
	    + "672195348"
	    + "198342567"
	    + "859761423"
	    + "426853791"
	    + "713924856"
	    + "961537284"
	    + "287419635"
	    + "345286170";
	Sudoku puzzle = new Sudoku(text);
	Sudoku dfs = firstSolution(solver.Soduku_dfs_solve(puzzle));
	Sudoku bfs = firstSolution(solver.Soduku_bfs_solve(puzzle));
	System.out.println("Puzzle:");
	printPuzzleText(text);
	// Please add minimal testing code for Sudoku_dfs_solve
	if (dfs != null) {
	    System.out.println("DFS:");
	    dfs.print();
	} else {
	    System.out.println("DFS: no solution");
	}
	// Please add minimal testing code for Sudoku_bfs_solve
	if (bfs != null) {
	    System.out.println("BFS:");
	    bfs.print();
	} else {
	    System.out.println("BFS: no solution");
	}
	return /*void*/;
    }
//
}
