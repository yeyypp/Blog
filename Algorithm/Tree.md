# Tree
- 94 [Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/)
```
Java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        
        Deque<TreeNode> stack = new LinkedList<>();
        
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                ans.add(root.val);
                root = root.right;
            }
        }
        return ans;
    }
}
```

- 98 [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)
```
Java

class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }
    
    private boolean helper(TreeNode node, Integer low, Integer up) {
        if (node == null) {
            return true;
        }
        int val = node.val;
        if (low != null && val <= low) {
            return false;
        }
        if (up != null && val >= up) {
            return false;
        }
        return helper(node.left, low, val) && helper(node.right, val, up);
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
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
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
        queue.offer(root);
        boolean toLeft = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (toLeft) {
                    list.add(cur.val);
                } else {
                    list.add(0, cur.val);
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

- 107 [Binary Tree Level Order Traversal II](https://leetcode.com/problems/binary-tree-level-order-traversal-ii/)
```
Java

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(0, list);
        }
        return ans;
    }
}
```

- 144 [Binary Tree Preorder Traversal](https://leetcode.com/problems/binary-tree-preorder-traversal/)
```
Java

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.push(root);
        while (!deque.isEmpty()) {
            root = deque.pop();
            ans.add(root.val);
            if (root.right != null) {
                deque.push(root.right);
            }
            if (root.left != null) {
                deque.push(root.left);
            }
        }
        return ans;
    }
}

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        helper(root, ans);
        return ans;
    }
    
    private void helper(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        ans.add(node.val);
        helper(node.left, ans);
        helper(node.right, ans);
    }
}

```

- 235 [Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)
```
Java

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        return root;
    }
}

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (node != null) {
            if (node.val < p.val && node.val < q.val) {
                node = node.right;
            } else if (node.val > p.val && node.val > q.val) {
                node = node.left;
            } else {
                return node;
            }
        }
        return node;
    }
}
```

- 236 [Lowest Common Ancestor of Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)


    [Tushar explaination](https://www.youtube.com/watch?v=13m9ZCB8gjw)
```
Java

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }
}
```


- 863 [All Nodes Distance K in Binary Tree](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/)
```
Java

class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Map<TreeNode, TreeNode> map = new HashMap<>();
        map.put(root, null);
        addFather(map, root);
        
        Set<TreeNode> set = new HashSet<>();
        
        bfs(map, ans, set, 0, K, target);
        return ans;
    }
    
    private void addFather(Map<TreeNode, TreeNode> map, TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            map.put(node.left, node);
            addFather(map, node.left);
        }
        if (node.right != null) {
            map.put(node.right, node);
            addFather(map, node.right);
        }
    }
    
    private void bfs(Map<TreeNode, TreeNode> map, List<Integer> ans, Set<TreeNode> set, int i, int K, TreeNode node) {
        if (i == K) {
            ans.add(node.val);
            return;
        }
        set.add(node);
        TreeNode father = map.get(node);
        TreeNode left = node.left;
        TreeNode right = node.right;
        
        if (father != null && !set.contains(father)) {
            bfs(map, ans, set, i + 1, K, father);
        }
        
        if (left != null && !set.contains(left)) {
            bfs(map, ans, set, i + 1, K, left);
        }
        
        if (right != null && !set.contains(right)) {
            bfs(map, ans, set, i + 1, K, right);
        }
    }
}

```



