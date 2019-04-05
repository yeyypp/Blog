public class Main {
    /**
     * 删除数组中重复元素
     * 返回不重复个数
     */

    class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums == null || nums.length == 0) {
                reutrn 0;
            }
            int i = 0;
            int j = 1;
            int ans = 1;
            while (j < nums.length) {
                if (nums[i] == nums[j]) {
                    j++;
                } else {
                    i++;
                    ans++;
                    nums[i] = nums[j];
                    j++;
                }
            }
            return ans;
        }
    }

    /**
     * 无需数组，找中位数
     */


}