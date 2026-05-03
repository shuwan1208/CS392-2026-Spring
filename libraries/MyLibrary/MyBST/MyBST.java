package MyLibrary.MyBST;

public class MyBST<T extends Comparable<? super T>> {
    private Node root;
    private int size;

    public class Node {
	public T key;
	public Node parent;
	public Node lchild;
	public Node rchild;

	Node(T key, Node parent) {
	    this.key = key;
	    this.parent = parent;
	    this.lchild = null;
	    this.rchild = null;
	}
    }

    public int size() {
	return size;
    }

    public boolean isEmpty() {
	return size <= 0;
    }

    public Node root() {
	return root;
    }

    public Node searchNode(T key) {
	Node cur = root;
	while (cur != null) {
	    int sgn = key.compareTo(cur.key);
	    if (sgn == 0) return cur;
	    cur = (sgn < 0 ? cur.lchild : cur.rchild);
	}
	return null;
    }

    public boolean search(T key) {
	return searchNode(key) != null;
    }

    public boolean insert(T key) {
	if (root == null) {
	    root = new Node(key, null);
	    size = 1;
	    return true;
	}
	Node cur = root;
	Node par = null;
	int sgn = 0;
	while (cur != null) {
	    par = cur;
	    sgn = key.compareTo(cur.key);
	    if (sgn == 0) return false;
	    cur = (sgn < 0 ? cur.lchild : cur.rchild);
	}
	Node node = new Node(key, par);
	if (sgn < 0) par.lchild = node; else par.rchild = node;
	size += 1;
	return true;
    }

    public boolean remove(T key) {
	Node node = searchNode(key);
	if (node == null) return false;
	deleteNode(node);
	size -= 1;
	return true;
    }

    public T min$opt() {
	Node node = minNode(root);
	return node == null ? null : node.key;
    }

    public T max$opt() {
	Node node = maxNode(root);
	return node == null ? null : node.key;
    }

    private Node minNode(Node node) {
	if (node == null) return null;
	while (node.lchild != null) node = node.lchild;
	return node;
    }

    private Node maxNode(Node node) {
	if (node == null) return null;
	while (node.rchild != null) node = node.rchild;
	return node;
    }

    private void transplant(Node oldn, Node newn) {
	if (oldn.parent == null) {
	    root = newn;
	} else if (oldn.parent.lchild == oldn) {
	    oldn.parent.lchild = newn;
	} else {
	    oldn.parent.rchild = newn;
	}
	if (newn != null) newn.parent = oldn.parent;
    }

    private void deleteNode(Node node) {
	if (node.lchild == null) {
	    transplant(node, node.rchild);
	    return;
	}
	if (node.rchild == null) {
	    transplant(node, node.lchild);
	    return;
	}
	Node succ = minNode(node.rchild);
	if (succ.parent != node) {
	    transplant(succ, succ.rchild);
	    succ.rchild = node.rchild;
	    succ.rchild.parent = succ;
	}
	transplant(node, succ);
	succ.lchild = node.lchild;
	succ.lchild.parent = succ;
    }
}
