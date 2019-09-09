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
