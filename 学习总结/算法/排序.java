public class Main {
    /**
     * 稳定的排序算法
     * 冒泡 n^2, 插入 n^2,归并nlogn
     *
     * 不稳定
     * 堆排 nlogn, 快排 nlogn, 选择 n^2;
     *
     * 快排优化https://blog.csdn.net/insistGoGo/article/details/7785038
     *
     * 为什么用快排不用堆排
     * 因为堆排中比较的都是不相邻的元素，而数据读取时，先从内存读到缓存，而一般读到缓存中会是需要的数据与其周围的数据，
     * 而堆排比较的是不相邻的元素，会造成多次从内存读取到缓存
     *
     *
     */

    /**
     * Binary Search
     */

    public static int binarySearch(int[] nums, int target) {
        Arrays.sort(nums);
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 快排
     * 不稳定
     * nlogn
     */

    public void QuickSort(int[] nums) {
        split(nums, 0, nums.length - 1);
    }

    private void split(int[] nums, int low, int high) {
        if (low > high) {
            return;
        }
        int pivot = findPivot(nums, low, high);
        split(nums, low, pivot - 1);
        split(nums, pivot + 1, high);
    }

    private int findPivot(int[] nums, int low, int high) {

        int key = nums[low];
        while (low < high) {
            while (low < high && key < nums[high]) {
                high--;
            }
            nums[low] = nums[high];
            while (low < high && key > nums[low]) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = key;
        return low;
    }

    private void swap(int[] nums, int i, int j) {
        int tem = nums[i];
        nums[i] = nums[j];
        nums[j] = tem;
    }

    /**
     * 归并
     * 稳定
     * nlogn
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
     * 堆排
     * nlogn
     * 不稳定
     * 建堆复杂度n
     * 在top k 问题中，求最小的k个值，用最大堆，反之最小堆
     * （也可以用类似快排的方法求）
     * 例如：求最小k个数
     * 先设一个k大小的数组存放结果，然后将元素依次放进数组中，并堆化
     * 当元素小于堆顶时，替换堆顶元素，并堆化
     * 最后数组中元素为所求元素
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
        if (rightNode <= length && nums[rightNode] > nums[maxNode]) {
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
    }


}