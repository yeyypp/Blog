# Math

- 168 [Excel Sheet Column Title](https://leetcode.com/problems/excel-sheet-column-title/)
```
Java

class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.append((char) ('A' + n % 26));
            n = n / 26;
        }
        return sb.reverse().toString();
    }
}
```

- 171 [Excel Sheet Column Number](https://leetcode.com/problems/excel-sheet-column-number/)
```
Java

class Solution {
    public int titleToNumber(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans = ans * 26 + s.charAt(i) - 'A' + 1;
        }
        return ans;
    }
}
```