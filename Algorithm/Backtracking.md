# Backtracking

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