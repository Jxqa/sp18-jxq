public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int length;
    private int nextFirst;
    private int nextLast;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        length = 8;
        nextFirst = 4;
        nextLast = 5;
    }
    public void addFirst(T x) {
        if (size == length) {
            expand();
        }
        items[nextFirst] = x;
        size++;
        if (nextFirst - 1 < 0) {
            nextFirst = length - 1;
        }
        nextFirst--;
    }
    public void addLast(T x) {
        if (size == length) {
            expand();
        }
        items[nextLast] = x;
        size++;
        if (nextLast + 1 > length - 1) {
            nextLast = 0;
        }
        nextLast++;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        int ptr = nextFirst + 1;
        if (nextFirst + 1 > length - 1) {
            ptr = 0;
        }
        while(ptr != nextLast) {
            System.out.print(items[ptr] + " ");
            if (ptr + 1 > length - 1) {
                ptr = 0;
            } else {
                ptr++;
            }
        }
    }
    public T removeFirst() {
        if (nextFirst + 1 > length - 1) {
            nextFirst = 0;
        } else {
            nextFirst++;
        }
        T x = items[nextFirst];
        size--;
        return x;
    }
    public T removeLast() {
        if (nextLast - 1 < 0) {
            nextLast = length - 1;
        } else {
            nextLast--;
        }
        T x = items[nextLast];
        size--;
        return x;
    }
    public T get(int index) {
        return items[index];
    }
    public void expand() {
        Item[] array = (Item[]) new Object[2 * length];
        System.arraycopy(items, nextLast, array,
                array.length -(items.length - nextLast + 1),
                 items.length - nextLast + 1);
        System.arraycopy(items, 0, array, 0, nextFirst + 1);
        nextFirst = array.length -(items.length - nextLast + 1) - 1;
        items = array;
    }
}
