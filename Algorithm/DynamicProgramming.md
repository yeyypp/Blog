# Dynamic Programming

- factorial
```
public static int factorial(int n) {
        int cur = 0;
        int pre = 2;

        for (int i = 3; i <= n; i++) {
            cur = i * pre;
            pre = cur;
        }
        return cur;
    }
```

- fib
```
public static int fib(int n) {
        if (n == 1) {
            return 0;
        }

        if (n == 2) {
            return 1;
        }
        int pre = 0, next = 1, cur = 0;
        for (int i = 3; i <= n; i++) {
            cur = pre + next;
            pre = next;
            next = cur;
        }
        return cur;
    }
```

- 42 [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)
```
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int length = height.length;
        int[] left = new int[length];
        int[] right = new int[length];
        
        left[0] = height[0];
        right[length - 1] = height[length - 1];
        
        for (int i = 1; i < length; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        
        for (int j = length - 2; j >= 0; j--) {
            right[j] = Math.max(right[j + 1], height[j]);
        }
        
        int ans = 0;
        
        for (int i = 0; i < length; i++) {
            ans += (Math.min(left[i], right[i]) - height[i]);
        }
        
        return ans;
    }
}
```

- 62 [Unique Paths](https://leetcode.com/problems/unique-paths/)
```
Java
class Solution {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 1;
        }
        int[][] dp = new int[n][m];
        dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = 1;
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[n - 1][m - 1];
    }
}
```
- 70 [Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)
```
class Solution {
    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }
        int first = 1, second = 2;
        int ans = 0;
        for (int i = 3; i <= n; i++) {
            ans = first + second;
            first = second;
            second = ans;
        }
        
        return ans;
    }
}
```

- 91 [Decode Ways](https://leetcode.com/problems/decode-ways/)
```
Java

class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i < dp.length; i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i - 1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }
}
```

- 121 [Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)
```
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int minPrice = prices[0];
        int ans = 0;
        
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            ans = Math.max(ans, prices[i] - minPrice);
        }
        
        return ans;
    }
}
```

- 122 [Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/)
```
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int ans = 0;
        
        int curPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > curPrice) {
                ans += prices[i] - curPrice;
            }
            curPrice = prices[i];
        }
        
        return ans;
    }
}
```

- 152 [Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)
```
Java
class Solution {
    public int maxProduct(int[] nums) {
    // Because there may be exist negative number, so two array need to be used to store the max and min
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = nums[0];
        min[0] = nums[0];
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                max[i] = Math.max(min[i - 1] * nums[i], nums[i]);
                min[i] = Math.min(max[i - 1] * nums[i], nums[i]);
            } else {
                max[i] = Math.max(max[i - 1] * nums[i], nums[i]);
                min[i] = Math.min(min[i - 1] * nums[i], nums[i]);
            }
            ans = Math.max(ans, max[i]);
        }
        return ans;
    }
}
```

- 198 [House Robber](https://leetcode.com/problems/house-robber/)
```
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        
        return dp[nums.length - 1];
    }
}
```

- 279 [Perfect Squares](https://leetcode.com/problems/perfect-squares/)
```
Java
class Solution {
    public int numSquares(int n) {
        if (n < 1) {
            return 0;
        }
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
```

- 213 [House Robber II](https://leetcode.com/problems/house-robber-ii/)
```
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        
        int[] dp1 = new int[nums.length - 1];
        int[] dp2 = new int[nums.length - 1];
        
        dp1[0] = nums[0];
        dp1[1] = Math.max(nums[0], nums[1]);
        dp2[0] = nums[1];
        dp2[1] = Math.max(nums[1], nums[2]);
        
        for (int i = 2; i < nums.length - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i]);
        }
        // pay attention to the length of dp1 and dp2
        for (int i = 3; i < nums.length; i++) {
            dp2[i - 1] = Math.max(dp2[i - 2], dp2[i - 3] + nums[i]);
        }
        
        return Math.max(dp1[dp1.length - 1], dp2[dp2.length - 1]);
    }
}
```

- 322 [Coin Change](https://leetcode.com/problems/coin-change/)
```
Java

class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount + 1] == amount + 1 ? -1 : dp[amount + 1];
    }
}

Go

func coinChange(coins []int, amount int) int {
    dp := make([]int, amount + 1)
    for i, _ := range dp {
        dp[i] = amount + 1
    }
    dp[0] = 0
    for i := 1; i < amount + 1; i++ {
        for _, v := range coins {
            if i >= v {
                dp[i] = min(dp[i], dp[i - v] + 1)
            }
        }
    }
    if dp[amount] == amount + 1 {
        return -1
    }
    return dp[amount]
}

func min(a, b int) int {
    if a > b {
        return b
    }
    return a
}
```

- 354 [Russian Doll Envelopes俄罗斯大套娃子](https://leetcode.com/problems/russian-doll-envelopes/)
```
Java

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) {
            return 0;
        }
        
        Arrays.sort(envelopes, new Comparator<int[]>() {
           @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });
        
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        int len = 0;
        
        for (int[] array : envelopes) {
            int i = Arrays.binarySearch(dp, 0, len, array[1]);
            
            if (i < 0) {
                i = - (i + 1);
            }
            
            dp[i] = array[1];
            
            if (i == len) {
                len++;
            }
        }
        return len;
    }
}
```

- 646 [Maximum Length of Pair Chain](https://leetcode.com/problems/maximum-length-of-pair-chain/)
```
Java

class Solution {
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0 || pairs[0].length == 0) {
            return 0;
        }
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        int ans = 1;
        for (int i = 1; i < pairs.length; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```

- 746 [Min Cost Climbing Stairs](https://leetcode.com/problems/min-cost-climbing-stairs/)
```
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        
        return dp[cost.length];
    }
}
```

- 1025 [Divisor Game](https://leetcode.com/problems/divisor-game/)
The dp[i] means if the first person can win at the number i;
clearly, dp[0] == false, dp[1] == false;
From i = 2, check every number from 1 to i, if the i % j == 0, and 
dp[i - j] == false, it means the number that the first person picked can
be divided and second person will lose at the situation pick number from
i - j.
```
class Solution {
    public boolean divisorGame(int N) {
        boolean[] dp = new boolean[N + 1];
        dp[0] = false;
        dp[1] = false;
        
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (i % j == 0 && !dp[i - j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[N];
    }
}
```

