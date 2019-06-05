public class Main {

    /**
     * 150 逆波兰表达式
     * 将元素从前往后依次入栈，出现运算符时，pop出两个元素，计算后push回栈，最后栈中的元素就为计算后的值
     */

    class Solution {
        public int evalRPN(String[] tokens) {
            if (tokens == null || tokens.length == 0) {
                return 0;
            }
            Stack<Integer> stack = new Stack<>();
            int a = 0, b = 0;
            for (String s : tokens) {
                switch (s) {
                    case "+" :
                        stack.push(stack.pop() + stack.pop());
                        break;
                    case "-" :
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b - a);
                        break;
                    case "*" :
                        stack.push(stack.pop() * stack.pop());
                        break;
                    case "/" :
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b / a);
                        break;
                    default :
                        stack.push(Integer.parseInt(s));
                        break;
                }
            }
            return stack.pop();
        }
    }

    /**
     * 227 基本计算器2
     * 用栈存储计算的元素，一个int num 存储当前值，一个sign存储当前运算符号
     * 如 123 + 2 * 4 可以看作 + 123 + 2 * 4 sign = ‘+’
     */
    class Solution {
        public int calculate(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            Stack<Integer> stack = new Stack<>();
            //首先num = 0， sign = +
            int num = 0;
            char sign = '+';

            for (int i = 0; i < s.length(); i++) {
                if (Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                }
                //当遇到不是数字的字符且不是空格 或者到最后一位了，再根据之前的符号和值，压入栈中
                if ((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i)) || i == s.length() - 1) {
                    if (sign == '+') {
                        stack.push(num);
                    }
                    if (sign == '-') {
                        stack.push(-num);
                    }
                    if (sign == '*') {
                        stack.push(stack.pop() * num);
                    }
                    if (sign == '/') {
                        stack.push(stack.pop() / num);
                    }
                    num = 0;
                    sign = s.charAt(i);
                }
            }
            //最后将栈中的值依次相加得到最后的值
            int ans = 0;
            for (int i : stack) {
                ans += i;
            }
            return ans;
        }
    }

    /**
     * 239 滑动窗口最大值
     * 使用deque存储元素的坐标，当元素坐标不在窗口内时，将头元素pop出去，每从尾新加入一个坐标后，用它在数据中对应的数与前一个比较
     * 大小，大于，则将前一个从尾部pop出，因为a[x] < a[i] 且 x < i 则a[x] 永远不可能是最大值 注意每一个a[i]都代表以此结尾的窗口
     * 此时从头部pop出的坐标的元素就为此时窗口的最大值
     */

    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {

            //首先判断数据是否合理
            if (nums == null || nums.length == 0) {
                return new int[0];
            }

            //Deque存储元素坐标，int[] ans 存储答案 大小为nums.length - k + 1
            Deque<Integer> deque = new ArrayDeque<>();
            int[] ans = new int[nums.length - k + 1];
            // int tem 记录坐标
            int tem = 0;

            //因为i代表以此结尾的窗口，所以需要小于nums.length
            for (int i = 0; i < nums.length; i++) {

                //首先判断是否为空，不为空则判断新加入的数据是否大于队尾数据，大于则移除队尾
                while (!deque.isEmpty()) {
                    if (nums[i] > deque.peekLast()) {
                        deque.pollLast();
                    } else {
                        //当不大于队尾了就结束循环
                        break;
                    }
                }

                //判断完后,在队尾加入新数据
                deque.offerLast(i);

                //判断此时队头数据是否在窗口里，不在则剔除
                while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                    deque.pollFirst();
                }

                //此时队头坐标元素就为此时窗口最大值，加入到ans数组中
               if (i >= k - 1) {
                   //说明i在窗口里，当i小于时，说明还没有形成窗口
                   ans[tem] = nums[deque.pollFirst()];
                   tem++;
               }
            }
            return ans;
        }
    }

    /**
     * 341 扁平化迭代器
     *
     */

    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *
     *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds, if it holds a single integer
     *     // Return null if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // @return the nested list that this NestedInteger holds, if it holds a nested list
     *     // Return null if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */
    public class NestedIterator implements Iterator<Integer> {
        private Stack<NestedInteger> stack = new Stack<>();

        public NestedIterator(List<NestedInteger> nestedList) {
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                //判断栈顶是否为整数
                NestedInteger cur = stack.peek();
                if (cur.isInteger()) {
                    return true;
                }
                //将不是整数的nestedList弹出，依次将里边的元素加入到栈中，再循环判断栈顶是否为一个整数
                stack.pop();
                for (int i = cur.getList().size() - 1; i >= 0; i--) {
                    stack.push(cur.getList().get(i));
                }
            }
            return false;
        }
    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */




    /**
     * 378 有序矩阵第k小元素
     */

    class Solution {
        public int kthSmallest(int[][] matrix, int k) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (o1 < o2) {
                        return 1;
                    }
                    if (o1 > o2) {
                        return -1;
                    }
                    return 0;
                }
            });
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (maxHeap.size() < k || matrix[i][j] < maxHeap.peek()) {
                        maxHeap.offer(matrix[i][j]);
                        if (maxHeap.size() > k) {
                            maxHeap.poll();
                        }
                    }
                }
            }
            return maxHeap.peek();
        }
    }

    /**
     * 347 前k个高频元素
     */

    class Solution {
        public List<Integer> topKFrequent(int[] nums, int k) {
            PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(10, new Comparator<Map.Entry<Integer, Integer>>() {
                @Override
                public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                    return o2.getValue() - o1.getValue();
                }
            });
            Map<Integer, Integer> map = new HashMap<>();
            for (int i : nums) {
                if (map.containsKey(i)) {
                    map.put(i, map.get(i) + 1);
                } else {
                    map.put(i, 1);
                }
            }
            for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                maxHeap.offer(e);
            }
            List<Integer> ans = new ArrayList<>(k);
            for (int i = 0; i < k; i++) {
                ans.add(maxHeap.poll().getKey());
            }
            return ans;
        }
    }


}