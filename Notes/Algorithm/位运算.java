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

    /**
     * 268 缺少的数字
     * 可以用求和公式得出sum，再一个一个减，最后剩下得就是缺少的
     * 或者用异或运算，0-n异或，nums[0]-nums[n - 1]异或，两者最后异或得到的就为消失得数字
     * 例如 0 1 2 4
     * 则 0 ^ 0 ^ 1 ^ 1 ^ 2 ^ 2 ^ 3 ^ 4 ^ 4
     */

    class Solution {
        public int findMiss(int[] nums) {
            int missing = nums.length;
            for (int i = 0; i < nums.length; i++) {
                missing ^= i ^ nums[i];
            }
            return missing;
        }
    }
}