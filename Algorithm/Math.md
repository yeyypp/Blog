# Math

- 13 [Roman to Integer](https://leetcode.com/problems/roman-to-integer/)
```
Java
class Solution {
    public int romanToInt(String s) {
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case 'I':
                    stack.push(1);
                    break;
                case 'V':
                    stack.push(5);
                    break;
                case 'X':
                    stack.push(10);
                    break;
                case 'L':
                    stack.push(50);
                    break;
                case 'C':
                    stack.push(100);
                    break;
                case 'D':
                    stack.push(500);
                    break;
                case 'M':
                    stack.push(1000);
                    break;
            }
        }
        
        int ans = stack.pop(), cur = ans;
        while (!stack.isEmpty()) {
            if (cur > stack.peek()) {
                cur = stack.pop();
                ans -= cur;
            } else {
                cur = stack.pop();
                ans += cur;
            }
        }
        return ans;
    }
}
```

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