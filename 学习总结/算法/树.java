public class Main {
    /**
     * 700.二叉搜索树中的搜索
     */

    class Solution {
        //非递归
        public TreeNode searchBST(TreeNode root, int val) {

            while (root != null) {
                if (root.val == val) {
                    return root;
                }
                if (val > root.val) {
                    root = root.right;
                } else {
                    root = root.left;
                }
            }
            return null;
        }

        //递归
        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null) {
                return null;
            }
            if (root.val == val) {
                return root;
            } else if (val > root.val) {
                return searchBST(root.right, val);
            } else {
                return searchBST(root.left, val);
            }
        }
    }
}