import MyLibrary.FnList.*;
import MyLibrary.LnList.*;
import MyLibrary.LnStrm.*;
import MyLibrary.FnTuple.*;
import MyLibrary.MyMap00.*;

public class Assign08_01<V>
    implements MyMap00<String, V> {
    private static final int DEFAULT_CAPACITY = 101;

    private LnList<FnTupl2<String, V>> table[];
    private int size;

    private static final class Ref<T> {
        T value;
    }

    @SuppressWarnings("unchecked")
    public Assign08_01() {
        this.table = (LnList<FnTupl2<String, V>>[]) new LnList[DEFAULT_CAPACITY];
        for (int i = 0; i < this.table.length; i += 1) {
            this.table[i] = new LnList<FnTupl2<String, V>>();
        }
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public Assign08_01(int capacity) {
        int cap = nextPrime(Math.max(3, capacity));
        this.table = (LnList<FnTupl2<String, V>>[]) new LnList[cap];
        for (int i = 0; i < this.table.length; i += 1) {
            this.table[i] = new LnList<FnTupl2<String, V>>();
        }
        this.size = 0;
    }

    private int indexFor(String key) {
        return Math.floorMod(key.hashCode(), table.length);
    }

    private LnList<FnTupl2<String, V>>
    rebuildWithoutKey(
        LnList<FnTupl2<String, V>> bucket,
        String key,
        Ref<V> oldRef,
        FnTupl2<String, V> replacement
    ) {
        if (bucket.nilq1()) {
            if (replacement != null) {
                return new LnList<FnTupl2<String, V>>(replacement, new LnList<FnTupl2<String, V>>());
            }
            return new LnList<FnTupl2<String, V>>();
        }

        FnTupl2<String, V> head = bucket.hd1();
        LnList<FnTupl2<String, V>> tail = bucket.tl1();
        if (head.sub0.equals(key)) {
            oldRef.value = head.sub1;
            LnList<FnTupl2<String, V>> rest = rebuildWithoutKey(tail, key, oldRef, replacement);
            return rest;
        }

        LnList<FnTupl2<String, V>> rest = rebuildWithoutKey(tail, key, oldRef, replacement);
        return new LnList<FnTupl2<String, V>>(head, rest);
    }

    private void ensureCapacityForInsert() {
        if ((size + 1) * 4 > table.length * 3) {
            rehash(nextPrime(table.length * 2 + 1));
        }
    }

    @SuppressWarnings("unchecked")
    private void rehash(int newCapacity) {
        LnList<FnTupl2<String, V>> oldTable[] = table;
        table = (LnList<FnTupl2<String, V>>[]) new LnList[newCapacity];
        for (int i = 0; i < table.length; i += 1) {
            table[i] = new LnList<FnTupl2<String, V>>();
        }
        int oldSize = size;
        size = 0;
        for (LnList<FnTupl2<String, V>> bucket : oldTable) {
            bucket.foritm1((kv) -> insert$new(kv.sub0, kv.sub1));
        }
        size = oldSize;
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

    private V searchBucket(LnList<FnTupl2<String, V>> bucket, String key) {
        LnList<FnTupl2<String, V>> cur = bucket;
        while (!cur.nilq1()) {
            FnTupl2<String, V> kv = cur.hd1();
            if (kv.sub0.equals(key)) {
                return kv.sub1;
            }
            cur = cur.tl1();
        }
        return null;
    }

    private LnStrm<FnTupl2<String, V>>
    bucketStrm(LnList<FnTupl2<String, V>> bucket) {
        return new LnStrm<FnTupl2<String, V>>(
            () -> {
                if (bucket.nilq1()) {
                    return new LnStcn<FnTupl2<String, V>>();
                }
                return new LnStcn<FnTupl2<String, V>>(bucket.hd1(), bucketStrm(bucket.tl1()));
            }
        );
    }

    private LnStrm<FnTupl2<String, V>> tableStrm(int i) {
        return new LnStrm<FnTupl2<String, V>>(
            () -> {
                int j = i;
                while (j < table.length && table[j].nilq1()) {
                    j += 1;
                }
                if (j >= table.length) {
                    return new LnStcn<FnTupl2<String, V>>();
                }
                LnStcn<FnTupl2<String, V>> cbucket = bucketStrm(table[j]).eval0();
                return new LnStcn<FnTupl2<String, V>>(cbucket.hd(), cbucket.tl().append0(tableStrm(j + 1)));
            }
        );
    }

    public int size() {
        return size;
    }

    public boolean isFull() {
        return false;
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
        return searchBucket(table[indexFor(key)], key);
    }

    public V insert$opt(String key, V val) {
        ensureCapacityForInsert();
        int index = indexFor(key);
        Ref<V> oldRef = new Ref<V>();
        LnList<FnTupl2<String, V>> updated =
            rebuildWithoutKey(table[index], key, oldRef, new FnTupl2<String, V>(key, val));
        table[index] = updated;
        if (oldRef.value == null) {
            size += 1;
        }
        return oldRef.value;
    }

    public void insert$new(String key, V val) {
        if (search$opt(key) == null) {
            ensureCapacityForInsert();
            int index = indexFor(key);
            table[index] = new LnList<FnTupl2<String, V>>(
                new FnTupl2<String, V>(key, val),
                table[index]
            );
            size += 1;
            return;
        }
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
        int index = indexFor(key);
        Ref<V> oldRef = new Ref<V>();
        table[index] = rebuildWithoutKey(table[index], key, oldRef, null);
        if (oldRef.value != null) {
            size -= 1;
        }
        return oldRef.value;
    }

    public void foritm(java.util.function.BiConsumer<? super String, ? super V> work) {
        for (LnList<FnTupl2<String, V>> bucket : table) {
            bucket.foritm1((kv) -> work.accept(kv.sub0, kv.sub1));
        }
    }
}
