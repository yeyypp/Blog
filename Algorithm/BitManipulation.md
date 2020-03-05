# Bit Manipulation
- 109 [Reverse Bit](https://leetcode.com/problems/reverse-bits/)
```
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int end = n & 1;
            n >>>= 1;
            result <<= 1;
            result |= end;
        }
        return result;
    }
}
```

- 191 [Number of Bits](https://leetcode.com/problems/number-of-1-bits/)
```
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n >>>= 1;
        }
        return count;
    }
}

public class Solution {
    
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            ans++;
            n &= n - 1;
        }
        return ans;
    }
}
```
- 371 [Sum of Two Integers](https://leetcode.com/problems/sum-of-two-integers/)
```
Java
// use & to get the carry, then << 1 position, use ^ to plus the integer.

class Solution {
    public int getSum(int a, int b) {
        int c = 0;
        while (b != 0) {
            c = a & b;
            a = a ^ b;
            b = c << 1;
        }
        return a;
    }
}
```

- [Hamming Distance](https://leetcode.com/explore/interview/card/top-interview-questions-easy/99/others/648/)
```
class Solution {
    public int hammingDistance(int x, int y) {
        int n = x ^ y;
        return hammingWeight(n);
    }
    
    private int hammingWeight(int n) {
        int ans = 0;
        
        while (n != 0) {
            ans++;
            n &= n - 1;
        }
        
        return ans;
    }
}
```

- [Reverse Bits](https://leetcode.com/explore/interview/card/top-interview-questions-easy/99/others/648)
```
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int ans = 0, end = 0;
        for (int i = 0; i < 32; i++) {
            end = n & 1;
            n >>= 1;
            ans <<= 1;
            ans |= end;
        }
        return ans;
    }
}
```




