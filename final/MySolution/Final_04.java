import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.LnStrm.*;
import MyLibrary.MyRefer.*;

public class Final_04 {
    private static final class Quiz02_06Map<K extends Comparable<K>, V> {
        private final class Node {
            K key;
            V val;
            int size;
            Node parent;
            Node lchild;
            Node rchild;

            Node(K key, V val, Node parent) {
                this.key = key;
                this.val = val;
                this.size = 1;
                this.parent = parent;
            }
        }

        private Node root = null;
        private long randState = 39220260428L;

        V search$opt(K key) {
            Node cur = root;
            while (cur != null) {
                int sgn = key.compareTo(cur.key);
                if (sgn == 0) {
                    return cur.val;
                }
                cur = (sgn < 0 ? cur.lchild : cur.rchild);
            }
            return null;
        }

        V insert$opt(K key, V val) {
            if (root == null) {
                root = new Node(key, val, null);
                return null;
            }
            Node cur = root;
            Node par = null;
            int sgn = 0;
            while (cur != null) {
                par = cur;
                sgn = key.compareTo(cur.key);
                if (sgn == 0) {
                    V old = cur.val;
                    cur.val = val;
                    return old;
                }
                cur = (sgn < 0 ? cur.lchild : cur.rchild);
            }
            Node node = new Node(key, val, par);
            if (sgn < 0) {
                par.lchild = node;
            } else {
                par.rchild = node;
            }
            refreshSizesUpward(par);
            reroot();
            return null;
        }

        void foritm(java.util.function.BiConsumer<? super K, ? super V> work) {
            inorder(root, work);
        }

        private int nextRandomInt(int bound) {
            randState = randState * 1103515245L + 12345L;
            long nonneg = randState & 0x7fffffffL;
            return (int)(nonneg % bound);
        }

        private int sizeOf(Node node) {
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
            if (y.lchild != null) {
                y.lchild.parent = x;
            }
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
            if (y.rchild != null) {
                y.rchild.parent = x;
            }
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
            if (rank < ls) {
                return selectByRank(node.lchild, rank);
            }
            if (rank == ls) {
                return node;
            }
            return selectByRank(node.rchild, rank - ls - 1);
        }

        private void reroot() {
            if (root == null) {
                return;
            }
            Node pick = selectByRank(root, nextRandomInt(root.size));
            while (pick.parent != null) {
                if (pick.parent.lchild == pick) {
                    rotateRight(pick.parent);
                } else {
                    rotateLeft(pick.parent);
                }
            }
        }

        private void inorder(Node node, java.util.function.BiConsumer<? super K, ? super V> work) {
            if (node == null) {
                return;
            }
            inorder(node.lchild, work);
            work.accept(node.key, node.val);
            inorder(node.rchild, work);
        }
    }

    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize4() {
        LnStrm<FnList<Character>> ws = Final_01.pg2701_word$strmize();
        Quiz02_06Map<String, Integer> map = new Quiz02_06Map<String, Integer>();
        while (true) {
            LnStcn<FnList<Character>> cell = ws.eval0();
            if (cell.nilq()) {
                break;
            }
            String word = Final_01.word2string(cell.hd());
            Integer old = map.search$opt(word);
            map.insert$opt(word, old == null ? 1 : old + 1);
            ws = cell.tl();
        }

        MyRefer<FnList<FnTupl2<FnList<Character>, Integer>>> ref =
            new MyRefer<FnList<FnTupl2<FnList<Character>, Integer>>>(FnListSUtil.nil());
        map.foritm((word, count) -> {
            FnTupl2<FnList<Character>, Integer> pair =
                new FnTupl2<FnList<Character>, Integer>(Final_01.string2word(word), count);
            ref.set$raw(FnListSUtil.cons(pair, ref.get$raw()));
        });
        return FnListSUtil.mergeSort(ref.get$raw(), Final_04::pairCompare);
    }

    private static int pairCompare(FnTupl2<FnList<Character>, Integer> p1, FnTupl2<FnList<Character>, Integer> p2) {
        int c1 = p1.sub1;
        int c2 = p2.sub1;
        if (c1 != c2) {
            return c2 - c1;
        }
        return Final_01.wordCompare(p1.sub0, p2.sub0);
    }

    public static void main(String[] args) {
        FnList<FnTupl2<FnList<Character>, Integer>> xs = pg2701_word$count$listize4();
        int i = 0;
        while (i < 100 && xs.consq()) {
            System.out.println(Final_01.word2string(xs.hd().sub0) + " " + xs.hd().sub1);
            xs = xs.tl();
            i += 1;
        }
    }
}
