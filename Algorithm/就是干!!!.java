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
    public static void perm(int[] nums, int start, int len) {
        if (start == len) {
            System.out.println(Arrays.toString(nums));
        } else {
            for (int i = start; i < nums.length; i++) {
                swap(nums, i, start);
                perm(nums, start + 1, len);
                swap(nums, start, i);
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




}