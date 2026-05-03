package MyLibrary.MyPQueue;

public abstract class MyPQueueBase<T> implements MyPQueue<T> {
    public boolean isEmpty() {
	return size() <= 0;
    }

    public T top$opt() {
	return isEmpty() ? null : top$raw();
    }

    public T top$exn() throws MyPQueueEmptyExn {
	T itm = top$opt();
	if (itm != null) return itm;
	throw new MyPQueueEmptyExn();
    }

    public T deque$opt() {
	return isEmpty() ? null : deque$raw();
    }

    public T deque$exn() throws MyPQueueEmptyExn {
	T itm = deque$opt();
	if (itm != null) return itm;
	throw new MyPQueueEmptyExn();
    }

    public boolean enque$opt(T itm) {
	if (isFull()) return false;
	enque$raw(itm);
	return true;
    }

    public void enque$exn(T itm) throws MyPQueueFullExn {
	if (!enque$opt(itm)) throw new MyPQueueFullExn();
    }
}
