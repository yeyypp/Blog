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