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
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i : nums) {
            if (minHeap.size() < k) {
                minHeap.offer(i);
            } else if (i > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(i);
            }
        }
        return minHeap.peek();
    }

    //3.用快排思想
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1, target = nums.length - k;
        int pivot = findPivot(nums, left, right);
        while (target != pivot) {
            if (target > pivot) {
                pivot = findPivot(nums, pivot + 1, right);
            } else {
                pivot = findPivot(nums, left, pivot - 1);
            }
        }
        return nums[pivot];
    }

    public int findPivot(int[] nums, int left, int right) {
        int key = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= key) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] < key) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = key;
        return left;
    }
}