package MyLibrary.MyMap00;

import MyLibrary.FnTuple.*;
import MyLibrary.LnList.*;
import MyLibrary.LnStrm.*;

public class MyHashMapSC<V> implements MyMap00<String, V> {
    private static final int DEFAULT_CAPACITY = 101;

    private LnList<FnTupl2<String, V>>[] table;
    private int size;

    private static final class Ref<T> {
	T value;
    }

    public MyHashMapSC() {
	this(DEFAULT_CAPACITY);
    }

    public MyHashMapSC(int capacity) {
	int cap = nextPrime(Math.max(3, capacity));
	table = makeTable(cap);
	size = 0;
    }

    private static<V>
	LnList<FnTupl2<String, V>>[] makeTable(int cap) {
	LnList<FnTupl2<String, V>>[] res =
	    (LnList<FnTupl2<String, V>>[]) new LnList[cap];
	for (int i0 = 0; i0 < cap; i0 += 1) {
	    res[i0] = new LnList<FnTupl2<String, V>>();
	}
	return res;
    }

    private int indexFor(String key) {
	return Math.floorMod(key.hashCode(), table.length);
    }

    private LnList<FnTupl2<String, V>>
	rebuildWithoutKey
	(LnList<FnTupl2<String, V>> bucket, String key, Ref<V> oldRef, FnTupl2<String, V> repl) {
	if (bucket.nilq1()) {
	    if (repl == null) return new LnList<FnTupl2<String, V>>();
	    return new LnList<FnTupl2<String, V>>(repl, new LnList<FnTupl2<String, V>>());
	}
	FnTupl2<String, V> hd = bucket.hd1();
	LnList<FnTupl2<String, V>> tl = bucket.tl1();
	if (hd.sub0.equals(key)) {
	    oldRef.value = hd.sub1;
	    return rebuildWithoutKey(tl, key, oldRef, repl);
	}
	return new LnList<FnTupl2<String, V>>(hd, rebuildWithoutKey(tl, key, oldRef, repl));
    }

    private V searchBucket(LnList<FnTupl2<String, V>> bucket, String key) {
	while (!bucket.nilq1()) {
	    FnTupl2<String, V> kv = bucket.hd1();
	    if (kv.sub0.equals(key)) return kv.sub1;
	    bucket = bucket.tl1();
	}
	return null;
    }

    private void ensureCapacityForInsert() {
	if ((size + 1) * 4 > table.length * 3) {
	    rehash(nextPrime(table.length * 2 + 1));
	}
    }

    private void rehash(int newCapacity) {
	LnList<FnTupl2<String, V>>[] oldTable = table;
	table = makeTable(newCapacity);
	int oldSize = size;
	size = 0;
	for (LnList<FnTupl2<String, V>> bucket : oldTable) {
	    bucket.foritm1((kv) -> insert$new(kv.sub0, kv.sub1));
	}
	size = oldSize;
    }

    private LnStrm<FnTupl2<String, V>> bucketStrm(LnList<FnTupl2<String, V>> bucket) {
	return new LnStrm<FnTupl2<String, V>>(
	    () -> {
		if (bucket.nilq1()) return new LnStcn<FnTupl2<String, V>>();
		return new LnStcn<FnTupl2<String, V>>(bucket.hd1(), bucketStrm(bucket.tl1()));
	    }
	);
    }

    private LnStrm<FnTupl2<String, V>> tableStrm(int i0) {
	return new LnStrm<FnTupl2<String, V>>(
	    () -> {
		int j0 = i0;
		while (j0 < table.length && table[j0].nilq1()) j0 += 1;
		if (j0 >= table.length) return new LnStcn<FnTupl2<String, V>>();
		LnStcn<FnTupl2<String, V>> c0 = bucketStrm(table[j0]).eval0();
		return new LnStcn<FnTupl2<String, V>>(c0.hd(), c0.tl().append0(tableStrm(j0 + 1)));
	    }
	);
    }

    public int size() { return size; }
    public boolean isFull() { return false; }
    public boolean isEmpty() { return size == 0; }
    public LnStrm<FnTupl2<String, V>> keyval_strmize() { return tableStrm(0); }
    public V search$old(String key) { return search$exn(key); }

    public V search$exn(String key) {
	V res = search$opt(key);
	if (res == null) throw new MyMap00NoKeyExn();
	return res;
    }

    public V search$opt(String key) {
	return searchBucket(table[indexFor(key)], key);
    }

    public V insert$opt(String key, V val) {
	ensureCapacityForInsert();
	int i0 = indexFor(key);
	Ref<V> oldRef = new Ref<V>();
	table[i0] =
	    rebuildWithoutKey(table[i0], key, oldRef, new FnTupl2<String, V>(key, val));
	if (oldRef.value == null) size += 1;
	return oldRef.value;
    }

    public void insert$new(String key, V val) {
	if (search$opt(key) == null) {
	    ensureCapacityForInsert();
	    int i0 = indexFor(key);
	    table[i0] = new LnList<FnTupl2<String, V>>(new FnTupl2<String, V>(key, val), table[i0]);
	    size += 1;
	    return;
	}
	insert$opt(key, val);
    }

    public V remove$old(String key) { return remove$exn(key); }

    public V remove$exn(String key) {
	V res = remove$opt(key);
	if (res == null) throw new MyMap00NoKeyExn();
	return res;
    }

    public V remove$opt(String key) {
	int i0 = indexFor(key);
	Ref<V> oldRef = new Ref<V>();
	table[i0] = rebuildWithoutKey(table[i0], key, oldRef, null);
	if (oldRef.value != null) size -= 1;
	return oldRef.value;
    }

    public void foritm(java.util.function.BiConsumer<? super String, ? super V> work) {
	for (LnList<FnTupl2<String, V>> bucket : table) {
	    bucket.foritm1((kv) -> work.accept(kv.sub0, kv.sub1));
	}
    }

    private static int nextPrime(int n0) {
	int c0 = Math.max(3, n0);
	if (c0 % 2 == 0) c0 += 1;
	while (!isPrime(c0)) c0 += 2;
	return c0;
    }

    private static boolean isPrime(int n0) {
	if (n0 <= 1) return false;
	if (n0 == 2) return true;
	if (n0 % 2 == 0) return false;
	for (int i0 = 3; i0 * i0 <= n0; i0 += 2) {
	    if (n0 % i0 == 0) return false;
	}
	return true;
    }
}
