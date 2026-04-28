//
// HX-2026-04-28: 30 points
// (plus up to 20 bonus points)
// This is more of a theory problem
// than a programming one.
//
public class Quiz02_05 {
    public class RBTnode {
	int key;
	int color; // Red = 0; Black = 1
	RBTnode lchild;
	RBTnode rchild;
    }
    //
    // HX: 10 points for this one
    // HX: If your implementation only
    // visit each node in [rbt] at most once,
    // then it will be rewarded with some bonus
    // points (up to 20 bonus points).
    // For instance, if you compute the size of
    // height of a tree, then you already visit
    // each node once.
    //
    public static boolean isRBT (RBTnode rbt) {
	if (rbt == null) return true;
	if (rbt.color != 1) return false;
	return blackHeightIfValid(rbt) >= 0;
    }

    private static int blackHeightIfValid(RBTnode rbt) {
	if (rbt == null) return 1;
	if (rbt.color != 0 && rbt.color != 1) return -1;
	if (rbt.color == 0) {
	    if ((rbt.lchild != null && rbt.lchild.color == 0)
		|| (rbt.rchild != null && rbt.rchild.color == 0)) {
		return -1;
	    }
	}
	int bl = blackHeightIfValid(rbt.lchild);
	if (bl < 0) return -1;
	int br = blackHeightIfValid(rbt.rchild);
	if (br < 0 || bl != br) return -1;
	return bl + (rbt.color == 1 ? 1 : 0);
    }
    //
    // HX: 20 points
    // This is largely about understanding red-black trees.
    // Please explain BRIEFLY as to why the generated RBT is
    // of minimal black height (not height).
    //
    public static boolean genRedBLackBST() {
	// A red-black tree of black-height b has height at most 2b,
	// so it has at most 2^(2b)-1 internal nodes.
	// Hence for n=1,000,000, the minimal possible black-height is
	// the least b with 1,000,000 <= 2^(2b)-1.
	int bh = 0;
	long maxNodes = 0L;
	while (maxNodes < 1000000L) {
	    bh += 1;
	    maxNodes = (1L << (2 * bh)) - 1L;
	}
	System.out.println("minimal red-black black-height for 1,000,000 keys = " + bh);
	return bh == 10;
    }
    public static void main (String[] args) {
	Quiz02_05 q = new Quiz02_05();
	RBTnode root = q.new RBTnode();
	root.color = 1;
	root.lchild = q.new RBTnode();
	root.lchild.color = 0;
	root.rchild = q.new RBTnode();
	root.rchild.color = 0;
	System.out.println(isRBT(root));
	root.lchild.lchild = q.new RBTnode();
	root.lchild.lchild.color = 0;
	System.out.println(isRBT(root));
	System.out.println(genRedBLackBST());
	return /*void*/;
    }
}
