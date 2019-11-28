# Two Pointers
- 11 [Container With Most Water](https://leetcode.com/problems/container-with-most-water/)
the area of the container is decided by the shortest line, so we moved the shortest line to
get a new area that may be bigger than the current one.
```
class Solution {
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int ans = 0;
        int start = 0, end = height.length - 1;
        while (start < end) {
            int left = height[start];
            int right = height[end];
            ans = Math.max(Math.min(left, right) * (end - start), ans);
            if (left < right) {
                start++;
            } else {
                end--;
            }
        }
        return ans;
    }
}
```
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

- 287 [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/)
```
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[nums[0]];
        
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        
        fast = 0;
        
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
}
```