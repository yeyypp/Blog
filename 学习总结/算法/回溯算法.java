public class Main {
    /**
     * https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
     * 为什么ans.add(new ArrayList<>(list));
     * 因为如果不这样做,在更改list时,ans里的list也会被改变
     */

    /**
     * 46 全排列
     * 因为全排列，每一次都需要从头遍历，所以不用设置position
     */

    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> ans = new LinkedList<>();
            if (nums == null || nums.length == 0) {
                return ans;
            }
            dfs(ans, new LinkedList<Integer>(), nums);
            return ans;
        }

        private void dfs(List<List<Integer>> ans, List<Integer> list, int[] nums) {
            if (list.size() == nums.length) {
                ans.add(new ArrayList<>(list));
                return;
            } else {
                for (int i = 0; i < nums.length; i++) {
                    if (list.contains(nums[i])) {
                        continue;
                    }
                    list.add(nums[i]);
                    dfs(ans, list, nums);
                    list.remove(list.size() - 1);
                }
            }
        }
    }

    /**
     * 78 子集
     * 因为需要添加子集，所以先执行ans.add(list);
     * 不需要设置出口,但因为子集需要从已添加的之后判断,所以需要设置position
     */

    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> ans = new LinkedList<>();
            if (nums == null || nums.length == 0) {
                return ans;
            }
            dfs(ans, new LinkedList<Integer>(), nums, 0);
            return ans;
        }

        private void dfs(List<List<Integer>> ans, List<Integer> list, int[] nums, int position) {
            ans.add(new ArrayList<>(list));
            for (int i = position; i < nums.length; i++) {
                list.add(nums[i]);
                dfs(ans, list, nums, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 131 分割回文字符串
     * 因为每一次遍历是希望在已有的子串之后，所以需要设置position
     */

    class Solution {
        public List<List<String>> partition(String s) {
            List<List<String>> ans = new LinkedList<>();
            dfs(ans, new LinkedList<String>(), s, 0);
            return ans;
        }

        private void dfs(List<List<String>> ans, List<String> list, String s, int position) {
            if (position == s.length()) {
                ans.add(new ArrayList<>(list));
                return;
            } else {
                for (int i = position; i < s.length(); i++) {
                    if (isPalindrome(s, position, i)) {
                        //position是这个子串的起始位置
                        list.add(s.substring(position, i + 1));
                        //注意此时是从i + 1开始，s还是原有的s
                        dfs(ans, list, s, i + 1);
                        list.remove(list.size() - 1);
                    }
                }
            }
        }

        private boolean isPalindrome(String s, int start, int end) {
            while (start < end) {
                if (s.charAt(start) != s.charAt(end)) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        }
    }

    /**
     * 784 Letter Case Permutation
     */

    class Solution {
        public List<String> letterCasePermutation(String S) {
            List<String> ans = new LinkedList<>();
            if (S == null || S.length() == 0) {
                return ans;
            }
            dfs(ans, S.toCharArray(), 0);
            return ans;
        }

        private void dfs(List<String> ans, char[] s, int start) {
            if (start == s.length) {
                ans.add(new String(s));
                return;
            } else {
                if (Character.isLetter(s[start])) {
                    s[start] = Character.toLowerCase(s[start]);
                    dfs(ans, s, start + 1);
                    s[start] = Character.toUpperCase(s[start]);
                }
                dfs(ans, s, start + 1);
            }
        }
    }
}