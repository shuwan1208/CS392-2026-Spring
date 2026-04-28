//
// HX-2026-04-28: 30 points
// (plus up to 20 bonus points)
// This is more of a theory problem
// than a programming one.
//
import MyLibrary.LnStrm.*;
//
public class Quiz02_04 {
    public class AVLnode {
	int key;
	AVLnode lchild;
	AVLnode rchild;
    }
    //
    // HX: 10 points for this one
    // HX: If your implementation only
    // visit each node in [avl] at most once,
    // then it will be rewarded with some bonus
    // points (up to 20 bonus points).
    // For instance, if you compute the size of
    // height of a tree, then you have already
    // visited each node once.
    //
    public static boolean isAVL (AVLnode avl) {
	return avlHeightIfValid(avl) >= 0;
    }

    private static int avlHeightIfValid(AVLnode avl) {
	if (avl == null) return 0;
	int hl = avlHeightIfValid(avl.lchild);
	if (hl < 0) return -1;
	int hr = avlHeightIfValid(avl.rchild);
	if (hr < 0) return -1;
	if (Math.abs(hl - hr) > 1) return -1;
	return 1 + (hl >= hr ? hl : hr);
    }
    //
    // HX: 20 points
    // This is largely about understanding AVL trees.
    // Please explain BRIEFLY as to why the generated AVL is
    // of maximal height (not minimal height). Note that this
    // is different from what is asked in Quiz02_05.
    //
    public static boolean genAVLBST() {
	// For AVL trees of height h (counting an empty tree as height 0
	// and a leaf as height 1), the fewest nodes needed is:
	// S(0)=0, S(1)=1, S(h)=1+S(h-1)+S(h-2).
	// Therefore the maximal possible height for 1,000,000 keys is
	// the largest h with S(h) <= 1,000,000.
	long s0 = 0L;
	long s1 = 1L;
	int h0 = 1;
	while (true) {
	    long s2 = 1L + s1 + s0;
	    if (s2 > 1000000L) break;
	    s0 = s1;
	    s1 = s2;
	    h0 += 1;
	}
	System.out.println("maximal AVL height for 1,000,000 keys = " + h0);
	return h0 == 28;
    }
    public static void main (String[] args) {
	Quiz02_04 q = new Quiz02_04();
	AVLnode root = q.new AVLnode();
	root.lchild = q.new AVLnode();
	root.rchild = q.new AVLnode();
	root.lchild.lchild = q.new AVLnode();
	System.out.println(isAVL(root));
	root.lchild.lchild.lchild = q.new AVLnode();
	System.out.println(isAVL(root));
	System.out.println(genAVLBST());
	return /*void*/;
    }
}
