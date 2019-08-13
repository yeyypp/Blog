public class Main {
    /**
     * https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
     * 为什么ans.add(new ArrayList<>(list));
     * 因为如果不这样做,在更改list时,ans里的list也会被改变
     *
     * 当问题是排列组合时，意味着每一次都要从头遍历，查看，所以需要循环。如果不是，则设一个int start 每回从start + 1开始
     */

    /**
     * 46 全排列
     * 因为全排列，每一次都需要从头遍历，所以不用设置position
     * 每一次遍历，例如i = 3时，当进行完list.remove(list.size() - 1)时，list中是有前0，1，2元素的
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
     * 因为不是全排列，所以设一个int start ，注意每次从i开始，而不是从0
     * 如果从start开始，会出现i = 1, start = 0, 则出现2 1 3 的情况
     * 因为需要添加子集，所以先执行ans.add(list);
     *
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

        private void dfs(List<List<Integer>> ans, List<Integer> list, int[] nums, int start) {
            ans.add(new ArrayList<>(list));
            for (int i = position; i < nums.length; i++) {
                list.add(nums[i]);
                dfs(ans, list, nums, i + 1);
                list.remove(list.size() - 1);
            }
        }

        /**
         * 有出口的版本
         * private void dfs(List<List<Integer>> ans, List<Integer> list, int[] nums, int start) {
         *        if (start == nums.length) {
         *            ans.add(new ArrayList<>(list));
         *            return;
         *        }
         *        ans.add(new ArrayList<>(list));
         *        for (int i = start; i < nums.length; i++) {
         *            list.add(nums[i]);
         *            dfs(ans, list, nums, i + 1);
         *            list.remove(list.size() - 1);
         *        }
         *    }
         */
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
     * 先进行判断是否为字母
     * 当是数字时，直接dfs下一个
     * 当是字母时，先小写，dfs下一个，再变大写，接着dfs下一个
     * 不论怎样都需要dfs下一个
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