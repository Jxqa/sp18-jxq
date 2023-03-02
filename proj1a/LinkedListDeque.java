public class LinkedListDeque<Item> {
    private Node sentinel;
    private int size;

    public class Node {
        public Item item;
        public Node prev;
        public Node next;
        public Node(Node p, Item i, Node n) {
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
    public LinkedListDeque(Item x) {
        sentinel.next = new Node(sentinel, x, sentinel);
        size = 1;
    }
    public void addFirst(Item x) {
        sentinel.next = new Node(sentinel, x, sentinel.next);
        size++;
    }
    public void addLast(Item x) {
        Node p = sentinel;
        while(p.next != sentinel) {
            p = p.next;
        }
        p.next = new Node(p, x, sentinel);
        sentinel.prev = p.next;
        size++;
    }
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        Node p = sentinel;
        for(; p.next != sentinel; p = p.next) {
            System.out.print(p.item + " ");
        }
        System.out.print(p.item + " ");
    }
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Item x = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size--;
        return x;
    }
    public Item removeLast() {
        if(isEmpty()) {
            return null;
        }
        Node p = sentinel;
        while(p.next != sentinel) {
            p = p.next;
        }
        Item x = p.item;
        p.prev.next = sentinel;
        size--;
        return x;
    }
    public Item get(int index) {
        if(index > size - 1) {
            return null;
        }
        Node p = sentinel.next;
        int current = 0;
        while(current < index) {
            p = p.next;
            current++;
        }
        return p.item;
    }
    public Item getRecursiveHelp(Node start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursiveHelp(start.next, index - 1);
        }
    }
    public Item getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return getRecursiveHelp(sentinel.next, index);
    }
}

