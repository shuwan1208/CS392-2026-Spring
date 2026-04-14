public class Assign09_01 {
    Node root = null;

    public class Node {
        int key;
        int size;
        Node parent;
        Node lchild;
        Node rchild;

        Node(int key, Node parent) {
            this.key = key;
            this.size = 1;
            this.parent = parent;
            this.lchild = null;
            this.rchild = null;
        }
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
        Node cur = node;
        while (cur != null) {
            refreshSize(cur);
            cur = cur.parent;
        }
    }

    private Node searchNode(int key) {
        Node cur = root;
        while (cur != null) {
            if (key == cur.key) {
                return cur;
            }
            if (key < cur.key) {
                cur = cur.lchild;
            } else {
                cur = cur.rchild;
            }
        }
        return null;
    }

    private Node minimum(Node node) {
        Node cur = node;
        while (cur != null && cur.lchild != null) {
            cur = cur.lchild;
        }
        return cur;
    }

    private void replaceAtParent(Node node, Node replacement) {
        Node parent = node.parent;
        if (parent == null) {
            root = replacement;
        } else if (parent.lchild == node) {
            parent.lchild = replacement;
        } else {
            parent.rchild = replacement;
        }
        if (replacement != null) {
            replacement.parent = parent;
        }
    }

    public boolean insert(int key) {
        if (root == null) {
            root = new Node(key, null);
            return true;
        }

        Node cur = root;
        Node parent = null;
        while (cur != null) {
            parent = cur;
            if (key == cur.key) {
                return false;
            }
            if (key < cur.key) {
                cur = cur.lchild;
            } else {
                cur = cur.rchild;
            }
        }

        Node node = new Node(key, parent);
        if (key < parent.key) {
            parent.lchild = node;
        } else {
            parent.rchild = node;
        }
        refreshSizesUpward(parent);
        return true;
    }

    public boolean remove(int key) {
        Node node = searchNode(key);
        if (node == null) {
            return false;
        }

        if (node.lchild == null) {
            Node parent = node.parent;
            replaceAtParent(node, node.rchild);
            refreshSizesUpward(parent);
            return true;
        }

        if (node.rchild == null) {
            Node parent = node.parent;
            replaceAtParent(node, node.lchild);
            refreshSizesUpward(parent);
            return true;
        }

        Node succ = minimum(node.rchild);
        Node succParentBefore = succ.parent;
        if (succ.parent != node) {
            replaceAtParent(succ, succ.rchild);
            succ.rchild = node.rchild;
            succ.rchild.parent = succ;
        }

        replaceAtParent(node, succ);
        succ.lchild = node.lchild;
        succ.lchild.parent = succ;
        refreshSizesUpward(succParentBefore == node ? succ : succParentBefore);
        return true;
    }

    private boolean contains(int key) {
        return searchNode(key) != null;
    }

    private void inorderPrint(Node node) {
        if (node == null) {
            return;
        }
        inorderPrint(node.lchild);
        System.out.print(node.key + "(" + node.size + ") ");
        inorderPrint(node.rchild);
    }

    public static void main(String[] args) {
        Assign09_01 tree = new Assign09_01();

        System.out.println(tree.insert(50));
        System.out.println(tree.insert(30));
        System.out.println(tree.insert(70));
        System.out.println(tree.insert(20));
        System.out.println(tree.insert(40));
        System.out.println(tree.insert(60));
        System.out.println(tree.insert(80));
        System.out.println(tree.insert(30));

        tree.inorderPrint(tree.root);
        System.out.println();
        System.out.println(tree.root.size);

        System.out.println(tree.remove(20));
        System.out.println(tree.remove(70));
        System.out.println(tree.remove(50));
        System.out.println(tree.remove(999));

        tree.inorderPrint(tree.root);
        System.out.println();
        System.out.println(tree.root.key);
        System.out.println(tree.root.size);
        System.out.println(tree.contains(20));
        System.out.println(tree.contains(50));
        System.out.println(tree.contains(60));
    }
}
