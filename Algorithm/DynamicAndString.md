# Dynamic And String

- 72 [Edit Distance](https://leetcode.com/problems/edit-distance/)
```
Java

class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] matrix = new int[n + 1][m + 1];
        
        for (int i = 1; i <= n; i++) {
            matrix[i][0] = i;
        }
        for (int j = 1; j <= m; j++) {
            matrix[0][j] = j;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1];
                } else {
                    matrix[i][j] = Math.min(matrix[i - 1][j - 1], Math.min(matrix[i - 1][j], matrix[i][j - 1])) + 1;
                }
            }
        }
        
        return matrix[n][m];
    }
}
```

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

- 647 [Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/)
```
Java

class Solution {
    int count = 0;
    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            expand(i, i, s);
            expand(i, i + 1, s);
        }
        return count;
    }
    
    private void expand(int i, int j, String s) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            count++;
            i--;
            j++;
        }
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


- 1143 [Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/)  
[Tushar Explain](https://www.youtube.com/watch?v=NnD96abizww&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=2)  
  

[Other Problems](https://leetcode.com/problems/longest-common-subsequence/discuss/349346/LC1143-Classic-DP-Longest-Common-Subsequence-With-Follow-up-Problems)

```
Java

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
            return 0;
        }
        int[][] matrix = new int[text1.length() + 1][text2.length() + 1];
        
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }
        
        return matrix[text1.length()][text2.length()];
    }
    
}
```