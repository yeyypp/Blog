# Two Pointers
- 26 [Remove Duplicate from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)
```
Java
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        
        int cur = 0, next = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[cur]) {
                continue;
            }
            swap(nums, next, i);
            cur++;
            next++;
        }
        return cur + 1;
    }
    
    private void swap(int[] nums, int a, int b) {
        int tem = nums[a];
        nums[a] = nums[b];
        nums[b] = tem;
    }
}
```

- 125 [Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)
```
Java

class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        int i = 0, j = s.length() - 1;
        s = s.toLowerCase();
        
        while (i < j) {
            
            char left = s.charAt(i);
            char right = s.charAt(j);
            
            if (!Character.isLetterOrDigit(left)) {
                i++;
            } else if (!Character.isLetterOrDigit(right)) {
                j--;
            } else if (s.charAt(i) != s.charAt(j)) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        
        return true;
    }
}
```