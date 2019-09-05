# Backtracking

- 46 [Permutation](https://leetcode.com/problems/permutations/)
```
Java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        dfs(ans, new LinkedList<>(), nums);
        return ans;
        
    }
    
    private void dfs(List<List<Integer>> ans, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            dfs(ans, list, nums);
            list.remove(list.size() - 1); 
        }
    }
}
```

- 140 [Word Break II](https://leetcode.com/problems/word-break-ii/)  
注意，需要先用isBreakable判断是否可拆分，否则出现TLE
```
Java


class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ans = new LinkedList<>();
        if (isBreakable(s, wordDict)) {
            dfs(ans, new LinkedList<String>(), s, wordDict);
        }
        return ans;
    }
    
    private void dfs(List<String> ans, List<String> result, String s, List<String> wordDict) {
        if (s.length() == 0) {
            ans.add(convert(result));
            return;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                result.add(word);
                dfs(ans, result, s.substring(word.length()), wordDict);
                result.remove(result.size() - 1);
            }
        }
    }
    
    private boolean isBreakable(String s, List<String> wordDict) {
        boolean[] isBreak = new boolean[s.length() + 1];
        isBreak[0] = true;
        
        for (int i = 1; i < isBreak.length; i++) {
            for (int j = 0; j < i; j++) {
                if (isBreak[j] && wordDict.contains(s.substring(j, i))) {
                    isBreak[i] = true;
                    break;
                }
            }
        }
        return isBreak[s.length()];
    }
    
    private String convert(List<String> result) {
        StringBuilder sb = new StringBuilder();
        for (String word : result) {
            sb.append(word + " ");
        }
        return sb.toString().trim();
    }
}
```