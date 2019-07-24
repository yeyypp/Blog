# Array
- 4 [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)

[Tushar Explaination](https://www.youtube.com/watch?v=LPFhl65R7ww&t=442s)
```
Java

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int x = nums1.length;
        int y = nums2.length;
        int left = 0, right = x;
        while (left <= right) {
            int partitionX = (left + right) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;
            
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];
            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];
            
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((x + y) % 2 == 1) {
                    return (double) Math.max(maxLeftX, maxLeftY);
                } else {
                    return (double) (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                }
            }
            
            if (maxLeftX > minRightY) {
                right = partitionX - 1;
            } else {
                left = partitionX + 1;
            }
        }
        return -1;
    }
}
```

- 41 [Find Missing Positive](https://leetcode.com/problems/first-missing-positive/)

missing number 一定在给出的数组大小加一的范围内，即nums.length = 4 则missing number 在
[1, 5]中
```
Java

class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int[] array = new int[nums.length + 2];
        for (int i : nums) {
            if (i > 0 && i <= nums.length + 1) {
                array[i] = 1;
            }
        }
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
```
- 448 [Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/)
```
Java

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]) - 1;
            if (nums[val] > 0) {
                nums[val] = -nums[val];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
```
```
Go

func findDisappearedNumbers(nums []int) []int {
    var ans []int
    for _, v := range nums {
        val := abs(v) - 1
        if nums[val] > 0 {
            nums[val] = -nums[val]
        }
    }
    for i, _ := range nums {
        if nums[i] > 0 {
            ans = append(ans, i + 1)
        }
    }
    return ans
}

func abs(a int) int {
    if a < 0 {
        return -a
    }
    return a
}
```