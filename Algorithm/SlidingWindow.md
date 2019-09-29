# Sliding Window
- 904 [Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/)  
Consider the question as to get the longest subarray with only two different elements.
```
Java

class Solution {
    public int totalFruit(int[] tree) {
        int[] type = new int[tree.length];
        int ans = 0, start = 0;
        Set<Integer> set = new HashSet<>();
        
        for (int i = 0; i < tree.length; i++) {
            while (set.size() == 2 && !set.contains(tree[i])) {
                type[tree[start]]--;
                if (type[tree[start]] == 0) {
                    set.remove(tree[start]);
                }
                start++;
            }
            set.add(tree[i]);
            type[tree[i]]++;
            ans = Math.max(ans, i - start + 1);
        }
        
        return ans;
    }
}
```
- 1208 [Get Equal Substrings Within Budget](https://leetcode.com/problems/get-equal-substrings-within-budget/)  
Consider the question as to get the longest subarray with all element in array is 
Math.abs(s.charAt(i) - t.charAt(i));

```
Java

class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int maxLength = 0;
        int start = 0;
        
        for (int i = 0; i < s.length(); i++) {
            maxCost -= Math.abs(s.charAt(i) - t.charAt(i));
            if (maxCost < 0) {
                maxCost += Math.abs(s.charAt(start) - t.charAt(start));
                start++;
            } else {
                maxLength = Math.max(maxLength, i - start + 1);
            }
        }
        
        return maxLength;
    }
}
```