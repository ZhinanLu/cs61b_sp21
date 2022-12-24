package deque;

public class ArrayDeque<T> {
    private T[] items;
    private  int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }
}
