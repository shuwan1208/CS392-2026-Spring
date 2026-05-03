import java.util.ArrayList;
import java.util.List;

//
// HX-2026-04-28: 30 points
// (plus up to 20 bonus points)
// This is more of a theory problem
// than a programming one.
public class Quiz02_04 {
    public static class AVLnode {
	int key;
	AVLnode lchild;
	AVLnode rchild;
    }

    private static final int TARGET_SIZE = 1000000;
    private static final long[] MIN_NODES = makeMinNodes(64);
    private static final long[] MAX_NODES = makeMaxNodes(64);
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
	int h0 = maximalAVLHeight(TARGET_SIZE);
	BuildState state = new BuildState();
	state.nextKey = 0;
	AVLnode root = buildAVLWithHeightAndSize(h0, TARGET_SIZE, state);
	List<Integer> keys = new ArrayList<Integer>();
	inorderCollect(root, keys);
	System.out.println("maximal AVL height for 1,000,000 keys = " + h0);
	System.out.println("generated AVL node count = " + keys.size());
	return
	    h0 == 28
	    && state.nextKey == TARGET_SIZE
	    && keys.size() == TARGET_SIZE
	    && isStrictlyIncreasing(keys)
	    && isAVL(root)
	    && avlHeightIfValid(root) == h0;
    }

    private static class BuildState {
	int nextKey;
    }

    private static long[] makeMinNodes(int len) {
	long[] mins = new long[len];
	mins[0] = 0L;
	if (len > 1) mins[1] = 1L;
	for (int i0 = 2; i0 < len; i0 += 1) {
	    mins[i0] = 1L + mins[i0 - 1] + mins[i0 - 2];
	}
	return mins;
    }

    private static long[] makeMaxNodes(int len) {
	long[] maxs = new long[len];
	maxs[0] = 0L;
	for (int i0 = 1; i0 < len; i0 += 1) {
	    maxs[i0] = 1L + maxs[i0 - 1] + maxs[i0 - 1];
	}
	return maxs;
    }

    private static int maximalAVLHeight(long n0) {
	int h0 = 0;
	while (h0 + 1 < MIN_NODES.length && MIN_NODES[h0 + 1] <= n0) {
	    h0 += 1;
	}
	return h0;
    }

    private static AVLnode buildAVLWithHeightAndSize(int h0, long n0, BuildState state) {
	if (h0 == 0) return null;
	if (h0 == 1) {
	    if (n0 != 1L) throw new IllegalArgumentException("infeasible AVL leaf");
	    AVLnode leaf = new AVLnode();
	    leaf.key = state.nextKey;
	    state.nextKey += 1;
	    return leaf;
	}
	if (n0 < MIN_NODES[h0] || n0 > MAX_NODES[h0]) {
	    throw new IllegalArgumentException("infeasible AVL request");
	}
	int[][] options = new int[][]{
	    new int[]{h0 - 1, h0 - 2},
	    new int[]{h0 - 2, h0 - 1},
	    new int[]{h0 - 1, h0 - 1}
	};
	for (int i0 = 0; i0 < options.length; i0 += 1) {
	    int hl = options[i0][0];
	    int hr = options[i0][1];
	    long rest = n0 - 1L;
	    long low = Math.max(MIN_NODES[hl], rest - MAX_NODES[hr]);
	    long high = Math.min(MAX_NODES[hl], rest - MIN_NODES[hr]);
	    if (low <= high) {
		long nl = low;
		long nr = rest - nl;
		AVLnode node = new AVLnode();
		node.lchild = buildAVLWithHeightAndSize(hl, nl, state);
		node.key = state.nextKey;
		state.nextKey += 1;
		node.rchild = buildAVLWithHeightAndSize(hr, nr, state);
		return node;
	    }
	}
	throw new IllegalStateException("unable to split AVL subtree");
    }

    private static void inorderCollect(AVLnode node, List<Integer> keys) {
	if (node == null) return;
	inorderCollect(node.lchild, keys);
	keys.add(node.key);
	inorderCollect(node.rchild, keys);
    }

    private static boolean isStrictlyIncreasing(List<Integer> keys) {
	for (int i0 = 1; i0 < keys.size(); i0 += 1) {
	    if (keys.get(i0 - 1) >= keys.get(i0)) return false;
	}
	return true;
    }
    public static void main (String[] args) {
	AVLnode root = new AVLnode();
	root.lchild = new AVLnode();
	root.rchild = new AVLnode();
	root.lchild.lchild = new AVLnode();
	System.out.println(isAVL(root));
	root.lchild.lchild.lchild = new AVLnode();
	System.out.println(isAVL(root));
	System.out.println(genAVLBST());
	return /*void*/;
    }
}
