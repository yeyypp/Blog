# HashTable
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