/** second part of project1A.
 * Deque implemented by array.
 * @author jiangxinqi
 */
public class ArrayDeque<T> {
    /** array to store the data. */
    private T[] items;
    /** the size of the deque. */
    private int size;
    /** the size of the array. */
    private int length;
    /** front index. */
    private int nextFirst;
    /** last index. */
    private int nextLast;
    /** constructor for ArrayDeque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        length = 8;
        nextFirst = 4;
        nextLast = 5;
    }

    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }

    private int plusOne(int index) {
        if (index == length - 1) {
            return 0;
        }
        return index + 1;
    }

    public void addFirst(T x) {
        if (size == length) {
            expand();
        }
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    public void addLast(T x) {
        if (size == length) {
            expand();
        }
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int ptr = plusOne(nextFirst);
        while (ptr != nextLast) {
            System.out.print(items[ptr] + " ");
            ptr = plusOne(ptr);
        }
    }
    public T removeFirst() {
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        /** if (size == 0) {
            return null;
        } */
        nextFirst = plusOne(nextFirst);
        T x = items[nextFirst];
        size -= 1;
        return x;
    }

    public T removeLast() {
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        /** if (size == 0) {
            return null;
        } */
        nextLast = plusOne(nextLast);
        T x = items[nextLast];
        size -= 1;
        return x;
    }
    public T get(int index) {
        /** if (index > size - 1) {
            return null;
        } */
        int ptr = plusOne(nextFirst);
        for (int i = 0; i < index; i++) {
            ptr = plusOne(ptr);
        }
        return items[ptr];
    }

    private void expand() {
        T[] array = (T[]) new Object[2 * length];

        int ptr = plusOne(nextFirst);
        int i = 0;
        while (ptr != nextLast) {
            array[i] = items[ptr];
            i++;
            ptr = plusOne(ptr);
        }

        items = array;
        length *= 2;
        nextLast = size;
        nextFirst = length - 1;
    }

    private void shrink() {
        T[] array = (T[]) new Object[length / 2];

        int ptr = plusOne(nextFirst);
        int i = 0;
        while (ptr != nextLast) {
            array[i] = items[ptr];
            i++;
            ptr = plusOne(ptr);
        }

        items = array;
        length /= 2;
        nextLast = size;
        nextFirst = length - 1;
    }
}
