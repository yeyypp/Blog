# DFS
- 79 [Word Search](https://leetcode.com/problems/word-search/)
```
Java

/* 通过修改board的值，省去了使用marked数组的空间

class Solution {
    public boolean exist(char[][] board, String word) {
        char start = word.charAt(0);
        for (int i =0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == start) {
                  if (dfs(board, word, i, j, 0)) {
                      return true;
                  }
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, String word, int i, int j, int position) {
        if (position == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
                
        if (board[i][j] == '#') {
            return false;
        }
        if (board[i][j] == word.charAt(position)) {
            char c = board[i][j];
            
        // 此处通过修改board的值表示已经遍历过  
                  
            board[i][j] = '#';
            if (dfs(board, word, i + 1, j, position + 1) ||
               dfs(board, word, i - 1, j, position + 1) ||
               dfs(board, word, i, j + 1, position + 1) ||
               dfs(board, word, i, j - 1, position + 1)) {
                return true;
            }
            
        // 此处恢复原值
            board[i][j] = c;
        }
        return false;
    }
}
```