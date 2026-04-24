package Library.MyPQueue;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class MyPQueueArray<T extends Comparable<? super T>> extends MyPQueueBase<T> {

    int nitm = 0;
    T[] itms = null;

    public
    MyPQueueArray(int cap)
    {
	assert (cap >= 1);
	itms = (T[]) new Comparable[cap];
    }

    @Override
    public int size() {
	return nitm;
    }

    @Override
    public boolean isFull() {
	return (nitm >= itms.length);
    }

    @Override
    public T top$raw() {
	return itms[0];
    }

    @Override
    public T deque$raw() {
	T itm = itms[0];
	nitm -= 1;
	itms[0] = itms[nitm];
	itms[nitm] = null;
	if (nitm > 0) sink(0);
	return itm;
    }

    @Override
    public void enque$raw(T itm) {
	itms[nitm] = itm;
	swim(nitm);
	nitm += 1;
	return;
    }

    private void swim(int i0) {
	while (i0 > 0) {
	    int p0 = (i0 - 1) / 2;
	    if (itms[p0].compareTo(itms[i0]) >= 0) break;
	    swap(p0, i0);
	    i0 = p0;
	}
	return;
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
	return;
    }

    private void swap(int i0, int j0) {
	T tmp = itms[i0];
	itms[i0] = itms[j0];
	itms[j0] = tmp;
	return;
    }

}
