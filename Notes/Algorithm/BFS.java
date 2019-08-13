public class Main {
    /**
     * check if there is a path between two nodes.
     * bfs ues queue
     */

    public static void bfs(Node start) {
        Queue<Node> queue;
        Set<Node> set;

        queue.offer(start);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (!set.contains(cur)) {
                set.add(cur);
                System.out.print(cur.val);
            }
            for (Node node : cur.adj) {
                if (!set.contains(node)) {
                    queue.offer(node);
                }
            }
        }
    }

    /**
     * 104. Maximum Depth of Binary Tree
     */

    class Solution {
        public int maxDepth(Node root) {
            if (root == null) {
                return 0;
            }
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }


    /**
     * 559 Maximum Depth of N-ary Tree
     */

    class Solution {
        public int maxDepth(Node root) {
            if (root == null) {
                return 0;
            }
            int ans = 0;
            if (root.children == null) {
                return 1;
            } else {
                for (Node node : root.children) {
                    ans = Math.max(ans, maxDepth(node));
                }
            }
            return 1 + ans;
        }
    }



    /**
     * 863 All Nodes Distance K in Binary Tree
     * We are given a binary tree (with root node root), a target node, and an integer value K.
     * Return a list of the values of all nodes that have a distance K from the target node.
     * The answer can be returned in any order.
     *
     * 用一个hashmap来记录所有节点的父节点，用一个set记录走过的节点
     * 注意判断是否走过节点
     */

    class Solution {
        public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
            Map<TreeNode, TreeNode> map = new HashMap<>();
            addFather(root, map);
            List<Integer> ans = new LinkedList<>();
            Set<TreeNode> set = new HashSet<>();
            bfs(target, set, 0, K, ans, map);
            return ans;
        }

        public void addFather(TreeNode node, Map<TreeNode, TreeNode> map) {
            if (node == null) {
                return;
            }
            if (node.left != null) {
                map.put(node.left, node);
                addFather(node.left, map);
            }
            if (node.right != null) {
                map.put(node.right, node);
                addFather(node.right, map);
            }
        }

        public void bfs(TreeNode node, Set<TreeNode> set, int i, int K, List<Integer> ans, Map<TreeNode, TreeNode> map) {
            if (i == K) {
                ans.add(node.val);
                return;
            }
            set.add(node);
            TreeNode left = node.left;
            TreeNode right = node.right;
            TreeNode father = map.get(node);
            if (left != null && !set.contains(left)) {
                set.add(left);
                bfs(left, set, i + 1, K, ans, map);
            }
            if (right != null && !set.contains(right)) {
                set.add(right);
                bfs(right, set, i + 1, K, ans, map);
            }
            if (father != null && !set.contains(father)) {
                set.add(father);
                bfs(father, set, i + 1, K, ans, map);
            }
        }
    }
}