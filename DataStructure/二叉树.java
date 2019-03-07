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
public class Main {
    /**
     * pre-order
     * 递归
     * 迭代
     */

    private void preOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

    private void preOrder2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tem = stack.pop();
            if (tem != null) {
                System.out.print(tem.val);
                stack.push(tem.right);
                stack.push(tem.left);
            }
        }
    }







    /**
     * inOrder
     *
     */

    private void inOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        helper(root.left, res);
        res.add(root.val);
        helper(root.right, res);
    }

    private void inOrder2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tem = root;
        while (tem != null || !stack.isEmpty()) {
            if (tem != null) {
                stack.push(tem);
                tem = tem.left;
            } else {
                tem = stack.pop();
                System.out.print(tem.val);
                tem = tem.right;
            }
        }
    }









    /**
     * postOrder
     */

    private void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val);
    }

    private void postOrder2(TreeNode root) {
        TreeNode lastVisited = null;
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.peek();
                if (node.right != null && node.rigth != lastVisited) {
                    root = node.right;
                } else {
                    stack.pop();
                    lastVisited = node;
                    root = null;
                    System.out.print(node.val)
                }
            }
        }
    }

    /**
     * max depth
     */

    private int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(helper(node.left), helper(node.right));
    }
}
