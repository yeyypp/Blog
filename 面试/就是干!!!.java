import java.util.Arrays;
import java.util.Random;

/**
 * 稳定的 冒泡，归并，插入
 */
public class Main {
    public static void main(String[] args) {

        int[] nums = new int[]{4,3,7,9,2,4,7};
        minHeap(nums);
        System.out.println(Arrays.toString(nums));


    }

    private static void swap(int[] nums, int i, int j) {
        int tem = nums[i];
        nums[i] = nums[j];
        nums[j] = tem;
    }

    /**
     * bubble sort
     */

    public static void bubble(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                }
            }
        }
    }

    /**
     *
     * select sort
     */

    public static void select(int[] nums) {
        for (int i = 0; i < nums.length; i++){
            int min = i;
            for (int j = i+ 1; j < nums.length - 1; j++) {
                if (nums[min] > nums[j]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(nums, min, i);
            }
        }
    }


    /**
     * insert
     * @param nums
     */
    public static void insert(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int j = i + 1;
            while (j > 0) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                    j--;
                }
            }

        }
    }

    /**
     * 全排列
     * @param nums
     * @param start
     * @param len
     */
    public static void Perm(int[] nums) {
        perm(nums, 0, nums.length - 1);
    }

    private static void perm(int[] nums, int start, int length) {
        if (start == length) {
            System.out.println(Arrays.toString(nums));
        } else {
            for (int i = start; i < nums.length; i++) {
                swap(nums, i, start);
                perm(nums, start + 1, length);
                swap(nums, i, start);
            }
        }
    }

    /**
     * quick sort
     * @param nums
     */
    public static void quickSort(int[] nums) {
        quick(nums, 0, nums.length - 1);
    }

    private static void quick(int[] nums, int start, int end) {
        if (start < end) {
            int pivot = partition(nums, start, end);
            quick(nums, start, pivot - 1);
            quick(nums, pivot + 1, end);
        }

    }

    private static int partition(int[] nums, int start, int end) {
        int key = nums[start];

        while (start < end) {
            while (start < end && nums[end] >= key) {
                end--;
            }
            nums[start] = nums[end];
            while (start < end && nums[start] <= key) {
                start++;
            }
            nums[end] = nums[start];
        }
        nums[start] = key;
        return start;
    }

    /**
     * heap sort
     * @param nums
     * @param node
     * @param length
     */
    /**
     * heap is a complete tree, missing items only at the bottom
     * time complex = nlogn;
     * non-stable;
     * father node = i
     * left child = 2 * i + 1;
     * right child = 2 * i + 2;
     * father = (i - 1) / 2;
     * 最小的k个元素用大顶堆
     * 最大的k个用小顶堆
     *
     */
    private static void heapHelper(int[] nums, int node, int length) {
        int leftNode = (node << 1) + 1;
        int rightNode = leftNode + 1;

        //如果左节点已经大于长度限制了，则返回
        if (leftNode > length) {
            return;
        }

        //先设最大节点为左节点
        int maxNode = leftNode;
        //如果右节点在长度里，且值大于左节点
        if (rightNode <= length && nums[rightNode] > nums[leftNode]) {
            maxNode = rightNode;
        }

        // 将左右节点中最大的，与根比较
        if (nums[maxNode] > nums[node]) {
            swap(nums, maxNode, node);
            heapHelper(nums, maxNode, length);
        }
    }

    public static void HeapSort(int[] nums) {
        //从第一个非叶子节点开始，构造最大堆
        int start = (nums.length - 2) / 2;
        for (int i = start; i >= 0; i--) {
            heapHelper(nums, i, nums.length - 1);
        }

        //然后依次交换最后一个元素与第一个元素，再使剩下的元素为最大堆
        for (int i = nums.length - 1; i > 0; i--) {
            swap(nums, 0, i);
            heapHelper(nums, 0, i - 1);
        }
    }

    /**
     * shuffle,每次取最后一个和他之前的随机一个交换
     * @param nums
     */
    public static void Shuffle(int[] nums) {
        Random random = new Random();
        for (int i = nums.length - 1; i > 1; i--) {
            swap(nums, i, random.nextInt(i - 1));
        }
    }


    /**
     * minHeap
     * @param nums
     */
    public static void minHeap(int[] nums) {
        int start = (nums.length - 2) / 2;
        for (int i = start; i >= 0; i--) {
            minHeapHelper(nums, i, nums.length - 1);
        }

        for (int i = nums.length - 1; i > 0; i--) {
            swap(nums, 0, i);
            minHeapHelper(nums, 0, i - 1);
        }
    }

    private static void minHeapHelper(int[] nums, int node, int length) {
        int left = (node << 1) + 1;
        int right = left + 1;
        if (left > length) {
            return;
        }
        int min = left;
        if (right <= length && nums[right] < nums[min]) {
            min = right;
        }
        if (nums[min] < nums[node]) {
            swap(nums, min, node);
            minHeapHelper(nums, min, length);
        }
    }

    /**
     * merge sort
     * @param nums
     */
    public static void mergeSort(int[] nums) {
        split(nums, 0, nums.length - 1);
    }

    private static void split(int[] nums, int L, int R) {
        // 当位置相等时，说明只有一个数
        if (L == R) {
            return;
        }
        int M = (L + R) / 2;
        //拆分左右
        split(nums, L, M);
        split(nums, M + 1, R);
        //合并
        merge(nums, L, M, R);
    }

    private static void merge(int[] nums, int L, int M, int R) {
        // 创建一个临时数组
        int[] tem = new int[R - L + 1];
        //左指针
        int i = L;
        //右指针
        int j = M + 1;
        //新数组坐标
        int k = 0;

        while (i <= M && j <= R) {
            if (nums[i] < nums[j]) {
                tem[k] = nums[i];
                k++;
                i++;
            } else {
                tem[k] = nums[j];
                k++;
                j++;
            }
        }

        //若此时左边还有剩的，则放进tem中
        while (i <= M) {
            tem[k] = nums[i];
            k++;
            i++;
        }

        //右边有剩的
        while (j <= R) {
            tem[k] = nums[j];
            k++;
            j++;
        }

        //把临时数组中的元素覆盖原数组
        for (int a = 0; a < tem.length; a++) {
            nums[a + L] = tem[a];
        }
    }

    /**
     * 前序
     * @param node
     */
    public static void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void preOrder(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        Node tem = null;
        while (!stack.isEmpty()) {
            tem = stack.pop();
            if (tem != null) {
                System.out.print(tem.val);
                stack.push(tem.right);
                stack.push(tem.left);
            }
        }
    }


    /**
     * 中序遍历
     * @param root
     */
    public static void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val);
        inOrder(root.right);
    }

    public static void inOrder(Node root) {
        Stack<Node> stack = new Stack<>();
        Node tem = root;
        while (tem != null || !stack.isEmpty()) {
            if (tem != null) {
                stack.push(tem);
                tem = tem.left;
            } else {
                tem = stack.pop();
                System.out.print(tem.val);
                tem = tem.right;
            }
        }
    }

    /**
     * 后序遍历
     * @param node
     */
    public static void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode last = null;
        TreeNode tem = root;


        while (tem != null || !stack.isEmpty()) {
            if (tem != null) {
                stack.push(tem);
                tem = tem.left;
            } else {
                TreeNode node = stack.peek();
                if (node.right != null && node.right != last) {
                    tem = node.right;
                } else {
                    stack.pop();
                    tem = null;
                    last = node;
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    /**
     * 最大。最小深度
     * @param root
     * @return
     */
  public static int maxHeight(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxHeight(root.left), maxHeight(root.right));
  }




    /**
     * 层次
     * @param root
     */
  public void levelOrder(Node root) {
      if (root == null) {
          return;
      }
      Queue<Node> queue = new LinkedList<>();
      queue.offer(root);
      Node tem = null;
      while (queue.size() > 0) {
          int size = queue.size();
          for (int i = 0; i < size; i++) {
              tem = queue.poll();
              System.out.print(tem.val);
              if (tem.left != null) {
                  queue.offer(tem.left);
              }
              if (tem.right != null) {
                  queue.offer(tem.right);
              }
          }
      }
  }

    /**
     * 合并二叉树
     */

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            TreeNode t = null;
            if (t1 == null) {
                return t2;
            }
            if (t2 == null) {
                return t1;
            }
            if (t1 != null && t2 != null) {
                t = new TreeNode(t1.val + t2.val);
                t.left = mergeTrees(t1.left, t2.left);
                t.right = mergeTrees(t1.right, t2.right);
            }
            return t;
        }
    }

    /**
     * 翻转二叉树
     */

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode right = invertTree(root.right);
            TreeNode left = invertTree(root.left);
            root.right = left;
            root.left = right;
            return root;
        }
    }

    /**
     * 非递归版翻转
     */

    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode current = null;
            TreeNode temp = null;
            queue.offer(root);
            while (!queue.isEmpty()) {
                current = queue.poll();
                temp = current.left;
                current.left = current.right;
                current.right = temp;
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            return root;
        }
    }

    /**
     * 将数组变成二叉搜索树
     * 先把数组排序
     */
    public class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            if (nums.length == 0 || nums == null) {
                return null;
            }
            return helper(nums, 0, nums,length - 1);
        }
        private TreeNode helper(int[] nums, int low, int high) {
            if (low > high) {
                return null;
            }
            int mid = (low + high) / 2;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = helper(nums, low, mid - 1);
            node.right = helper(nums, mid + 1, high);
            return node;
        }
    }

    /**
     * 把链表编程bst
     */

    class Solution {
        public TreeNode sortedListToBST(ListNode head) {
            List<Integer> array = new ArrayList<>();
            while (head != null) {
                array.add(head.val);
                head = head.next;
            }
            return helper(array, 0, array.size() - 1);
        }

        private TreeNode helper(List<Integer> array, int low, int high) {
            if (low > high) {
                return null;
            }
            int mid = (low + high) / 2;
            TreeNode node = new TreeNode(array.get(mid));
            node.left = helper(array, low, mid - 1);
            node.right = helper(array, mid + 1, high);
            return node;
        }
    }

    /**
     *
     * 最近公共父亲
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) {
                return root;
            }
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            return left == null ? right : right == null ? left : root;
        }
    }

    class Solution {
        public TreeNode invertTree(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            if (root == null) {
                return null;
            }
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                TreeNode tem = node.left;
                node.left = node.right;
                node.right = tem;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            return root;
        }
    }

    /**
     * 朋友圈
     * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，
     * 那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
     *给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，
     * 否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
     */

    public class Solution {
        public int findCircleNum(int[][] M) {
            int count = 0;
            boolean[] visited = new boolean[M.length];
            Arrays.fill(visited, false);
            for (int i = 0; i < M.length; i++) {
                if (!visited[i]) {
                    count++;
                    dfs(M, visited, i);
                }
            }
            return count;
        }

        private void dfs(int[][] M, boolean[] visited, int person) {
            for (int other = 0; i < M.length; i++) {
                if (M[person][other] == 1 && !visited[other]) {
                    visited[other] = true;
                    dfs(M, visited, other);
                }
            }
        }
    }

    /**
     * 给出一个区间的集合，请合并所有重叠的区间。
     */

    public class Solution {
        /**
         * Definition for an interval.
         * public class Interval {
         *     int start;
         *     int end;
         *     Interval() { start = 0; end = 0; }
         *     Interval(int s, int e) { start = s; end = e; }
         * }
         */
        public List<Interval> merge(List<Interval> intervals) {
            Collections.sort(intervals, new intervalCom());
            LinkedList<Interval> ans = new LinkedList<>();
            for (Interval i : intervals) {
                if (ans.size() == 0 || ans.getLast().end < i.start) {
                    ans.add(i);
                } else {
                    ans.getLast().end = Math.max(ans.getLast().end, i.end);
                }
            }
            return ans;
        }

        public class intervalCom implements Comparator<Interval> {
            @Override
            public int compare(Interval in1, Interval in2) {
                return in1.start < in2.start ? -1 : in1.start == in2.start ? 0 : 1;
            }
        }
    }

    /**
     * 接雨水
     */

    public class Solution {
        public int trap(int[] height) {
            int ans = 0;
            int length = height.length;
            if (length == 0) {
                reutrn 0;
            }
            int[] leftMax = new int[length];
            int[] rightMax = new int[length];

            leftMax[0] = height[0];
            for (int i = 1; i < length - 1; i++) {
                leftMax[i] = Math.max(leftMax[i - 1], height[i]);
            }

            rightMax[length - 1] = height[length - 1];
            for (int i = length - 2; i > 0; i--) {
                rightMax[i] = Math.max(rightMax[i + 1], height[i]);
            }

            for (int i = 1; i < length - 1; i++) {
                ans += Math.min(leftMax[i], rightMax[i]) - height[i];
            }
            return ans;
        }
    }

    /**
     * 合并有序链表
     *
     */


    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l2.next, l1);
                return l2;
            }
        }
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode pre = null;
            ListNode tem = null;
            while (head != null) {
                tem = head.next;
                head.next = pre;
                pre = head;
                head = tem;
            }
            return pre;
        }
    }

    /**
     * 链表中点
     */

    class Solution {
        public ListNode mid(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }

    /**
     * 链表相加
     */

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode ans = new ListNode(0);
            ListNode curr = ans;
            int carry = 0;
            while (l1 != null || l2 != null) {
                int x = (l1 != null) ? l1.val : 0;
                int y = (l2 != null) ? l2.val : 0;
                int sum = x + y + carry;
                carry = sum / 10;
                curr.next = new ListNode(sum % 10);
            }
        }
    }

    public class Solution {
        public int min(int n) {
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 1;

            dp[n] = Main.min(dp[n - 1] + 1, dp[n / 2] + 1);
            return dp[n];
        }
    }

    /**
     * 删除链表重复
     *
     */

    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode curr = head;
            while (curr != null && curr.next != null) {
                if (curr.val == curr.next.val) {
                    curr.next = curr.next.next;
                } else {
                    curr = curr.next;
                }
            }
            return head;
        }
    }

    /**
     * 排序链表
     */

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode sortList(ListNode head) {
           if (head == null || head.next == null) {
               return head;
           }
           ListNode fast = head;
           ListNode slow = head;
           ListNode pre = null;
           while (fast != null && fast.next != null) {
               pre = slow;
               slow = slow.next;
               fast = fast.next.next;
           }
           pre.next = null;
           ListNode l1 = sortList(head);
           ListNode l2 = sortList(slow);
           return mrege(l1, l2);

        }

        private ListNode merge(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            if (l1.val < l2.val) {
                l1.next = merge(l1.next, l2);
                return l1;
            } else {
                l2.next = merge(l2.next, l1);
                return l2;
            }
        }
    }

    /**
     * 合并有序链表
     */

    public class Solution {
        public ListNode merge(ListNode l1, ListNode l2) {
            ListNode head = new ListNode(0);
            ListNode tem = head;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    tem.next = l1;
                    l1 = l1.next;
                } else {
                    tem.next = l2;
                    l2 = l2.next;
                }
                tem = tem.next;
            }
            while (l1 != null) {
                tem.next = l1;
                l1 = l1.next;
                tem = tem.next;
            }
            while (l2 != null) {
                tem.next = l2;
                l2 = l2.next;
                tem = tem.next;
            }
            return head.next;
        }
    }

    /**
     * 合并K个排序链表
     */
    public class Solution {

    }

    /**
     * 最大正方形
     *
     */
    class Solution {
        public int maxmalSquare(char[][] matrix) {
            /**
             * dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
             * dp[i][j]为以i，j为右底角的正方形的最大边长，等于左，上，左斜上的最小边长加1；
             */
            //边界
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            int ans = 0;
            int row = matrix.length;
            int column = matrix[0].length;
            int[][] dp = new int[row][column];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    //先判断是否为最外侧的
                    if (i == 0 || j == 0) {
                        dp[i][j] = matrix[i][j] == '0' ? 0 : 1;
                    } else {
                        //判断matrix[i][j]是否为0
                        if (matrix[i][j] == '0') {
                            dp[i][j] = 0;
                        } else {
                            dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                        }
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
            }
            return ans * ans;

        }
    }








}