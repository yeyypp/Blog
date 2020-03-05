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

- 69 [Sqrt(x)](https://leetcode.com/problems/sqrtx/)
```
class Solution {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int start = 1, end = x;
        while (true) {
            int mid = start + (end - start) / 2;
            if (mid > x / mid) {
                end = mid - 1;
            } else {
                if (mid + 1 > x / (mid + 1)) {
                    return mid;
                }
                start = mid + 1;
            }
        }
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

- 172 [Factorizl Trailing Zeroes](https://leetcode.com/problems/factorial-trailing-zeroes/)
```
Java

class Solution {
    public int trailingZeroes(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}
```

- 326 [Power of Three](https://leetcode.com/problems/power-of-three/)
```
Java

class Solution {
    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }
        
        while (n % 3 == 0) {
            n /= 3;
        }
        
        return n == 1;
    }
}
```
- [Count primes](https://91mjw.com/vplay/Mjk5Ni0xLTA=.html)
```
Java

class Solution {
    public int countPrimes(int n) {
        if (n < 2) {
            return 0;
        }
        
        int[] nums = new int[n];
        
        nums[0] = -1;
        nums[1] = -1;
        
        for (int i = 2; i * i < nums.length; i++) {
            if (nums[i] != -1) {
                for (int j = i * i; j < nums.length; j += i) {
                    nums[j] = -1;
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += nums[i] == -1 ? 0 : 1;
        }
        
        return ans;
    }
}
```