# Design
- 208 [Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/)
```
Java

class Trie {
    
    private class TrieNode {
        private TrieNode[] links;
        private final int R = 26;
        private boolean isEnd;
        private String word;
        
        private TrieNode() {
            this.links = new TrieNode[R];
        }
        
        private void put(char c, TrieNode node) {
            links[c - 'a'] = node;
        }
        
        private TrieNode getNode(char c) {
            return links[c - 'a'];
        }
        
        private boolean contains(char c) {
            return links[c - 'a'] != null;
        }
        
        private void setEnd() {
            isEnd = true;
        }
        
        private boolean isEnd() {
            return isEnd;
        }
        
        private void setWord(String word) {
            this.word = word;
        }
        
        private String getWord() {
            return word;
        }
    }
    
    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curNode = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curNode.contains(c)) {
                curNode.put(c, new TrieNode());
            }
            curNode = curNode.getNode(c);
        }
        curNode.setEnd();
        curNode.setWord(word);
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode curNode = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curNode.contains(c)) {
                return false;
            }
            curNode = curNode.getNode(c);
        }
        return curNode.isEnd();
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode curNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!curNode.contains(c)) {
                return false;
            }
            curNode = curNode.getNode(c);
        }
        return true;
    }
}
```
- 341 [Flatten Nested List Iterator](https://leetcode.com/problems/flatten-nested-list-iterator/)
```
Java

public class NestedIterator implements Iterator<Integer> {
    
    private Queue<Integer> queue;

    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new LinkedList<>();
       for (NestedInteger n : nestedList) {
           add(n, queue);
       }
    }
    
    private void add(NestedInteger n, Queue<Integer> queue) {
        if (n.isInteger()) {
            queue.offer(n.getInteger());
        } else {
            for (NestedInteger nest : n.getList()) {
                add(nest, queue);
            }
        }
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
```

- 380 [Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/)
```
Java

class RandomizedSet {
    
    private List<Integer> list;
    private Random random;
    private int size;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        this.list = new LinkedList<>();
        this.random = new Random();
        this.size = 0;
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (list.contains(val)) {
            return false;
        }
        list.add(val);
        size++;
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!list.contains(val)) {
            return false;
        }
        Integer item = (Integer) val;
        list.remove(item);
        size--;
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(random.nextInt(size));
    }
}
```