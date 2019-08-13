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