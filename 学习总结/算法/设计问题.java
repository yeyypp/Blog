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
     *
     * inc时如果key存在，则keyMap.put(key,value + 1),如果不存在则keyMap.put(key, 1)
     *          key存在时，value + 1 存在，则removeKey 从源node 此时如果node.set == 0 则从valMap中remove
     *                     在value + 1的node中添加key
     *                     如果value + 1 不存在， 则添加新的value + 1， node 到valMap
     *                     将node添加到原node的前边
     *
     * 以此为例，每一次改变都要考虑valMap，keyMap，node顺序，node.set大小是否为0
     * skip list
     *
     */

    class AllOne {
        private Map<Integer, Node> valMap;
        private Map<String, Integer> keyMap;
        private Node head;
        private Node tail;


        class Node {
            private int value;
            private Set<String> set;
            private Node pre;
            private Node next;

            public Node(int value) {
                this.value = value;
                this.set = new HashSet<>();
                pre = null;
                next = null;
            }
        }


        private void addPreNode(Node node, Node current) {
            Node pre = current.pre;
            pre.next = node;
            node.pre = pre;
            node.next = current;
            current.pre = node;
        }

        private void addNextNode(Node node, Node current) {
            Node next = current.next;
            current.next = node;
            node.pre = current;
            node.next = next;
            next.pre = node;
        }

        private void removeNode(Node node) {
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            next.pre = pre;
        }

        private void removeKeyFromNode(String key, Node node) {
            node.set.remove(key);
            if (node.set.isEmpty()) {
                removeNode(node);
                valMap.remove(node.value);
            }
        }

        private void changeKey(String key, int offset) {
            int oldValue = keyMap.get(key);
            keyMap.put(key, oldValue + offset);
            Node oldNode = valMap.get(oldValue);
            Node newNode = valMap.get(oldValue + offset);
            if (newNode == null) {
                newNode = new Node(oldValue + offset);
                valMap.put(oldValue + offset, newNode);
                if (offset == 1) {
                    addPreNode(newNode, oldNode);
                } else {
                    addNextNode(newNode, oldNode);
                }
            }
            newNode.set.add(key);
            removeKeyFromNode(key, oldNode);
        }

        /** Initialize your data structure here. */
        public AllOne() {
            keyMap = new HashMap<>();
            valMap = new HashMap<>();
            head = new Node(0);
            tail = new Node(0);
            head.next = tail;
            tail.pre = head;
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {
            if (keyMap.containsKey(key)) {
                changeKey(key, 1);
            } else {
                keyMap.put(key, 1);
                if (valMap.containsKey(1)) {
                    valMap.get(1).set.add(key);
                } else {
                    Node node = new Node(1);
                    node.set.add(key);
                    addPreNode(node, tail);
                    valMap.put(1, node);
                }
            }
        }

        /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
        public void dec(String key) {
            if (!keyMap.containsKey(key)) {
                return;
            } else {
                if (keyMap.get(key) == 1) {
                    Node node = valMap.get(1);
                    removeKeyFromNode(key, node);
                    keyMap.remove(key);
                } else {
                    changeKey(key, -1);
                }
            }
        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {
            return head.next == tail ? "" : head.next.set.iterator().next();
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {
            return tail.pre == head ? "" : tail.pre.set.iterator().next();
        }
    }

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */

}