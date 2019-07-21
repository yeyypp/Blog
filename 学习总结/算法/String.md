# String
- 3 [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
```
Java

/* 注意ans值为j - i + 1，map在put时，位置为j + 1 */

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(i, map.get(s.charAt(j)));
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
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