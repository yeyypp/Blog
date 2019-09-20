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

- 200 [Number of Islands](https://leetcode.com/problems/number-of-islands/)
```
Java

class Solution {
    int[] dr = new int[]{1, 0, -1, 0};
    int[] dc = new int[]{0, 1, 0, -1};
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int ans = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(grid, i, j, visited);
                    ans++;
                }
            }
        }
        return ans;
    }
    
    private void dfs(char[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0' || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        for (int a = 0; a < 4; a++) {
            dfs(grid, i + dr[a], j + dc[a], visited);
        }
    }
}
```

- 417 [Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/)
反过来想，海水能流过的坐标，这样就不用多使用一个boolean[][] visited
```
Java

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }
        
        int m = matrix.length, n = matrix[0].length;
        boolean[][] toPacific = new boolean[m][n];
        boolean[][] toAtlantic = new boolean[m][n];
        int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};        
       
        for (int i = 0; i < m; i++) {
            dfs(matrix, toPacific, dir, i, 0);
            dfs(matrix, toAtlantic, dir, i, n - 1);
        }
        
        for (int j = 0; j < n; j++) {
            dfs(matrix, toPacific, dir, 0, j);
            dfs(matrix, toAtlantic, dir, m - 1, j);
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (toPacific[i][j] && toAtlantic[i][j]) {
                    List<Integer> tem = new ArrayList<>(2);
                    tem.add(i);
                    tem.add(j);
                    ans.add(tem);
                }
            }
        }
        
        return ans;
    }
    
    private boolean validate(int i, int j, int[][] matrix) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
            return false;
        }
        return true;
    }
    
    private void dfs(int[][] matrix, boolean[][] ocean, int[][] dir, int i, int j) {
        ocean[i][j] = true;
        for (int[] coor : dir) {
            int a = i + coor[0];
            int b = j + coor[1];
            if (!validate(a, b, matrix)) {
                continue;
            } else if (ocean[a][b] == false && matrix[a][b] >= matrix[i][j]) {
                dfs(matrix, ocean, dir, a, b);
            }
        }
    }
}
```