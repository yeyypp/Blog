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

- 15 [3Sum](https://leetcode.com/problems/3sum/)
```
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int target = 0 - nums[i];
            int low = i + 1, high = nums.length - 1;
            
            while (low < high) {
                int sum = nums[low] + nums[high];
                if (sum == target) {
                    List<Integer> list = new ArrayList<>(3);
                    list.add(nums[i]);
                    list.add(nums[low]);
                    list.add(nums[high]);
                    ans.add(list);
                    
                    low++;
                    high--;
                    
                    while (low < high && nums[low] == nums[low - 1]) {
                        low++;
                    }
                    
                    while (low < high && nums[high] == nums[high + 1]) {
                        high--;
                    }
                    
                } else if (sum < target) {
                    low++;
                } else {
                    high--;
                }
                
                
            }
        }
        
        return ans;
    }
}
```

- 33 [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)
```
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int min = findMin(nums);
        if (nums[min] == target) {
            return min;
        }
        
        int start = target >= nums[0] ? 0 : min;
        int end = target <= nums[nums.length - 1] ? nums.length - 1 : min;
        
        return binarySearch(nums, target, start, end);
    }
    
    private int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
    
    private int binarySearch(int[] nums, int target, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
```

- 41 [First Missing Positive](https://leetcode.com/problems/first-missing-positive/)
Consider the [1], the missing number would be 2.
If all number is at the correct position, the value in nums[i] should be i + 1.
```
class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        
        return n + 1;
        
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```
- 42 [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)
每一个bar能存的水量，是Math.min(leftMax, rightMax) - height[i]，依次遍历即可，用两个数组
记录每一个位置的left，right，max值
```
Java

class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        
        int length = height.length;
        int[] leftMax = new int[length];
        int[] rightMax = new int[length];
        leftMax[0] = height[0];
        rightMax[length - 1] = height[length - 1];
        
        for (int i = 1; i < length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        
        for (int i = length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        
        int ans = 0;
        for (int i = 0; i < length; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }
}
```

- 48 [Rotate Matrix](https://leetcode.com/problems/rotate-image/)
先以左顶点，将边对换，再将左右两边的列，依次对换
```
class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        change(matrix);
        flip(matrix);
    }
    
    private void change(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }
    
    private void flip(int[][] matrix) {
        int i = 0, j = matrix.length - 1;
        while (i < j) {
            for (int a = 0; a < matrix.length; a++) {
                swap(matrix, a, i, a, j);
            }
            i++;
            j--;
        }
    }
    
    private void swap(int[][] matrix, int i, int j, int a, int b) {
            int tem = matrix[i][j];
            matrix[i][j] = matrix[a][b];
            matrix[a][b] = tem;
    }
    
}
```

- 54 [Spiral Matrix](https://leetcode.com/problems/spiral-matrix/)

用两个数组记录横纵坐标的变化，当超出范围时改变横纵坐标

```
Java

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }
        
        int m = matrix.length, n = matrix[0].length;
        int[] dr = new int[]{0, 1, 0, -1};
        int[] dc = new int[]{1, 0, -1, 0};
        int di = 0, dj = 0, pos = 0;
        boolean[][] visited = new boolean[m][n];
        
        for (int count = 0; count < m * n; count++) {
            ans.add(matrix[di][dj]);
            visited[di][dj] = true;
            
            int ndi = di + dr[pos];
            int ndj = dj + dc[pos];
            
            if (isValid(m, n, ndi, ndj) && !visited[ndi][ndj]) {
                di = ndi;
                dj = ndj;
            } else {
                pos = (pos + 1) % 4;
                di = di + dr[pos];
                dj = dj + dc[pos];
            }
        }
        
        return ans;
    }
    
    private boolean isValid(int m, int n, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return false;
        }
        return true;
    }
}

Go

func spiralOrder(matrix [][]int) []int {
    if matrix == nil || len(matrix) == 0 || len(matrix[0]) == 0 {
        return nil
    }
    R := len(matrix)
    C := len(matrix[0])
    ans := make([]int, R * C)
    seen := make([][]bool, R)
    for i := 0; i < R; i++ {
        seen[i] = make([]bool, C)
    }
    dr := []int{0, 1, 0, -1}
    dc := []int{1, 0, -1, 0}
    r, c, di := 0, 0, 0
    
    for i := 0; i < R * C; i++ {
        ans[i] = matrix[r][c]
        seen[r][c] = true
        cr := r + dr[di]
        cc := c + dc[di]
        if cr >= 0 && cr < R && cc >= 0 && cc < C && !seen[cr][cc] {
            r = cr
            c = cc
        } else {
            di = (di + 1) % 4
            r += dr[di]
            c += dc[di]
        }
    }
    return ans
}
```

- 55 [Jump Game](https://leetcode.com/problems/jump-game/)
```
class Solution {
    public boolean canJump(int[] nums) {
        int good = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= good) {
                good = i;
            }
        }
        
        return good == 0;
    }
}
```

- 56 [Merge Intervals](https://leetcode.com/problems/merge-intervals/)
```
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                }
                return a[0] - b[0];
            }
        });
        
        List<int[]> tem = new LinkedList<>();
        int[] cur = null;
        for (int[] interval : intervals) {
            if (cur == null || cur[1] >= interval[0]) {
                cur = merge(cur, interval);
            } else {
                tem.add(cur);
                cur = interval;
            }
        }
        tem.add(cur);
        
        int[][] ans = new int[tem.size()][2];
        for (int i = 0; i < tem.size(); i++) {
            ans[i] = tem.get(i);
        }
        return ans;
    }
    
    private int[] merge(int[] a, int[] b) {
        if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        }
        return new int[]{Math.min(a[0], b[0]), Math.max(a[1],b[1])};
    }
}
```
- 84 [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/)
    
    [leetcode discuss](https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/28902/5ms-O(n)-Java-solution-explained-(beats-96))
```
Java

class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int length = heights.length;
        int[] leftFirstLess = new int[length];
        int[] rightFirstLess = new int[length];
        leftFirstLess[0] = -1;
        rightFirstLess[length - 1] = length;
        
        for (int i = 1; i < length; i++) {
            int leftPoint = i - 1;
            while (leftPoint >= 0 && heights[leftPoint] >= heights[i]) {
                leftPoint = leftFirstLess[leftPoint];
            }
            leftFirstLess[i] = leftPoint;
        }
        
        for (int i = length - 2; i >= 0; i--) {
            int rightPoint = i + 1;
            while (rightPoint < length && heights[rightPoint] >= heights[i]) {
                rightPoint = rightFirstLess[rightPoint];
            }
            rightFirstLess[i] = rightPoint;
        }
        
        int max = 0;
        for (int i = 0; i < length; i++) {
            max = Math.max(max, heights[i] * (rightFirstLess[i] - leftFirstLess[i] - 1));
        }
        return max;
    }
}
```

- 75 [Sort Colors](https://leetcode.com/problems/sort-colors/)
```
class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int start = 0, end = nums.length - 1, cur = 0;
        while (cur <= end) {
            switch(nums[cur]) {
                case 0:
                    swap(nums, start, cur);
                    start++;
                    cur++;
                    break;
                case 1:
                    cur++;
                    break;
                case 2:
                    swap(nums, cur, end);
                    end--;
                    break;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tem = nums[i];
        nums[i] = nums[j];
        nums[j] = tem;
    }
}
```

- 88 [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)
```
Java

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
        
        while (i >= 0) {
            nums1[k--] = nums1[i--];
        }
        
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}
```

- 121 [Best time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)
```
Java

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int minPrice = prices[0];
        int curProfit = 0;
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            minPrice = minPrice < prices[i] ? minPrice : prices[i];
            curProfit = prices[i] - minPrice;
            maxProfit = maxProfit > curProfit ? maxProfit : curProfit;
        }
        return maxProfit;
    }
}
```


- 128 [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/)
First use the set to store all the value in the nums.
Then iterate each one nums[i] to check if the nums[i] - 1 exists in the set.
If not, it means we can take current number as the start to count max sequence.
```
Java

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        
        int max = 1;
        
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int curNum = num;
                int curMax = 1;
                while (set.contains(curNum + 1)) {
                    curNum++;
                    curMax++;
                }
                max = Math.max(max, curMax);
            }
        }
        return max;
    }
}
```

- 152 [Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)
```
class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] maxDp = new int[nums.length];
        int[] minDp = new int[nums.length];
        
        maxDp[0] = nums[0];
        minDp[0] = nums[0];
        
        int ans = maxDp[0];
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                maxDp[i] = Math.max(nums[i], nums[i] * maxDp[i - 1]);
                minDp[i] = Math.min(nums[i], nums[i] * minDp[i - 1]);
            } else {
                maxDp[i] = Math.max(nums[i], nums[i] * minDp[i - 1]);
                minDp[i] = Math.min(nums[i], nums[i] * maxDp[i - 1]);
            }
            
            ans = Math.max(ans, maxDp[i]);
        }
        
        return ans;
    }
}
```

- 189 [Rotate Array](https://leetcode.com/problems/rotate-array/)
Must reverse the whole array first.
```
Java

class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
    
    private void swap(int[] nums, int a, int b) {
        int tem = nums[a];
        nums[a] = nums[b];
        nums[b] = tem;
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
- 581 [Shortest Unsorted Continuous Subarray](https://leetcode.com/problems/shortest-unsorted-continuous-subarray/)
从头遍历，从第一个小于前一个的元素开始一直到尾，记录最小值
从尾遍历，从第一个大于前一个的元素开始一直到头，记录最大值
然后找到最小值，最大值应该在的位置，长度即为答案
```
Java

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                min = Math.min(min, nums[i]);
            }
        }
        for (int j = nums.length - 2; j >= 0; j--) {
            if (nums[j] > nums[j + 1]) {
                max = Math.max(max, nums[j]);
            }
        }
        
        int l, r;
        for (l = 0; l < nums.length; l++) {
            if (min < nums[l]) {
                break;
            }
        }
        for (r = nums.length - 1; r >= 0; r--) {
            if (max > nums[r]) {
                break;
            }
        }
        
        return r < l ? 0 : r - l + 1;
    }
}