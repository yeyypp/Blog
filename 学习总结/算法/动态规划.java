public class Main {
    /**
     * 53 最大子数组和
     * dp[i]代表以i结尾的子序列的最大值
     * 则dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
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
     * 120 三角形最小路径和
     */

    class Solution {
        public int minTotal(List<List<Integer>> triangle) {
            /**
             * dp[i] = Math.min(dp[i], dp[i + 1]) + data[i];
             * 代表从dp[i]到最底层的路径和等于自身加加上，下边两个相邻中最小的路径和
             * 最后一行的dp[i]等于他们自身，依次往上计算
             * 最底层个数为n，在计算上一层时，个数为n - 1， 所以只需要一个一维数组用来计算
             *
             * class Solution {
             *     public int minimumTotal(List<List<Integer>> triangle) {
             *         int[] dp = new int[triangle.size()];
             *         for (int i = 0; i < dp.length; i++) {
             *             dp[i] = triangle.get(dp.length - 1).get(i);
             *         }
             *         for (int i = triangle.size() - 2; i >= 0; i--) {
             *             for (int j = 0; j < triangle.get(i).size(); j++) {
             *                 dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
             *             }
             *         }
             *         return dp[0];
             *     }
             * }
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
            return helper(coins, amount, new int[amount]);
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
            return memo[res - 1];
        }
    }

    /**
     * 64.最小路径和
     * 有向无环图
     * dp[i] 可以表示从i开始的最长或最短路径，也可以表示到i的最长或最短路径，一般用后者
     * 此题到dp[i]的值等于上边或者左边的最小值加上其本身，dp[i][[j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
     * 记得用memo存储已经计算的值
     */

    class Solution {
        public int minPathSum(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int[][] memo = new int[m][n];
            //初始化第一行和第一列
            memo[0][0] = grid[0][0];
            for (int i = 1; i < m; i++) {
                memo[i][0] = memo[i - 1][0] + grid[i][0];
            }
            for (int j = 1; j < n; j++) {
                memo[0][j] = memo[0][j - 1] + grid[0][j];
            }
            //依次遍历剩余元素
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    memo[i][j] = Math.min(memo[i - 1][j], memo[i][j - 1]) + grid[i][j];
                }
            }
            return memo[m - 1][n - 1];
        }
    }

    /**
     * 121 买股票最佳时机
     * 只能买卖一次
     * profit[i]代表到这个元素能获得的最多利润
     */

    class Solution {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int[] profit = new int[prices.length];
            profit[0] = 0;
            int min = prices[0];
            for (int i = 1; i < prices.length; i++) {
                // 每一次都获得之前的最小值，然后用当下元素减最小值得到profit[i]，在与之前的profit[i - 1]相比
                min = Math.min(min, prices[i]);
                profit[i] = prices[i] - min > profit[i - 1] ? prices[i] - min : profit[i - 1];
            }
            return profit[prices.length - 1];
        }
    }

    /**
     * 221 最大正方形
     * dp[i][j]代表以此为右下角的正方形的边长
     * 等于左，上，左斜上的最小边长加1
     *
     */

    class Solution {
        public int maximalSquare(char[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            int ans = 0;
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] dp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = matrix[i][j] == '0' ? 0 : 1;
                    } else {
                        if (matrix[i][j] == '0') {
                            dp[i][j] = 0;
                        } else {
                            dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                        }
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
            }
            return ans * ans;
        }
    }


}