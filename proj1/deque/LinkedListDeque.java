package deque;

import java.util.Iterator;


public class LinkedListDeque<T> {
    private class typeNode {
        private typeNode prev;
        private T item;
        private typeNode next;

        private typeNode() {
            item = null;
            prev = null;
            next = null;
        }

        private typeNode(T i, typeNode p, typeNode n) {
            this.item = i;
            this.prev = p;
            this.next = n;
        }
    }

    private int size;
    private typeNode head;

    public LinkedListDeque() {
        head = new typeNode();
        head.prev = head;
        head.next = head;
        size = 0;
    }

    public void addFirst(T item) {
        typeNode newNode = new typeNode(item, head, head.next);
        head.next = newNode;
        head.next.next.prev = head.next;
        size += 1;
    }

    public void addLast(T item) {
        typeNode newNode = new typeNode(item, head.prev, head);
        head.prev = newNode;
        head.prev.prev.next = head.prev;
        size += 1;
    }

    public boolean isEmpty() {
        return (head.next == head);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        typeNode curr = head.next;
        while (curr != head) {
            System.out.print(curr.item);
            System.out.print(" ");
            curr = curr.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (head.next == head) {
            return null;
        }
        typeNode removed = head.next;
        head.next.next.prev = head;
        head.next = head.next.next;
        size -= 1;
        return removed.item;
    }

    public T removeLast() {
        if (head.prev == head) {
            return null;
        }
        typeNode removed = head.prev;
        head.prev.prev.next = head;
        head.prev = head.prev.prev;
        size -= 1;
        return removed.item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        typeNode curr = head.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.item;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(head.next, index);

    }

    public T getRecursiveHelper(typeNode n, int index) {
        if (n == head) {
            return null;
        }
        if (index == 0) {
            return n.item;
        }
        return getRecursiveHelper(n.next, index - 1);
    }



}

