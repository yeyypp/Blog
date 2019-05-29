public class Main {
    /**
     * 215 数组中第k大元素
     * 此题可有多种解法
     */

    //1.排序数组，之后求第nums.length - k个元素 time：nlogn（因为用了Arrays.sort) space：1
    public int findKthLargest(int[] nums, int k) {
        int target = nums.length - k;
        Arrays.sort(nums);
        return nums[target];
    }

    //2.利用最小堆求，Java中可以用PriorityQueue，time：nlogk（因为堆中插入删除复杂度为logk，又因为做了n次） space：k
    public int findKthLargest(int[] nums, int k) {

    }
}