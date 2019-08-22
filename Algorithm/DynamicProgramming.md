# Dynamic Programming
- 300 [Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)
```
Java

class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int ans = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }
}
    
Go

func lengthOfLIS(nums []int) int {
    var ans int = 1
    if len(nums) == 0 {
        return 0
    }
    dp := make([]int, len(nums))
    for i, _ := range dp {
        dp[i] = 1
    }
    for i := 1; i < len(nums); i++ {
        for j := 0; j < i; j++ {
            if nums[j] < nums[i] {
                dp[i] = max(dp[i], dp[j] + 1)
            }
        }
        ans = max(ans, dp[i])
    }
    return ans
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
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

- 674 [Longest Continuous Increasing Subsequence](https://leetcode.com/problems/longest-continuous-increasing-subsequence/)
```
Java

class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int ans = 0, anchor = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] >= nums[i]) {
                anchor = i;
            }
            ans = Math.max(ans, i - anchor + 1);
        }
        return ans;
    }
}
```