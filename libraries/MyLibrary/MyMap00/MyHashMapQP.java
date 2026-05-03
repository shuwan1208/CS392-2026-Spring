package MyLibrary.MyMap00;

import MyLibrary.FnTuple.*;
import MyLibrary.LnStrm.*;

public class MyHashMapQP<V> implements MyMap00<String, V> {
    private static final int DEFAULT_CAPACITY = 101;

    private FnTupl2<String, V>[] table;
    private boolean[] deleted;
    private int size;
    private int usedSlots;

    private static final class ProbeResult<V> {
	int index;
	FnTupl2<String, V> entry;
    }

    public MyHashMapQP() {
	this(DEFAULT_CAPACITY);
    }

    public MyHashMapQP(int capacity) {
	int cap = nextPrime(Math.max(3, capacity));
	table = (FnTupl2<String, V>[]) new FnTupl2[cap];
	deleted = new boolean[cap];
	size = 0;
	usedSlots = 0;
    }

    private int baseIndex(String key) {
	return Math.floorMod(key.hashCode(), table.length);
    }

    private ProbeResult<V> probeSearch(String key) {
	int base = baseIndex(key);
	for (int i0 = 0; i0 < table.length; i0 += 1) {
	    int j0 = Math.floorMod(base + i0 * i0, table.length);
	    if (table[j0] == null) {
		if (!deleted[j0]) return null;
	    } else if (table[j0].sub0.equals(key)) {
		ProbeResult<V> res = new ProbeResult<V>();
		res.index = j0;
		res.entry = table[j0];
		return res;
	    }
	}
	return null;
    }

    private int probeInsertIndex(String key) {
	int base = baseIndex(key);
	int firstDeleted = -1;
	for (int i0 = 0; i0 < table.length; i0 += 1) {
	    int j0 = Math.floorMod(base + i0 * i0, table.length);
	    if (table[j0] == null) {
		if (deleted[j0]) {
		    if (firstDeleted < 0) firstDeleted = j0;
		} else {
		    return firstDeleted >= 0 ? firstDeleted : j0;
		}
	    } else if (table[j0].sub0.equals(key)) {
		return j0;
	    }
	}
	return firstDeleted;
    }

    private void ensureCapacityForInsert() {
	if ((usedSlots + 1) * 2 >= table.length) {
	    rehash(nextPrime(table.length * 2 + 1));
	}
    }

    private void rehash(int newCapacity) {
	FnTupl2<String, V>[] oldTable = table;
	table = (FnTupl2<String, V>[]) new FnTupl2[newCapacity];
	deleted = new boolean[newCapacity];
	int oldSize = size;
	size = 0;
	usedSlots = 0;
	for (FnTupl2<String, V> kv : oldTable) {
	    if (kv != null) insert$new(kv.sub0, kv.sub1);
	}
	size = oldSize;
	usedSlots = oldSize;
    }

    private LnStrm<FnTupl2<String, V>> tableStrm(int i0) {
	return new LnStrm<FnTupl2<String, V>>(
	    () -> {
		int j0 = i0;
		while (j0 < table.length && table[j0] == null) j0 += 1;
		if (j0 >= table.length) return new LnStcn<FnTupl2<String, V>>();
		return new LnStcn<FnTupl2<String, V>>(table[j0], tableStrm(j0 + 1));
	    }
	);
    }

    public int size() { return size; }
    public boolean isFull() { return size == table.length; }
    public boolean isEmpty() { return size == 0; }
    public LnStrm<FnTupl2<String, V>> keyval_strmize() { return tableStrm(0); }
    public V search$old(String key) { return search$exn(key); }

    public V search$exn(String key) {
	V res = search$opt(key);
	if (res == null) throw new MyMap00NoKeyExn();
	return res;
    }

    public V search$opt(String key) {
	ProbeResult<V> found = probeSearch(key);
	return found == null ? null : found.entry.sub1;
    }

    public V insert$opt(String key, V val) {
	ProbeResult<V> found = probeSearch(key);
	if (found != null) {
	    V old = found.entry.sub1;
	    table[found.index] = new FnTupl2<String, V>(key, val);
	    return old;
	}
	ensureCapacityForInsert();
	int i0 = probeInsertIndex(key);
	if (i0 < 0) {
	    rehash(nextPrime(table.length * 2 + 1));
	    i0 = probeInsertIndex(key);
	}
	if (table[i0] == null && !deleted[i0]) usedSlots += 1;
	table[i0] = new FnTupl2<String, V>(key, val);
	deleted[i0] = false;
	size += 1;
	return null;
    }

    public void insert$new(String key, V val) {
	insert$opt(key, val);
    }

    public V remove$old(String key) { return remove$exn(key); }

    public V remove$exn(String key) {
	V res = remove$opt(key);
	if (res == null) throw new MyMap00NoKeyExn();
	return res;
    }

    public V remove$opt(String key) {
	ProbeResult<V> found = probeSearch(key);
	if (found == null) return null;
	V old = found.entry.sub1;
	table[found.index] = null;
	deleted[found.index] = true;
	size -= 1;
	return old;
    }

    public void foritm(java.util.function.BiConsumer<? super String, ? super V> work) {
	for (FnTupl2<String, V> kv : table) {
	    if (kv != null) work.accept(kv.sub0, kv.sub1);
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
