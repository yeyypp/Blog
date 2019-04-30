public class Main {
    /**
     * 1.两数之和
     * 可以排序后，用前后两个指针依次判断
     * 也可以直接用map存储值和坐标
     */

    class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {

                if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i) {
                    return new int[]{map.get(target - nums[i]), i};
                }
                map.put(nums[i], i);
            }
            return new int[0];
        }
    }

    /**
     * 15.三数之和
     * 返回的数不能有重复如 1 0 1 -1， 只能返回一个 1 0 -1
     *
     */

    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> ans = new LinkedList<>();
            if (nums == null || nums.length == 0) {
                return ans;
            }
            //先排序数组
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                // 首先判断固定值，当下一个数与前一个数相等时，直接跳过
                if (i != 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                // 设定两端值
                int start = i + 1;
                int end = nums.length - 1;
                while (start < end) {
                    // sum 在循环里
                    int sum = nums[i] + nums[start] + nums[end];
                    if (sum == 0) {
                        List<Integer> list = new LinkedList<>();
                        list.add(nums[i]);
                        list.add(nums[start]);
                        list.add(nums[end]);
                        ans.add(list);
                        start++;
                        end--;
                        //当前后两个数相等时，直接跳过
                        while (start < end && nums[start] == nums[start - 1]) {
                            start++;
                        }
                        while (start < end && nums[end] == nums[end + 1]) {
                            end--;
                        }
                    } else if (sum < 0) {
                        start++;
                    } else if (sum > 0) {
                        end--;
                    }
                }
            }
            return ans;
        }
    }

    /**
     * 18 四数之和
     * 与三数之和类似
     */

    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> ans = new LinkedList<>();
            if (nums == null || nums.length == 0) {
                return ans;
            }
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 3; i++) {
                if (i != 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                for (int j = i + 1; j < nums.length - 2; j++) {
                    if (j != i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }
                    int start = j + 1;
                    int end = nums.length - 1;
                    while (start < end) {
                        int sum = nums[i] + nums[j] + nums[start] + nums[end];
                        if (sum == target) {
                            List<Integer> list = new LinkedList<>();
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[start]);
                            list.add(nums[end]);
                            ans.add(list);
                            start++;
                            end--;
                            while (start < end && nums[start] == nums[start - 1]) {
                                start++;
                            }
                            while (start < end && nums[end] == nums[end + 1]) {
                                end--;
                            }
                        } else if (sum > target) {
                            end--;
                        } else {
                            start++;
                        }
                    }
                }
            }
            return ans;
        }
    }


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
     * 54 螺旋矩阵
     * 层层递进
     * r1 = 0 c1 = 0 r2 = matrix.length - 1 c2 = matrix[0].length - 1
     */

    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> ans = new LinkedList<>();
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return ans;
            }
            int r1 = 0, r2 = matrix.length - 1, c1 = 0, c2 = matrix[0].length - 1;
            while (r1 <= r2 && c1 <= c2) {
                for (int c = c1; c <= c2; c++) {
                    ans.add(matrix[r1][c]);
                }
                for (int r = r1 + 1; r <= r2; r++) {
                    ans.add(matrix[r][c2]);
                }
                if (r1 < r2 && c1 < c2) {
                    for (int c = c2 - 1; c > c1; c--) {
                        ans.add(matrix[r2][c]);
                    }
                    for (int r = r2; r > r1; r--) {
                        ans.add(matrix[r][c1]);
                    }
                }
                r1++;
                c1++;
                r2--;
                c2--;
            }
            return ans;
        }
    }

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
     * 454 四数相加
     * 四个数组
     * 两个数组为一组，求他们所有的和，存在map中
     * 再在两个个数组的和中，找是否有相应的值
     */

    class Solution {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            Map<Integer, Integer> map = new HashMap<>();
            int count = 0;
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    int sum = A[i] + B[j];
                    if (map.containsKey(sum)) {
                        map.put(sum, map.get(sum) + 1);
                    } else {
                        map.put(sum, 1);
                    }

                }
            }

            for (int i = 0; i < C.length; i++) {
                for (int j = 0; j < D.length; j++) {
                    int sum = C[i] + D[j];
                    if (map.containsKey(-sum)) {
                        count += map.get(-sum);
                    }
                }
            }
            return count;
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