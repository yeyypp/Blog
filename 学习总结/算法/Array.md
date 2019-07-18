# Array
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