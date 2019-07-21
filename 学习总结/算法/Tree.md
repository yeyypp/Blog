# Tree
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