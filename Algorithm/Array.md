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

- 41 [Find Missing Positive](https://leetcode.com/problems/first-missing-positive/)

missing number 一定在给出的数组大小加一的范围内，即nums.length = 4 则missing number 在
[1, 5]中
```
Java

class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int[] array = new int[nums.length + 2];
        for (int i : nums) {
            if (i > 0 && i <= nums.length + 1) {
                array[i] = 1;
            }
        }
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 0) {
                return i;
            }
        }
        return -1;
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
        int R = matrix.length, C = matrix[0].length;
        
        boolean[][] seen = new boolean[R][C];
        int[] dr = new int[]{0, 1, 0, -1};
        int[] dc = new int[]{1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        
        for (int i = 0; i < R * C; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            
            int newDr = r + dr[di];
            int newDc = c + dc[di];
            
            if (newDr >= 0 && newDr < R && newDc >= 0 && newDc < C && !seen[newDr][newDc]) {
                r = newDr;
                c = newDc;
            } else {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
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