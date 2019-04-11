public class Main {
    /**
     * 稳定的排序算法
     * 冒泡 n^2, 插入 n^2,归并nlogn
     *
     * 不稳定
     * 堆排 nlogn, 快排 nlogn, 选择 n^2;
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

    }

    private static void merge(int[] nums, int L, int M, int R) {
        int[] tem = new int[R - L + 1];
        int i = L;
        int j = M + 1;
        int k = 0;

    }

    /**
     * 堆排
     * nlogn
     * 不稳定
     * 建堆复杂度n
     */


}