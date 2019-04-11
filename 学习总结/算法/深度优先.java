public class Main {
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