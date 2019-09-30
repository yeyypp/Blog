# Bit Manipulation
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