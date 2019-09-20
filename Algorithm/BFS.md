# BFS
- 101 [Symmetric Tree](https://leetcode.com/problems/symmetric-tree/)
```
Java

Recur
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return helper(root, root);
    }
    
    private boolean helper(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.val == b.val && helper(a.left, b.right) && helper(a.right, b.left);
    }
}

Non-recur
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }
}
```

- 102 [Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)
```
Java

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            ans.add(list);
        }
        
        return ans;
    }
}
```

- 103 [Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/)
```
Java

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        boolean toLeft = true;
        
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (toLeft) {
                    list.add(cur.val);
                } else {
                    list.add(0, cur.val);
                }
                
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            if (toLeft) {
                toLeft = false;
            } else {
                toLeft = true;
            }
            ans.add(list);
        }
        return ans;
    }
}
```

- 207 [Course Schedule](https://leetcode.com/problems/course-schedule/)
```
Java

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new LinkedList<>();
        }
        
        int[] incomingDegree = new int[numCourses];
        
        for (int[] pair : prerequisites) {
            incomingDegree[pair[0]]++;
            adj[pair[1]].add(pair[0]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < incomingDegree.length; i++) {
            if (incomingDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int count = 0;
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            count++;
            for (Integer neighbor : adj[cur]) {
                if (--incomingDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return count == numCourses;
    }
}
```

- 210 [Course Schedule II](https://leetcode.com/problems/course-schedule-ii/)
```
Java

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] incomingDegree = new int[numCourses];
        List<Integer>[] adj = new List[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new LinkedList<>();
        }
        
        for (int[] pair : prerequisites) {
            incomingDegree[pair[0]]++;
            adj[pair[1]].add(pair[0]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> tem = new ArrayList<>(numCourses);
        int count = 0;
        
        for (int i = 0; i < incomingDegree.length; i++) {
            if (incomingDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            tem.add(cur);
            count++;
            
            for (Integer neighbor : adj[cur]) {
                if (--incomingDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        if (count != numCourses) {
            return new int[0];
        }
        
        int[] ans = new int[numCourses];
        int index = 0;
        for (int i : tem) {
            ans[index++] = i;
        }
        
        return ans;
    }
}
```
- 994 [Rotting Oranges](https://leetcode.com/problems/rotting-oranges/)
```
Java

class Solution {
    public int orangesRotting(int[][] grid) {
        int minute = 0, fresh = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    fresh++;
                }
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        
        if (fresh == 0) {
            return 0;
        }
        
        int[] dr = new int[]{1, 0, -1, 0};
        int[] dc = new int[]{0, 1, 0, -1};
        
        while (!queue.isEmpty()) {
            minute++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int a = 0; a < 4; a++) {
                    int newI = cur[0] + dr[a];
                    int newJ = cur[1] + dc[a];
                    if (valid(newI, newJ, grid) && grid[newI][newJ] == 1) {
                        grid[newI][newJ] = 2;
                        fresh--;
                        queue.offer(new int[]{newI, newJ});
                    }
                }
            }
        }
        
        return fresh == 0 ? minute - 1 : -1;
    }
    
    private boolean valid(int i, int j, int[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return false;
        }
        return true;
    }
}
```