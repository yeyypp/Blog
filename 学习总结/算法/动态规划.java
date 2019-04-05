public class Main {
    /**
     * 最大子数组和
     */

    class Solution {
        public int maxSubArray(int[] nums) {
            /**
             * dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
             * 在nums[i]位置的最大子序和等于前一个的最大加本身，或者等于其自身
             * 因为是等于前一个位置的最大子序和加本身，所以是连续的
             */
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int[] dp = new int[nums.length];
            int ans = (dp[0] = nums[0]);
            for (int i = 1; i < dp.length; i++) {
                dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
                ans = Math.max(ans, dp[i]);
            }
            return ans;
        }
    }

    /**
     * 最长递增子序列
     * 注意：序列是可以不连续的，子串是连续的
     * dp[i] represents the length of the longest increasing subsequence possible considering the array
     * elements upto the ith index only, including ith element;
     * 为获得dp[i]，需要把nums[i]与前边每一个元素比较，如果大于，则记录这个元素的dp[j]
     * maxval = 0；
     * if (nums[i] > nums[j]) {
     *     maxval = Math.max(maxval, dp[j])
     * }
     * 最后dp[i] = 前边最长的序列maxval + 自己本身 1；
     * 在从所有dp元素中选出最大的，即为LIS
     */

    class Solution {
        public int LIS(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int ans = 1;
            int[] dp = new int[nums.length];
            dp[0] = 1;
            for (int i = 1; i < nums.length; i++) {
                int maxval = 0;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        maxval = Math.max(maxval, dp[j]);
                    }
                }
                dp[i] = maxval + 1;
                ans = Math.max(ans, dp[i]);
            }
            return ans;
        }
    }

    /**
     * 三角形最小路径和
     */

    class Solution {
        public int minTotal(List<List<Integer>> triangle) {
            /**
             * dp[i] = Math.min(dp[i], dp[i + 1]) + data[i];
             * 代表从dp[i]到最底层的路径和等于自身加加上，下边两个相邻中最小的路径和
             * 最后一行的dp[i]等于他们自身，依次往上计算
             * 最底层个数为n，在计算上一层时，个数为n - 1， 所以只需要一个一维数组用来计算
             */

            if (triangle == null || triangle.size() == 0) {
                return 0;
            }
            int layer = triangle.size();
            int size = triangle.get(size - 1).size();
            int[] dp = new int[size];

            //先给最后一层赋值
            for (int i = 0; i < size; i++) {
                dp[i] = triangle.get(size - 1).get(i);
            }

            //从倒数第二层开始，依次计算
            for (int i = layer - 2; i >= 0; i--) {
                //创建一个list存储此层的元素
                List<Integer> list = triangle.get(i);
                //j < list.size()即可保证不越界
                for (int j = 0; j < list.size(); j++) {
                    dp[j] = Math.min(dp[j], dp[j + 1]) + list.get(j);
                }
            }
            return dp[0];
        }
    }

    /**
     * 最长递增子序列的个数
     */

    class Solution {
        public int findNumberOfLIS(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int ans = 0;
            int[] length = new int[nums.length];
            int[] count = new int[nums.length];

            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        //如果length[j] + 1 >= length[i]，说明前边还没有相同长度的序列
                        if (length[j] + 1 >= length[i]) {
                            length[i] = length[j] + 1;
                            count[i] = count[j];
                        } else if (length[j] + 1 == length[i]) {
                            //如果length[j] + 1 刚好等于length[i]，说明前边出现过长度相等的序列
                            count[i] += count[j];
                        }
                    }
                }
            }

            int longest = 0;
            //找到最长的长度
            for (Integer i : length) {
                longest = Math.max(longest, i);
            }

            for (int i = 0; i < count.length; i++) {
                if (length[i] == longest) {
                    ans += count[i];
                }
            }
            return ans;
        }
    }

    /**
     * 硬币组合
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1
     * int[] memo ，memo[11]代表组合成11的最小硬币数
     * 加入有 1，2，5那么memo[11] = Math.min(memo[11 - 5], memo[11 - 1], memo[11 - 2]) + 1;
     */

    class Solution {
        public int coinChange(int[] coins, int amount) {

        }
        // res代表剩下的数
        private int helper(int[] coins, int res, int[] memo) {
            if (res < 0) {
                return -1;
            }
            if (res == 0) {
                return 0;
            }
            if (memo[res - 1] != 0) {
                return memo[res - 1];
            }
            int min = Integer.MIN_VALUE;
            for (int coin : coins) {
                int number = helper(coins, res - coin, memo);
                if (number >= 0) {
                    min = Math.min(number, min);
                }
            }
        }
    }
}