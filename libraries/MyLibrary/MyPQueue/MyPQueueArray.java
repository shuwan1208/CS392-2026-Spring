package MyLibrary.MyPQueue;

public class MyPQueueArray<T extends Comparable<? super T>> extends MyPQueueBase<T> {
    private int nitm;
    private final T[] itms;

    public MyPQueueArray(int cap) {
	assert(cap >= 1);
	nitm = 0;
	itms = (T[]) new Comparable[cap];
    }

    public int size() {
	return nitm;
    }

    public boolean isFull() {
	return nitm >= itms.length;
    }

    public T top$raw() {
	return itms[0];
    }

    public T deque$raw() {
	T itm = itms[0];
	nitm -= 1;
	itms[0] = itms[nitm];
	itms[nitm] = null;
	if (nitm > 0) sink(0);
	return itm;
    }

    public void enque$raw(T itm) {
	itms[nitm] = itm;
	swim(nitm);
	nitm += 1;
    }

    private void swim(int i0) {
	while (i0 > 0) {
	    int p0 = (i0 - 1) / 2;
	    if (itms[p0].compareTo(itms[i0]) >= 0) break;
	    swap(p0, i0);
	    i0 = p0;
	}
    }

    private void sink(int i0) {
	while (true) {
	    int l0 = 2 * i0 + 1;
	    int r0 = l0 + 1;
	    int j0 = i0;
	    if (l0 < nitm && itms[l0].compareTo(itms[j0]) > 0) j0 = l0;
	    if (r0 < nitm && itms[r0].compareTo(itms[j0]) > 0) j0 = r0;
	    if (j0 == i0) break;
	    swap(i0, j0);
	    i0 = j0;
	}
    }

    private void swap(int i0, int j0) {
	T tmp = itms[i0];
	itms[i0] = itms[j0];
	itms[j0] = tmp;
    }
}
