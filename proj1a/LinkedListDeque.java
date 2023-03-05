/** first part of project1A.
 * Deque implemented by Linked List
 * @author jiangxinqi
 */
public class LinkedListDeque<T> {
    /** sentinel node. */
    private Node sentinel;
    /** size of the deque. */
    private int size;

    /** inner class Node. */
    public class Node {
        /** the item stored on this node. */
        public T item;
        /** the node before this node. */
        public Node prev;
        /** the node after this node. */
        public Node next;

        /** constructor for Node. */
        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
        /** constructor for Node. (especially for sentinel node.) */
        public Node(Node p, Node n) {
            prev = p;
            next = n;
        }
    }

    /** constructor for the deque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T x) {
        Node newNode = new Node(sentinel, x, sentinel.next);
        sentinel.next = newNode;
        newNode.next.prev = newNode;
        size++;
    }

    public void addLast(T x) {
        Node newNode = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }
    public boolean isEmpty() {
        return size == 0;
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
        if (size == 0) {
            return null;
        }
        T x = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return x;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T x = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
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
    private T getRecursiveHelp(Node start, int index) {
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
