import Library00.FnList.*;
import Library00.LnList.*;
import Library00.LnStrm.*;
import Library00.FnTuple.*;
import Library00.MyMap00.*;

public class Assign08_02<V>
    implements MyMap00<String, V> {
    private static final int DEFAULT_CAPACITY = 101;

    private FnTupl2<String, V> table[];
    private boolean deleted[];
    private int size;
    private int usedSlots;

    @SuppressWarnings("unchecked")
    public Assign08_02() {
        int cap = nextPrime(DEFAULT_CAPACITY);
        this.table = (FnTupl2<String, V>[]) new FnTupl2[cap];
        this.deleted = new boolean[cap];
        this.size = 0;
        this.usedSlots = 0;
    }

    @SuppressWarnings("unchecked")
    public Assign08_02(int capacity) {
        int cap = nextPrime(Math.max(3, capacity));
        this.table = (FnTupl2<String, V>[]) new FnTupl2[cap];
        this.deleted = new boolean[cap];
        this.size = 0;
        this.usedSlots = 0;
    }

    private static final class ProbeResult<V> {
        int index;
        FnTupl2<String, V> entry;
    }

    private int baseIndex(String key) {
        return Math.floorMod(key.hashCode(), table.length);
    }

    private ProbeResult<V> probeSearch(String key) {
        int base = baseIndex(key);
        for (int i = 0; i < table.length; i += 1) {
            int index = Math.floorMod(base + i * i, table.length);
            if (table[index] == null) {
                if (!deleted[index]) {
                    return null;
                }
            } else if (table[index].sub0.equals(key)) {
                ProbeResult<V> res = new ProbeResult<V>();
                res.index = index;
                res.entry = table[index];
                return res;
            }
        }
        return null;
    }

    private int probeInsertIndex(String key) {
        int base = baseIndex(key);
        int firstDeleted = -1;
        for (int i = 0; i < table.length; i += 1) {
            int index = Math.floorMod(base + i * i, table.length);
            if (table[index] == null) {
                if (deleted[index]) {
                    if (firstDeleted < 0) {
                        firstDeleted = index;
                    }
                } else {
                    return firstDeleted >= 0 ? firstDeleted : index;
                }
            } else if (table[index].sub0.equals(key)) {
                return index;
            }
        }
        return firstDeleted;
    }

    private void ensureCapacityForInsert() {
        if ((usedSlots + 1) * 2 >= table.length) {
            rehash(nextPrime(table.length * 2 + 1));
        }
    }

    @SuppressWarnings("unchecked")
    private void rehash(int newCapacity) {
        FnTupl2<String, V>[] oldTable = table;
        table = (FnTupl2<String, V>[]) new FnTupl2[newCapacity];
        deleted = new boolean[newCapacity];
        int oldSize = size;
        size = 0;
        usedSlots = 0;
        for (FnTupl2<String, V> kv : oldTable) {
            if (kv != null) {
                insert$new(kv.sub0, kv.sub1);
            }
        }
        size = oldSize;
        usedSlots = oldSize;
    }

    private static int nextPrime(int n) {
        int candidate = Math.max(3, n);
        if (candidate % 2 == 0) {
            candidate += 1;
        }
        while (!isPrime(candidate)) {
            candidate += 2;
        }
        return candidate;
    }

    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private LnStrm<FnTupl2<String, V>> tableStrm(int i) {
        return new LnStrm<FnTupl2<String, V>>(
            () -> {
                int j = i;
                while (j < table.length && table[j] == null) {
                    j += 1;
                }
                if (j >= table.length) {
                    return new LnStcn<FnTupl2<String, V>>();
                }
                return new LnStcn<FnTupl2<String, V>>(table[j], tableStrm(j + 1));
            }
        );
    }

    public int size() {
        return size;
    }

    public boolean isFull() {
        return size == table.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public LnStrm<FnTupl2<String, V>> keyval_strmize() {
        return tableStrm(0);
    }

    public V search$old(String key) {
        return search$exn(key);
    }

    public V search$exn(String key) {
        V res = search$opt(key);
        if (res == null) {
            throw new MyMap00NoKeyExn();
        }
        return res;
    }

    public V search$opt(String key) {
        ProbeResult<V> res = probeSearch(key);
        return res == null ? null : res.entry.sub1;
    }

    public V insert$opt(String key, V val) {
        ProbeResult<V> found = probeSearch(key);
        if (found != null) {
            V old = found.entry.sub1;
            table[found.index] = new FnTupl2<String, V>(key, val);
            return old;
        }

        ensureCapacityForInsert();
        int index = probeInsertIndex(key);
        if (index < 0) {
            rehash(nextPrime(table.length * 2 + 1));
            index = probeInsertIndex(key);
        }
        if (table[index] == null && !deleted[index]) {
            usedSlots += 1;
        }
        table[index] = new FnTupl2<String, V>(key, val);
        deleted[index] = false;
        size += 1;
        return null;
    }

    public void insert$new(String key, V val) {
        insert$opt(key, val);
    }

    public V remove$old(String key) {
        return remove$exn(key);
    }

    public V remove$exn(String key) {
        V res = remove$opt(key);
        if (res == null) {
            throw new MyMap00NoKeyExn();
        }
        return res;
    }

    public V remove$opt(String key) {
        ProbeResult<V> found = probeSearch(key);
        if (found == null) {
            return null;
        }
        V old = found.entry.sub1;
        table[found.index] = null;
        deleted[found.index] = true;
        size -= 1;
        return old;
    }

    public void foritm(java.util.function.BiConsumer<? super String, ? super V> work) {
        for (FnTupl2<String, V> kv : table) {
            if (kv != null) {
                work.accept(kv.sub0, kv.sub1);
            }
        }
    }
}
