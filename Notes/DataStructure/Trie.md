# Trie
```
Java

public class Trie {
    private class TrieNode {
        private TrieNode[] links;
        private final int R = 26;
        private boolean isEnd;
        private String word;

        private TrieNode() {
            links = new TrieNode[R];
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

    public Trie() {
        root = new TrieNode();
    }

    private String clean(String word) {
        return word.replaceAll("[^a-zA-z]", "").toLowerCase();
    }

    private TrieNode search(String word) {
        String cleanWord = clean(word);
        TrieNode curNode = root;
        for (int i = 0; i < cleanWord.length(); i++) {
            char curC = cleanWord.charAt(i);
            if (curNode.contains(curC)) {
                curNode = curNode.getNode(curC);
            } else {
                return null;
            }
        }
        return curNode;
    }
    
    public boolean searchWord(String word) {
        TrieNode node = search(word);
        return node != null && node.isEnd();
    }
    
    public void insert(String word) {
        String cleanWord = clean(word);
        TrieNode curNode = root;
        for (int i = 0; i < cleanWord.length(); i++) {
            char curC = cleanWord.charAt(i);
            if (!curNode.contains(curC)) {
                curNode.put(curC, new TrieNode());
            }
            curNode = curNode.getNode(curC);
        }
        curNode.setEnd();
        curNode.setWord(word);
    }

    public List<String> wordsByPrefix(String prefix) {
        List<String> ans = new LinkedList<>();
        String cleanPrefix = clean(prefix);
        TrieNode curNode = root;
        for (int i = 0; i < cleanPrefix.length(); i++) {
            char curC = cleanPrefix.charAt(i);
            if (!curNode.contains(curC)) {
                return null;
            }
            curNode = curNode.getNode(curC);
        }

        Deque<TrieNode> stack = new LinkedList<>();
        stack.push(curNode);

        while (!stack.isEmpty()) {
            TrieNode cur = stack.pop();
            if (cur.isEnd()) {
                ans.add(cur.word);
            }
            for (int i = 0; i < 26; i++) {
                if (cur.links[i] != null) {
                    stack.push(cur.links[i]);
                }
            }
        }
        return ans;
    }
}
```