package deque;

public class ArrayDeque<T> {
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

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int startIndex = size / 2;
        System.arraycopy(items, 0, a, startIndex, size);
        items = a;
        nextFirst = startIndex;
        nextLast = startIndex + size - 1;
    }


    public void addFirst(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextFirst] = item;
        nextFirst -= 1;
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextLast] = item;
        nextLast += 1;
        size += 1;
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
        T removed = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        nextFirst += 1;
        return removed;
    }

    public T removeLast() {
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
        return items[index + nextFirst];
    }
}

