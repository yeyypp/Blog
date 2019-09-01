# String
- 3 [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
```
Java

/* 注意i = map.get(c) + 1

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int ans = 1, i = 0, j = 0;
        while (j < s.length()) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(i, map.get(s.charAt(j)) + 1);
            }
            map.put(s.charAt(j), j);
            ans = Math.max(ans, j - i + 1);
            j++;
        }
        return ans;
    }
}
```

- 5 [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)
```
Java

class Solution {
    private int max = 0;
    private int start = 0;
    
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            expand(s, i, i);
            expand(s, i, i + 1);
        }
        return s.substring(start, start + max);
    }
    
    private void expand(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        
        if (max < j - i - 1) {
            max = j - i - 1;
            start = i + 1;
        }
    }
}
```

- 14 [Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/)
```
Java

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        String prefix = strs[0];
        
        for (String word : strs) {
            while (word.indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
    }
}
```

- 76 [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)
```
Java

class Solution {
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        
        int left = 0, right = 0, min = Integer.MAX_VALUE, start = 0, count = t.length();
        
        while (right < s.length()) {
            char c1 = s.charAt(right);
            if (map[c1] > 0) {
                count--;
            }
            map[c1]--;
            right++;
            
            while (count == 0) {
                if (min > right - left) {
                    min = right - left;
                    start = left;
                }
                char c2 = s.charAt(left);
                map[c2]++;
                if (map[c2] > 0) {
                    count++;
                }
                left++;
            }
        }
        
        return min == Integer.MAX_VALUE ? "" : s.substring(start, start + min);
    }
}
```

- 344 [Reverse String](https://leetcode.com/problems/reverse-string/)
```
Java

class Solution {
    public void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        int left = 0, right = s.length - 1;
        while (left < right) {
            swap(s, left++, right--);
        }
    }
    
    private void swap(char[] s, int a, int b) {
        char tem = s[a];
        s[a] = s[b];
        s[b] = tem;
    }
}
```