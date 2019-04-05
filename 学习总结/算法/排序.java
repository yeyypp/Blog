public class Main {
    /**
     * 稳定的排序算法
     * 冒泡 n^2, 插入 n^2,归并nlogn
     *
     * 不稳定
     * 堆排 nlogn, 快排 nlogn, 选择 n^2;
     */

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


}