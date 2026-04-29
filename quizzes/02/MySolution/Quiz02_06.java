//
// HX-2026-04-28: 50 points
// A partial implementation of
// randomized doubly linked binary search tree
// 30 points for reroot and 20 points for insert
public class Quiz02_06 {
    Node root = null;
    private long randState = 39220260428L;
    public class Node {
	int key; // key stored in the node
	int size; // size of the tree rooted as the node
	Node parent; // parent of the node
	Node lchild; // left-child of the node
	Node rchild; // right-child of the node

	Node(int key, Node parent) {
	    this.key = key;
	    this.size = 1;
	    this.parent = parent;
	    this.lchild = null;
	    this.rchild = null;
	}
    }

    private int nextRandomInt(int bound) {
	randState = randState * 1103515245L + 12345L;
	long nonneg = randState & 0x7fffffffL;
	return (int)(nonneg % bound);
    }

    private static int sizeOf(Node node) {
	return node == null ? 0 : node.size;
    }

    private void refreshSize(Node node) {
	if (node != null) {
	    node.size = 1 + sizeOf(node.lchild) + sizeOf(node.rchild);
	}
    }

    private void refreshSizesUpward(Node node) {
	while (node != null) {
	    refreshSize(node);
	    node = node.parent;
	}
    }

    private void rotateLeft(Node x) {
	Node y = x.rchild;
	Node p = x.parent;
	x.rchild = y.lchild;
	if (y.lchild != null) y.lchild.parent = x;
	y.lchild = x;
	x.parent = y;
	y.parent = p;
	if (p == null) {
	    root = y;
	} else if (p.lchild == x) {
	    p.lchild = y;
	} else {
	    p.rchild = y;
	}
	refreshSize(x);
	refreshSize(y);
	refreshSizesUpward(p);
    }

    private void rotateRight(Node x) {
	Node y = x.lchild;
	Node p = x.parent;
	x.lchild = y.rchild;
	if (y.rchild != null) y.rchild.parent = x;
	y.rchild = x;
	x.parent = y;
	y.parent = p;
	if (p == null) {
	    root = y;
	} else if (p.lchild == x) {
	    p.lchild = y;
	} else {
	    p.rchild = y;
	}
	refreshSize(x);
	refreshSize(y);
	refreshSizesUpward(p);
    }

    private Node selectByRank(Node node, int rank) {
	int ls = sizeOf(node.lchild);
	if (rank < ls) return selectByRank(node.lchild, rank);
	if (rank == ls) return node;
	return selectByRank(node.rchild, rank - ls - 1);
    }

    private void inorderPrint(Node node) {
	if (node == null) return;
	inorderPrint(node.lchild);
	System.out.print(node.key + "(" + node.size + ") ");
	inorderPrint(node.rchild);
    }

    public void reroot() {
	if (root == null) return;
	Node pick = selectByRank(root, nextRandomInt(root.size));
	while (pick.parent != null) {
	    if (pick.parent.lchild == pick) {
		rotateRight(pick.parent);
	    } else {
		rotateLeft(pick.parent);
	    }
	}
    }
    public boolean insert(int key) {
	if (root == null) {
	    root = new Node(key, null);
	    return true;
	}
	Node cur = root;
	Node par = null;
	while (cur != null) {
	    par = cur;
	    if (key == cur.key) return false;
	    if (key < cur.key) {
		cur = cur.lchild;
	    } else {
		cur = cur.rchild;
	    }
	}
	Node node = new Node(key, par);
	if (key < par.key) {
	    par.lchild = node;
	} else {
	    par.rchild = node;
	}
	refreshSizesUpward(par);
	return true;
    }
    public static void main (String[] args) {
	Quiz02_06 tree = new Quiz02_06();
	System.out.println(tree.insert(50));
	System.out.println(tree.insert(30));
	System.out.println(tree.insert(70));
	System.out.println(tree.insert(20));
	System.out.println(tree.insert(40));
	System.out.println(tree.insert(60));
	System.out.println(tree.insert(80));
	System.out.println(tree.insert(70));
	tree.inorderPrint(tree.root);
	System.out.println();
	tree.reroot();
	tree.inorderPrint(tree.root);
	System.out.println();
	System.out.println(tree.root.key);
	System.out.println(tree.root.size);
	return /*void*/;
    }
}
