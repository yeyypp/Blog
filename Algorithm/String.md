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

- 409 [Longest Palindrome](https://leetcode.com/problems/longest-palindrome/)
```
Java

class Solution {
    public int longestPalindrome(String s) {
        Set<Character> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                set.remove(s.charAt(i));
                count++;
            } else {
                set.add(s.charAt(i));
            }
        }
        if (set.size() != 0) {
            return count * 2 + 1;
        }
        return count * 2;
    }
}
```
- 412 [Fizz Buzz](https://leetcode.com/problems/fizz-buzz/)
```
Java
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> ans = new LinkedList<>();
        if (n == 0) {
            return ans;
        }
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                ans.add("FizzBuzz");
            } else if (i % 3 == 0) {
                ans.add("Fizz");
            } else if (i % 5 == 0) {
                ans.add("Buzz");
            } else {
                ans.add(Integer.toString(i));
            }
        }
        return ans;
    }
}
```

- 482 [License Key Formatting](https://leetcode.com/problems/license-key-formatting/)
```
Java

class Solution {
    public String licenseKeyFormatting(String S, int K) {
        String s = S.replaceAll("-", "").toUpperCase();
        StringBuilder sb = new StringBuilder(s).reverse();
        // i should be equals i += K + 1 because of the insert methods.
        for (int i = K; i < sb.length(); i += K + 1) {
            sb.insert(i, "-");
        }
        return sb.reverse().toString();
    }
}
```

- 819 [Most Common Word](https://leetcode.com/problems/most-common-word/)
```
Java
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>(Arrays.asList(banned));
        String[] words = paragraph.replaceAll("\\W+", " ").toLowerCase().split(" ");
        for (String word : words) {
            if (!set.contains(word)) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }
        PriorityQueue<String> maxHeap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        map.keySet().forEach(key -> maxHeap.offer(key));
        return maxHeap.poll();
    }
}
```