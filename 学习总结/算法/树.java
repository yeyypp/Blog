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
     * 94 二叉树中序
     */

    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> ans = new LinkedList<>();
            if (root == null) {
                return ans;
            }
            LinkedList<TreeNode> stack = new LinkedList<>();
            while (root != null | !stack.isEmpty()) {
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

    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> ans = new LinkedList<>();
            if (root == null) {
                return ans;
            }
            inorder(root, ans);
            return ans;
        }

        private void inorder(TreeNode root, List<Integer> ans) {
            if (root == null) {
                return;
            }
            inorder(root.left, ans);
            ans.add(root.val);
            inorder(root.right, ans);
        }
    }


    /**
     * 101 对称二叉树
     */
    class Solution {
        public boolean isSymmetric(TreeNode root) {
            return is(root, root);
        }

        private boolean is(TreeNode a, TreeNode b) {
            if (a == null && b == null) {
                return true;
            }
            if (a == null || b == null) {
                return false;
            }
            if (a.val == b.val) {
                return is(a.left, b.right) && is(a.right, b.left);
            }
            return false;
        }
    }

    class Solution {
        public boolean isSymmetric(TreeNode root) {

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            queue.offer(root);
            TreeNode a = null;
            TreeNode b = null;
            while (!queue.isEmpty()) {
                a = queue.poll();
                b = queue.poll();
                if (a == null && b == null) {
                    continue;
                }
                if (a == null || b == null) {
                    return false;
                }
                if (a.val != b.val) {
                    return false;
                }
                queue.offer(a.left);
                queue.offer(b.right);
                queue.offer(a.right);
                queue.offer(b.left);
            }
            return true;
        }
    }
    /**
     * 108 Convert a Sorted Array to a bst
     * 根据bst性质，左子树元素小于根节点，右子树元素大于根节点
     * 所以数组的中位数为根节点，依次递归
     * 注意mid边界
     * low，mid - 1   mid + 1， high
     * 在判断时通过if (left <= high) 判断
     *
     */

    class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            return convert(nums, 0, nums.length - 1);
        }

        private TreeNode convert(int[] nums, int left, int right) {
            if (left <= right) {
                int mid = (left + right) / 2;
                TreeNode node = new TreeNode(nums[mid]);
                node.left = convert(nums, left, mid - 1);
                node.right = convert(nums, mid + 1, right);
                return node;
            }
            return null;
        }
    }

    /**
     * 109 有序链表转换成bst
     * 把链表变成list
     * 再通过上边的方法转换
     * 时间空间均为N
     */

    class Solution {
        public TreeNode sortedListToBST(ListNode head) {
            if (head == null) {
                return null;
            }
            List<Integer> list = new LinkedList<>();
            while (head != null) {
                list.add(head.val);
                head = head.next;
            }
            return convert(list, 0, list.size() - 1);
        }

        private TreeNode convert(List<Integer> list, int left, int right) {
            if (left <= right) {
                int mid = (left + right) / 2;
                TreeNode node = new TreeNode(list.get(mid));
                node.left = convert(list, left, mid - 1);
                node.right = convert(list, mid + 1, right);
                return node;
            }
            return null;
        }
    }

    /**
     * 110 Balanced binary tree
     * 先计算出左右树的高度
     * 注意平衡树的子树也是平衡树
     */

    class Solution {
        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }

        private int height(TreeNode node) {
            if (node == null) {
                return 0;
            } else {
                return 1 + Math.max(height(node.left), height(node.right));
            }
        }
    }


    /**
     * 144 二叉树前序遍历
     */

    //非递归
    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> ans = new LinkedList<>();
            if (root == null) {
                return ans;
            }
            LinkedList<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            TreeNode tem = null;
            while (!stack.isEmpty()) {
                tem = stack.pop();
                ans.add(tem.val);
                if (tem.right != null) {
                    stack.push(tem.right);
                }
                if (tem.left != null) {
                    stack.push(tem.left);
                }
            }
            return ans;
        }
    }
    //递归
    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> ans = new LinkedList<>();
            if (root == null) {
                return ans;
            }
            preorder(root, ans);
            return ans;
        }

        private void preorder(TreeNode root, List<Integer> ans) {
            if (root == null) {
                return;
            }
            ans.add(root.val);
            preorder(root.left, ans);
            preorder(root.right, ans);
        }
    }

    /

    /**
     * 145 二叉树后序遍历
     * 非递归时设一个last节点，存上一个遍历的节点
     * 代码与中序类似
     */

    class Solution {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> ans = new LinkedList<>();
            if (root == null) {
                return ans;
            }
            LinkedList<TreeNode> stack = new LinkedList<>();
            TreeNode tem = null;
            TreeNode last = null;
            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    tem = stack.peek();
                    //判断当前节点右节点是否为空或者已经遍历过，没有则root = tem.right
                    if (tem.right != null && tem.right != last) {
                        root = tem.right;
                    } else {
                        //当没有右节点或已经遍历过，则pop出，然后设last为tem，并root = null
                        stack.pop();
                        ans.add(tem.val);
                        last = tem;
                        root = null;
                    }
                }
            }
            return ans;
        }
    }

    class Solution {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> ans = new LinkedList<>();
            postorder(root, ans);
            return ans;
        }

        private void postorder(TreeNode root, List<Integer> ans) {
            if (root == null) {
                return;
            }
            postorder(root.left, ans);
            postorder(root.right, ans);
            ans.add(root.val);
        }
    }

    /**
     * 226 Invert Binary Tree
     * 递归思想时，注意不能 TreeNode left = root.left
     * 必须直接使用root.left
     * 因为改变left时不会改变原有root.left
     * (值传递概念）
     */

    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode tem = root.left;
            root.left = root.right;
            root.right = tem;
            invertTree(root.left);
            invertTree(root.right);
            return root;
        }
    }

    /**
     * 非递归
     */

    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode tem = queue.poll();
                TreeNode tem2 = tem.left;
                tem.left = tem.right;
                tem.right = tem2;
                if (tem.left != null) {
                    queue.offer(tem.left);
                }
                if (tem.right != null) {
                    queue.offer(tem.right);
                }
            }
            return root;
        }
    }

    /**
     * 235 Lowest common ancestor of binary search tree
     * 利用bst性质，p，q值都小于root则在左，都大于则在右，否则返回root
     */

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode node = root;
            while (node != null) {
                if (q.val < node.val && p.val < node.val) {
                    node = node.left;
                } else if (q.val > node.val && p.val > node.val) {
                    node = node.right;
                } else {
                    return node;
                }
            }
            return null;
        }
    }

    /**
     * 236 二叉树最近公共祖先
     * 首先判断
     * if (root == null || root == p || root == q) {
     *     return root;
     * }
     * 出现上述情况则返回root
     * 然后建立TreeNode left，right
     * 当left，right都不为null时，则说明root是最近的祖先，返回root
     * 若没有则返回不为null的
     * 当左右均不为null时，说明root为祖先
     * 否则返回不为null的节点
     */

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) {
                return root;
            }
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left != null && right != null) {
                return root;
            } else if (left != null) {
                return left;
            } else if (right != null) {
                return right;
            }
        }
    }

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

    /**
     * 257 二叉树所有路径
     * 注意不能直接传递String
     * 注意要判断if (node.left == null && node.right == null)
     * 不能判断if (node == null)
     * 因为要保证每次给s增加的是 s + node.val + "->"
     */

    class Solution {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> ans = new LinkedList<>();
            if (root == null) {
                return ans;
            }
            dfs(ans, root, "");
            return ans;
        }

        private void dfs(List<String> ans, TreeNode node, String s) {
            if (node.left == null && node.right == null) {
                ans.add(s + node.val);
            }
            if (node.left != null) {
                dfs(ans, node.left, s + node.val + "->");
            }
            if (node.right != null) {
                dfs(ans, node.right, s + node.val + "->");
            }
        }
    }

    /**
     * 429 N 叉树，层次遍历
     */

    class Solution {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> ans = new LinkedList<>();
            if (root == null) {
                return ans;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> list = new LinkedList<>();
                for (int i = 0; i < size; i++) {
                    Node node = queue.poll();
                    list.add(node.val);
                    for (Node child : node.children) {
                        queue.offer(child);
                    }
                }
                ans.add(list);
            }
            return ans;
        }
    }

    /**
     * 563 binary tree tilt
     * 左数的和等于left.val + left.left + left.right
     * 所以 traverse(node) = left + right + node.val
     * 而坡度等于 Math.abs(left - right);
     */

    class Solution {
        int tilt = 0;
        public int findTilt(TreeNode root) {
            traverse(root);
            return tilt;
        }

        private int traverse(TreeNode node) {
            if (node == null) {
                return 0;
            }
            int left = traverse(node.left);
            int right = traverse(node.right);
            tilt += Math.abs(left - right);
            return left + right + node.val;
        }
    }

    /**
     * 617 Merge two Binary Tree
     */

    class Solution {
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if (t1 == null) {
                return t2;
            }
            if (t2 == null) {
                return t1;
            }
            TreeNode node = new TreeNode(t1.val + t2.val);
            node.left = mergeTrees(t1.left, t2.left);
            node.right = mergeTrees(t1.right,t2.right);
            return node;
        }
    }

    /**
     * 653 bst 两数之和
     * 可以把bst转化为有序数组
     * 也可以利用set
     */

    class Solution {
        public boolean findTarget(TreeNode root, int k) {
            Set<Integer> set = new HashSet<>();
            return find(set, root, k);
        }

        private boolean find(Set<Integer> set, TreeNode node, int k) {
            if (node == null) {
                return false;
            }
            if (set.contains(k - node.val)) {
                return true;
            }
            set.add(node.val);
            return find(set, node.left, k) || find(set, node.right, k);
        }
    }

    /**
     * 669 Trim a BST
     * 当root小于最小值则root等于从root.right开始，反之大于R时从root.left开始
     * 如果在中间则返回这个节点，再依次递归左边与右边。
     */

    class Solution {
        public TreeNode trimBST(TreeNode root, int L, int R) {
            if (root == null) {
                return null;
            }
            if (root.val < L) {
                return trimBST(root.right, L, R);
            }
            if (root.val > R) {
                return trimBST(root.left, L, R);
            }
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
            return root;
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

    /**
     * 938 BST搜索范围
     * 递归
     */

    class Solution {
        public int rangeSumBST(TreeNode root, int L, int R) {
            if (root == null) {
                return 0;
            }
            if (root.val >= L && root.val <= R) {
                return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
            }
            if (root.val > R) {
                return rangeSumBST(root.left, L, R);
            }
            if (root.val < L) {
                return rangeSumBST(root.right, L, R);
            }
            return 0;
        }
    }

    /**
     * 965 univalued binary tree
     * 层次遍历即可
     */

    class Solution {
        public boolean isUnivalTree(TreeNode root) {
            if (root == null) {
                return false;
            }
            int ans = root.val;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.val != ans) {
                    return false;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            return true;
        }
    }


}