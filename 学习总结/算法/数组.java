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

    /**
     * 88.合并两个有序数组
     */

    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int[] tem = new int[m + n];
            int k = 0;
            int i = 0;
            int j = 0;
            while (i < m && j < n) {
                if (nums1[i] < nums2[j]) {
                    tem[k++] = nums1[i++];
                } else {
                    tem[k++] = nums2[j++];
                }
            }
            while (i < m) {
                tem[k++] = nums1[i++];
            }
            while (j < n) {
                tem[k++] = nums2[j++];
            }
            System.arraycopy(tem, 0, nums1, 0, m + n);
            return;
        }
    }

    /**
     * 240.搜索二维数组
     * 左至右依次递增
     * 上至下依次递增
     * 所以右上角matrix[i][j]是第一行最大值，是这一列的最小值。
     * 如果target大于matrix[i][j]，则肯定大于第一行所有元素，则删掉第一行
     * 如果target小于matrix[i][j]，则肯定小于这一列所有元素，则删掉这一列
     */

    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }
            int m = matrix.length;
            int n = matrix[0].length;
            int i = 0;
            int j = n - 1;
            while (i < m && j >= 0) {
                if (target < matrix[i][j]) {
                    j--;
                } else if (target > matrix[i][j]) {
                    i++;
                } else {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 509 Fibonacci Number
     */

    class Solution {
        public int fib(int N) {
            if (N < 2) {
                return N;
            }
            int[] dp = new int[N + 1];
            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i < dp.length; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[N];
        }
    }


}