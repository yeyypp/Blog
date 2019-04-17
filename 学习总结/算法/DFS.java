public class Main {
    /**
     * when to use :
     * backtracking, complete search, all possible
     */

    public static void dfs(Node start) {
        Stack<Node> stack;
        Set<Node> set;
        stack.push(start);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            if (!set.contains(cur)) {
                set.add(cur);
                System.out.print(cur.val);
            }

            for (Node node : cur.adj) {
                if (!set.contains(node)) {
                    stack.push(node);
                }
            }
        }
    }

    /**
     * 46 Permutation
     * 比如 1 2 3
     * 当 tem = 1 2 3 时， ans.add(tem)
     * 然后tem退回， tem = 1 2
     * 此时退到i = 1，在执行tem.remove，tem = 1
     * 在继续循环 i = 2 时 tem = 1 3
     */

    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> ans = new LinkedList<>();
            List<Integer> tem = new LinkedList<>();
            dfs(nums, ans, tem);
            return ans;
        }

        private void dfs(int[] nums, List<List<Integer>> ans, List<Integer> tem) {
            if (tem.size() == nums.length) {
                ans.add(new ArrayList<>(tem));
            } else {
                for (int i = 0; i < nums.length; i++) {
                    if (tem.contains(nums[i])) {
                        continue;
                    }
                    tem.add(nums[i]);
                    dfs(nums, ans, tem);
                    tem.remove(tem.size() - 1);
                }
            }
        }
    }



    /**
     * 386 字典序排序
     * 相关数据结构字典树还需了解
     * 相当于从1开始的字典树，进行pre-order遍历
     */

    class Solution {
        public List<Integer> lexicalOrder(int n) {
            List<Integer> ans = new LinkedList<>();
            //从10,11...2,20....所以i范围是1-9
            for (int i = 1; i < 10; i++) {
                dfs(i, n, ans);
            }
            return ans;
        }

        private void dfs(int cur, int n, List<Integer> ans) {
            //如果当前元素大于n，则直接返回
            if (cur > n) {
                return;
            }
            ans.add(cur);
            //继续以cur为父节点，向下遍历,10,11,12......19,所以是0-9
            for (int i = 0; i < 10; i++) {
                int tem = cur * 10 + i;
                dfs(tem, n, ans);
            }
        }
    }
}