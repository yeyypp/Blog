# Stack
- 155 [Min Stack](https://leetcode.com/problems/min-stack/)
```
Java

class MinStack {
    
    private Deque<Integer> curMin;
    private Deque<Integer> stack;

    /** initialize your data structure here. */
    public MinStack() {
        curMin = new LinkedList<>();
        stack = new LinkedList<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (curMin.size() == 0 || x <= curMin.peek()) {
            curMin.push(x);
        }
    }
    
    public void pop() {
        int a = stack.pop();
        if (a == curMin.peek()) {
            curMin.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return curMin.peek();
    }
}
```

- 388 [Longest Absolute File Path](https://leetcode.com/problems/longest-absolute-file-path/)
```
Java

class Solution {
    public int lengthLongestPath(String input) {
        String[] dir = input.split("\n");
        int max = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        for (String s : dir) {
            // if there is no \t in the string, the lastIndexOf will
            // return -1
            int numberOfTab = s.lastIndexOf("\t") + 1;
            int level = numberOfTab + 1;
            while (level < stack.size()) {
                stack.pop();
            }
            // the curLen has to add the last "\"
            int curLen = stack.peek() + s.length() - numberOfTab + 1;
            stack.push(curLen);
            if (s.contains(".")) {
                // the curLen has to reduce the last "\"
                max = Math.max(max, curLen - 1);
            }
        }
        return max;
    }
}
```

- 1047 [Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/)
```
Java

class Solution {
    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < S.length(); i++) {
            int size = sb.length();
            if (size > 0 && sb.charAt(size - 1) == S.charAt(i)) {
                sb.deleteCharAt(size - 1);
            } else {
                sb.append(S.charAt(i));
            }
        }
        
        return sb.toString();
    }
}
```

- 1209 [Remove All Adjacent Duplicates in String II](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/)
```
Java

class Solution {
    public String removeDuplicates(String s, int k) {
        Deque<Integer> countStack = new LinkedList<>();
        Deque<Character> charStack = new LinkedList<>();
        
        for (char c : s.toCharArray()) {
            if (!charStack.isEmpty() && charStack.peek() == c) {
                countStack.push(countStack.peek() + 1);
            } else {
                countStack.push(1);
            }
            charStack.push(c);
            
            if (countStack.peek() == k) {
                for (int i = 0; i < k; i++) {
                    charStack.pop();
                    countStack.pop();
                }
            }
        }
        
        return convert(charStack);
    }
    
    private String convert(Deque<Character> stack) {
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
```
