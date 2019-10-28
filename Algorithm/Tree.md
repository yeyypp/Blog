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
        TreeNode curNode = root;
        
        while (!stack.isEmpty() || curNode != null) {
            if (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            } else {
                curNode = stack.pop();
                ans.add(curNode.val);
                curNode = curNode.right;
            }
        }
        return ans;
    }
    
    
}

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        inorder(root, ans);
        return ans;
    }
    
    private void inorder(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        inorder(node.left, ans);
        ans.add(node.val);
        inorder(node.right, ans);
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

- 101 [Symmetric Tree](https://leetcode.com/problems/symmetric-tree/)
```
Java

class Solution {
    public boolean isSymmetric(TreeNode root) {
        return helper(root, root);
    }
    
    private boolean helper(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        } else if (a == null || b == null) {
            return false;
        } else if (a.val != b.val) {
            return false;
        }
        return helper(a.left, b.right) && helper(a.right, b.left);
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

- 104 [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)
```
Java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.right), maxDepth(root.left)) + 1;
    }
}
```

- 105 [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)
```
Java

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return findRoot(preorder, inorder, 0, 0, inorder.length - 1);
    }
    
    private TreeNode findRoot(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd) {
        if (preStart >= preorder.length || inStart > inEnd) {
            return null;
        }
        
        TreeNode root = new TreeNode(preorder[preStart]);
        
        int pos = 0;
        for (int j = 0; j < inorder.length; j++) {
            if (inorder[j] == preorder[preStart]) {
                pos = j;
            }
        }
        
        root.left = findRoot(preorder, inorder, preStart + 1, inStart, pos - 1);
        root.right = findRoot(preorder, inorder, preStart + pos - inStart + 1, pos + 1, inEnd);
        return root;
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

- 108 [Convert sorted array to BST](https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/)
```
Java
// Using binary search find the mid val, then recur

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return helper(nums, 0, nums.length - 1);
    }
    
    private TreeNode helper(int[] nums, int low, int high) {
        if (low <= high) {
            int mid = low + (high - low) / 2;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = helper(nums, low, mid - 1);
            node.right = helper(nums, mid + 1, high);
            return node;
        }
        return null;
    }
}
```

- 109 [convert Sorted List to BST](https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/)
```
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> array = new ArrayList<>();
        while (head != null) {
            array.add(head.val);
            head = head.next;
        }
        return helper(array, 0, array.size() - 1);
        
    }
    
    private TreeNode helper(List<Integer> array, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(array.get(mid));
        node.left = helper(array, low, mid - 1);
        node.right = helper(array, mid + 1, high);
        return node;
    }
}
```

- 112 [Path Sum](https://leetcode.com/problems/path-sum/)
```
Java

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
```

- 124 [Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/)
**maxPathDown**:代表过这个节点后是选择从左侧往下走还是右侧往下走，得到得最大值，是一条边
**max**:代表过这个节点，再加上左右两条边的最大值
```
Java
class Solution {
    
    private int max = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        maxPathDown(root);
        return max;
    }
    
    private int maxPathDown(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        max = Math.max(max, left + right + node.val);
        return Math.max(left, right) + node.val;
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
        
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curNode = root;
        stack.push(curNode);
        while (!stack.isEmpty()) {
            curNode = stack.pop();
            ans.add(curNode.val);
            if (curNode.right != null) {
                stack.push(curNode.right);
            }
            if (curNode.left != null) {
                stack.push(curNode.left);
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

- 145 [Binary Tree Postorder Traversal](https://leetcode.com/problems/binary-tree-postorder-traversal/)
```
Java

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        
        Deque<TreeNode> stack = new LinkedList<>();
        
        TreeNode curNode = root;
        stack.push(curNode);
        while (!stack.isEmpty()) {
            curNode = stack.pop();
            ans.add(0, curNode.val);
            if (curNode.left != null) {
                stack.push(curNode.left);
            }
            if (curNode.right != null) {
                stack.push(curNode.right);
            }
        }
        
        return ans;
    }
}
```

- 230 [Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/)
```
Java

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        
        TreeNode cur = root;
        
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (--k == 0) {
                    return cur.val;
                }
                cur = cur.right;
            }
        }
        return 0;
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

- 297 [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/)
```
Java

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString().trim();
    }
    
    private void preorder(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("n").append(" ");
        } else {
            sb.append(node.val).append(" ");
            preorder(node.left, sb);
            preorder(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>();
        String[] nums = data.split(" ");
        for (String s : nums) {
            queue.offer(s);
        }
        
        return des(queue);
    }
    
    private TreeNode des(Queue<String> queue) {
        String cur = queue.poll();
        if (cur.equals("n")) {
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.valueOf(cur));
            node.left = des(queue);
            node.right = des(queue);
            return node;
        }
    }
}
```

- 530 [Minimum Absolute Difference in BST](https://leetcode.com/problems/minimum-absolute-difference-in-bst/)
```
Java

class Solution {
    public int getMinimumDifference(TreeNode root) {
        TreeNode cur = root, pre = null;
        int min = Integer.MAX_VALUE;
        Deque<TreeNode> stack = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (pre != null) {
                    min = Math.min(min, Math.abs(cur.val - pre.val));
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return min;
    }
}
```
- 538 [Convert BST to Greater Tree](https://leetcode.com/problems/convert-bst-to-greater-tree/)
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode convertBST(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        int sum = 0;
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.right;
            } else {
                cur = stack.pop();
                sum += cur.val;
                cur.val = sum;
                cur = cur.left;
            }
        }
        return root;
    }
}
```

- 637 [Average of Levels in Binary Tree](https://leetcode.com/problems/average-of-levels-in-binary-tree/)
```
Java

class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double tem = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                tem += cur.val;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            ans.add(tem / size);
        }
        return ans;
    }
}
```

- 783 [Minimum Distance Between BST Nodes](https://leetcode.com/problems/minimum-distance-between-bst-nodes/)
```
Java

class Solution {
    public int minDiffInBST(TreeNode root) {
        int ans = Integer.MAX_VALUE;
        if (root == null) {
            return 0;
        }
        TreeNode cur = root, pre = null;
        Deque<TreeNode> stack = new LinkedList<>();
        
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (pre != null) {
                    ans = Math.min(ans, cur.val - pre.val);
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return ans;
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



