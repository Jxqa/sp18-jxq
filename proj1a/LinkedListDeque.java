public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        private T item;
        private Node prev;
        private Node next;
        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
        public Node(Node p, Node n) {
            prev = p;
            next = n;
        }
    }
    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }
    public void addFirst(T x) {
        sentinel.next = new Node(sentinel, x, sentinel.next);
        size++;
    }
    public void addLast(T x) {
        Node p = sentinel;
        while (p.next != sentinel) {
            p = p.next;
        }
        p.next = new Node(p, x, sentinel);
        sentinel.prev = p.next;
        size++;
    }
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        Node p = sentinel;
        for (; p.next != sentinel; p = p.next) {
            System.out.print(p.item + " ");
        }
        System.out.print(p.item + " ");
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T x = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size--;
        return x;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node p = sentinel;
        while (p.next != sentinel) {
            p = p.next;
        }
        T x = p.item;
        p.prev.next = sentinel;
        size--;
        return x;
    }
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        Node p = sentinel.next;
        int current = 0;
        while (current < index) {
            p = p.next;
            current++;
        }
        return p.item;
    }
    public T getRecursiveHelp(Node start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursiveHelp(start.next, index - 1);
        }
    }
    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return getRecursiveHelp(sentinel.next, index);
    }
}

