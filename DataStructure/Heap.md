# Heap
```
Java

/**
 * @author ShuaiYe
 * @date 2019/8/9 19:52
 */
public class ArrayMinHeap<T> {

    private static class Node<T> {
        private T item;
        private double priority;

        private Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        private T getItem() {
            return item;
        }

        private double getPriority() {
            return priority;
        }

        @Override
        public String toString() {
            return item.toString() + "," + priority;
        }
    }

    private Node[] contents;
    private int size;

    public ArrayMinHeap() {
        contents = new Node[16];
        size = 0;
        contents[0] = null;
    }

    private int leftIndex(int i) {
        return 2 * i;
    }

    private int rightIndex(int i) {
        return 2 * i + 1;
    }

    private int fatherIndex(int i) {
        return i / 2;
    }

    private boolean isValidIndex(int i) {
        if ((i > size) || (i < 1)) {
            return false;
        }
        return true;
    }

    private Node getNode(int i) {
        if (!isValidIndex(i)) {
            return null;
        }
        return contents[i];
    }

    private void swap(int a, int b) {
        Node temA = getNode(a);
        Node temB = getNode(b);
        contents[a] = temB;
        contents[b] = temA;
    }

    private int min(int a, int b) {
        Node nodeA = getNode(a);
        Node nodeB = getNode(b);
        return nodeA == null ? b : nodeB == null ? a : nodeA.getPriority() > nodeB.getPriority() ? b : a;
    }

    private void swim(int i) {
        if (i <= 1 || !isValidIndex(i)) {
            return;
        }
        int father = fatherIndex(i);
        int min = min(father, i);
        if (min == i) {
            swap(father, i);
            swim(father);
        }
    }

    private void sink(int i) {
        if (i >= size || !isValidIndex(i)) {
            return;
        }
        int left = leftIndex(i);
        int right = rightIndex(i);
        int min = min(min(left, right), i);
        if (min != i) {
            swap(min, i);
            sink(min);
        }
    }

    private void resize(int capacity) {
        Node[] tem = new Node[capacity];
        System.arraycopy(contents, 0, tem, 0, contents.length);
        contents = tem;
    }

    public void insert(T item, double priority) {
        if (size + 1 == contents.length) {
            resize(2 * contents.length);
        }

        contents[size + 1] = new Node(item, priority);
        size++;
        swim(size);
    }

    public T peek() {
        return (T) getNode(1).getItem();
    }

    public T removeMin() {
        Node ans = getNode(1);
        swap(1, size);
        contents[size] = null;
        size--;
        sink(1);
        return (T) ans.getItem();
    }

    public int size() {
        return size;
    }
}
```