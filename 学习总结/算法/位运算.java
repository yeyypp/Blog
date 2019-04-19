public class Main {
    /**
     * 136 只出现一次的数字
     * 异或预算
     * 1 0 = 1
     * 1 1 = 0
     * 0 0 = 0
     */

    class Solution {
        public int singleNumber(int[] nums) {
            int ans = 0;
            for (int i : nums) {
                ans ^= i;
            }
            return ans;
        }
    }
}