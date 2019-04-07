public class Main {
    /**
     * 最小栈
     * 利用两个stack
     * 一个正常使用
     * 一个存放最小元素
     */

    class MinStack {
        Stack<Integer> stack;
        Stack<Integer> minStack;

        public MinStack() {
            this.stack = new Stack<Integer>();
            this.minStack = new Stack<Integer>();
        }

        public void push(int x) {
            stack.push(x);
            if (minStack.isEmpty() || minStack.peek() >= x) {
                minStack.push(x);
            }
        }

        public void pop() {
            int i = stack.pop();
            if (i == minStack.peek()) {
                minStack.pop();
            }
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    /**
     * LRU
     */

    public class LRU {
        private int capacity;
        private int size;
        private Node head;
        private Node tail;
        private Map<Integer, Node> map;

        class Node {
            private int key;
            private int value;
            private Node pre;
            private Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
                pre = null;
                next = null;
            }
        }

        public LRU(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            head = new Node(0, 0);
            tail = new Node(0, 0);
            map = new HashMap<>();
        }

        private void addNode(Node node) {
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
        }

        private void removeNode(Node node) {
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            next.pre = pre;
        }

        private void moveToHead(Node node) {
            removeNode(node);
            addNode(node);
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            } else {
                Node node = map.get(key);
                moveToHead(node);
                return node.value;
            }
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.value = value;
                moveToHead(node);
            } else {
                Node node = new Node(key, value);
                addNode(node);
                size++;
                if (size > capacity) {
                    removeNode(tail.pre);
                    map.remove(tail.pre.key);
                    size--;
                }
            }
        }
    }

    /**
     * All O(1) operation data structure
     * 通过两个map
     * Map<Intger, Node> valMap
     * Map<String, Integer> keyMap
     * 4   {a, b, c}
     * 3   {z,x}
     * 1   {q}
     */

    public class AllOne {

    }

}