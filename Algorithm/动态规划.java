public class Main {
    /**
     * 最大连续字串和
     */

    public int maxsub(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            // 检查如果dp[i - 1]是负数，则返回nums[i]；
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 硬币问题
     * [1, 3, 5]
     * 组成11
     */
    public int coinchange(int[] coins, int amount) {
        helper(coins, amount, new int[amount]);
    }
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
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            // number 为到res - coin 的最小硬币数
            int number = helper(coins, res - coin, memo);
            if (number >= 0) {
                min = Math.min(number, min);
            }
        }
        memo[res - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return memo[res - 1];
    }

    /**
     * 最长公共子序列
     * C[i, j] = (x[i] = y[j]) C[i - 1, j - 1] + 1;
     *               (x[i] != y[j])  Math.max(C[i - 1, j], C[i, j -1]);
     */

    public static int LCS(String str1, String str2) {
        int a = str1.length();
        int b = str2.length();
        if (a == 0 || b == 0) {
            return 0;
        }
        int[][] matrix = new int[a + 1][b + 1];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = 0;
        }
        for (int j = 0; j < matrix[0].length; j++) {
            matrix[0][j] = 0;
        }
        for (int i = 1; i <= a; i++) {
            for (int j = 1; j <= b; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }
        return matrix[a][b];
    }

    /**
     * 最长递增子序列
     */

    public int LIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int ans = 0;

        for (int i = 1; i < dp.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            ans = Math.max(dp[i],ans);
        }
    }

    /**
     *约瑟夫环
     *      * n个数，删掉第m个，即每隔m - 1个
     *      * 求最后的数的坐标
     *      * 第一轮出列（m - 1）% n的人
     *      * 此时（m + i）% n对应n - 1中序号为i的人
     *      *
     *      * f(N,M) = (f(N - 1, M) + M) % N;
     */

    private int yuesefu(int n, int m) {
        if (n == 1) {
            return 0;
        }
        return (yuesefu(n - 1, m) + m) % n;
    }



}