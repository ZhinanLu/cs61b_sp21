package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public ArrayDeque(T item) {
        items[4] = item;
        size = 1;
        nextFirst = 3;
        nextLast = 5;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int firstIndex = Math.abs(capacity - size) / 2;
        System.arraycopy(items, nextFirst + 1,newItems, firstIndex, size);
        items = newItems;
        nextFirst = firstIndex - 1;
        nextLast = firstIndex + size;
    }


    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst -= 1;
        size += 1;
        if (nextFirst == -1) {
            resize(size * 2);
        }
    }

    public void addLast(T item) {
        items[nextLast] = item;
        nextLast += 1;
        size += 1;
        if (nextLast == items.length) {
            resize(size * 2);
        }
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = nextFirst; i <= nextLast; i += 1) {
            System.out.print(items[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removed = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        nextFirst += 1;
        return removed;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removed = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        nextLast -= 1;
        return removed;
    }

    public T get(int index) {
        if (index > size || (index + nextFirst) > nextLast) {
            return null;
        }
        return items[index + nextFirst + 1];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (other.size() != size) {
            return false;
        }
        for(int i = 0; i < size; i++) {
            if (other.get(i) != get(i)) {
                return false;
            }
        }
        return true;
    }

    private class ArrayDequeIterator implements Iteraotr<T> {
        private int index;

        ArrayDequeIterator() {
            index = 0;
        }

        public boolean hasNext() {
            return index < size;
        }

        public T next() {
            T item = get(index);
            index += 1;
            return item;
        }
    }

}

