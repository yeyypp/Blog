# HashTable
- 36 [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/)
```
class Solution {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return false;
        }
        
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    if (!set.add(c + "row:" + i) ||
                       !set.add(c + "col:" + j) ||
                       !set.add(c + "block:" + i/3 + "-" + j/3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
```

- 49 [Group Anagrams](https://leetcode.com/problems/group-anagrams/)
```
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new LinkedList<>();
        Map<String, List<String>> map = new HashMap<>();
        
        for (String s : strs) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String cur = new String(c);
            if (map.containsKey(cur)) {
                map.get(cur).add(s);
            } else {
                map.put(cur, new LinkedList<String>());
                map.get(cur).add(s);
            }
        }
        
        for (Map.Entry<String, List<String>> e : map.entrySet()) {
            ans.add(e.getValue());
        }
        
        return ans;
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

- 202 [Happpy Number](https://leetcode.com/problems/happy-number/)
```
Java

class Solution {
    public boolean isHappy(int n) {
        return helper(n, new HashSet<>());
    }
    
    private boolean helper(int n, Set<Integer> set) {
        if (n == 1) {
            return true;
        }
        if (set.contains(n)) {
            return false;
        }
        set.add(n);
        int sum = 0;
        while (n != 0) {
            int a = n % 10;
            sum += a * a;
            n = n / 10;
        }
        return helper(sum, set);
    }
}
```


- 204 [Count Primes](https://leetcode.com/problems/count-primes/)
```
Java

class Solution {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        boolean[] nums = new boolean[n];
        for (int i = 2; i * i < n; i++) {
            if (!nums[i]) {
                for (int j = i; i * j < n; j++) {
                    nums[i * j] = true;
                }
            }
        }
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (!nums[i]) {
                ans++;
            }
        }
        return ans;
    }
}

Go

func countPrimes(n int) int {
    if n <= 2 {
        return 0
    }
    nums := make([]bool, n)
    for i := 2; i * i < n; i++ {
        if !nums[i] {
            for j := i; j * i < n; j++ {
                nums[i * j] = true;
            }
        }
    }
    ans := 0
    for i := 2; i < n; i++ {
        if !nums[i] {
            ans++
        }
    }
    return ans;
}
```

- 217 [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/)
```
Java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
            } else {
                return true;
            }
        }
        return false;
    }
}

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }
}
```


- 242 [Valid Anagram](https://leetcode.com/problems/valid-anagram/)
```
Java

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int counter : count) {
            if (counter != 0) {
                return false;
            }
        }
        return true;
    }
}
```

- 350 [Intersection of Two Arrays II](https://leetcode.com/problems/intersection-of-two-arrays-ii/)
```
Java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        List<Integer> list = new LinkedList<>();
        for (int i : nums2) {
            if (map.get(i) != null && map.get(i) > 0) {
                list.add(i);
                map.put(i, map.get(i) - 1);
            }
        }
        return convert(list);
    }
    
    private int[] convert(List<Integer> list) {
        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
```

- 387 [First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/)
```
Java

class Solution {
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
```

- 454 [4Sum II](https://leetcode.com/problems/4sum-ii/)
```
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int sum = A[i] + B[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sum = C[i] + D[j];
                count += map.getOrDefault(0 - sum, 0);
            }
        }
        
        return count;
    }
}
```


- 957 [Prison Cells After N Days](https://leetcode.com/problems/prison-cells-after-n-days/)
```
Java

class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<String, Integer> map = new HashMap<>();
        while (N > 0) {
            int[] cells2 = new int[8];
            map.put(Arrays.toString(cells), N--);
            for (int i = 1; i < 7; i++) {
                cells2[i] = cells[i  -1] == cells[i + 1] ? 1 : 0;
            }
            cells = cells2;
            if (map.containsKey(Arrays.toString(cells))) {
                N %= map.get(Arrays.toString(cells)) - N;
            }
        }
        return cells;
    }
}
```