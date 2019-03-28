public class Main {

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
}