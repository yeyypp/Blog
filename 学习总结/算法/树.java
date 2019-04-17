public class Main {
    /**
     * n 总结点数
     * n0 度为0的节点数（叶子）
     * n1 度为1的节点（完全二叉树中只能有一个或两个）
     * n2 度为2的节点
     *
     * n = n0 + n1 + n2;
     * n0 = n2 + 1;
     *
     *
     深度为k的二叉树最多有2的k次方减1个节点，(2^k) - 1
     第i层最多有2^(i - 1)个节点
     叶子节点个数为n0，度为2的节点个数为n2， n0 = n2 + 1
     n个节点的完全二叉树，深度<= log2^n + 1

     完全二叉树 只有最下面两层的节点的度数小于2，其余各层的度数都等于2，并且最下面一层的节点都集中在最左边的若干位置上
     平衡二叉树 平衡二叉搜索树（Self-balancing binary search tree）又被称为AVL树（有别于AVL算法），
     且具有以下性质：它是一 棵空树或它的左右两个子树的高度差的绝对值不超过1

     二叉搜索树 左子树所有节点小于根节点，右子树大于，且左右子树均为二叉搜索树

     */

    /**
     * 108 Convert a Sorted Array to a bst
     * 根据bst性质，左子树元素小于根节点，右子树元素大于根节点
     * 所以数组的中位数为根节点，依次递归
     * 注意mid边界
     * low，mid - 1   mid + 1， high
     */

    class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            return helper(nums, 0, nums.length - 1);
        }

        private TreeNode helper(int[] nums, int low, int high) {
            if (low > high) {
                return null;
            }
            int mid = low + (high - low) / 2;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = helper(nums, low, mid - 1);
            node.right = helper(nums, mid + 1, high);
            return node;
        }
    }

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