public class Main {
    /**
     * 3 最长无重复子串
     * set 滑动窗口
     */

    class Solution {
        public int lengthOfLongestSubstring(String s) {
            Set<Character> set = new HashSet<>();
            int ans = 0, i = 0, j = 0;
            while (i < s.length() && j < s.length()) {
                if (!set.contains(s.charAt(j))) {
                    set.add(s.charAt(j));
                    j++;
                    ans = Math.max(ans, set.size());
                } else {
                    //移除窗口的第一个数，直到不包含重复
                    set.remove(s.charAt(i));
                    i++;
                }
            }
            return ans;
        }
    }


    /**
     * 14 最长共同前缀
     */
    class Solution {
        public longCommonPrefix(String[] strs) {
            /**
             * 利用String.indexOf
             */
            if (strs == null || strs.length == 0) {
                return "";
            }
            String prefix = strs[0];
            for (String s : strs) {
                while (s.indexOf(prefix) != 0) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                }
            }
            return prefix;
        }
    }

    /**
     * 43 字符串乘
     * i * j位的数会影响i + j位与i + j + 1位的数
     * 在转换成字符串时，第一位是0的话则不要
     */

    class Solution {
        public String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) {
                return "0";
            }
            int[] result = new int[num1.length() + num2.length()];
            for (int i = num1.length() - 1; i >= 0; i--) {
                for (int j = num2.length() - 1; j >= 0; j--) {
                    int sum = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + result[i + j + 1];
                    result[i + j] += sum / 10;
                    result[i + j + 1] = sum % 10;
                }
            }


            StringBuilder sb = new StringBuilder();
            for (Integer p : result) {
                if (!(sb.length() == 0 && p == 0)) {
                    sb.append(p);
                }
            }
            return sb.toString();
        }
    }

    /**
     * 76 最短覆盖串
     * 一般求字串问题都可以通过一个滑动窗口来解决
     * https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
     * 此题用两个map，一个表示t的所有字母及个数
     * 一个表示窗口中的字母及个数
     *
     * 注意Integer过大，自动拆箱问题
     */

    class Solution {
        public String minWindow(String s, String t) {
            if (s.length() == 0 || t.length() == 0 || t.length() > s.length()) {
                return "";
            }

            //用两个map一个记录需匹配的字符及个数，一个记录窗口中的字符
            Map<Character, Integer> map1 = new HashMap<>();
            Map<Character, Integer> map2 = new HashMap<>();

            for (int i = 0; i < t.length(); i++) {
                char c = t.charAt(i);
                int count = map1.getOrDefault(c, 0);
                map1.put(c, count + 1);
            }

            //form代表unique字符种类的个数，required代表总共独特字符的种类个数
            int left = 0, right = 0, form = 0, required = map1.size();

            //用一个数组存放答案,第一个元素为符合的字符串长度，第二第三元素为坐标
            int[] ans = new int[]{-1, 0, 0};

            //分两个判断，一判断right是否小于s.length
            while (right < s.length()) {
                //将此时的right位置元素加入窗口map
                char c = s.charAt(right);
                int count = map2.getOrDefault(c, 0);
                map2.put(c, count + 1);

                //如果加入的这个元素记录map中有，且加入后使此种的元素个数达到记录，则form++
                if (map1.containsKey(c) && map1.get(c).intValue() == map2.get(c).intValue()) {
                    form++;
                }

                //当form == required时，表示现在窗口中包含所有需要的字符，此时进行第二次判断，判断left移动时是否还包含
                while (left <= right && form == required) {
                    //当现在匹配字符串长度小于ans时，存放新长度及坐标
                    if (ans[0] == -1 || ans[0] > right - left + 1) {
                        ans[0] = right - left + 1;
                        ans[1] = left;
                        ans[2] = right;
                    }
                    //减去最左边元素，再判断是否还达到匹配要求
                    char cur = s.charAt(left);
                    int tem = map2.get(cur);
                    map2.put(cur, tem - 1);
                    //判断是否达到要求
                    if (map1.containsKey(cur) && map1.get(cur).intValue() > map2.get(cur).intValue()) {
                        //没有达到要求，form--
                        form--;
                    }
                    left++;
                }
                right++;
            }
            return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
        }
    }

    /**
     * 125 翻转字符串
     *
     */

    class Solution {
        public boolean isPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return true;
            }
            int head = 0;
            int tail = s.length() - 1;
            while (head < tail) {
                char a = s.charAt(head);
                char b = s.charAt(tail);
                if (!Character.isLetterOrDigit(a)) {
                    head++;
                } else if (!Character.isLetterOrDigit(b)) {
                    tail--;
                } else {
                    if (Character.toLowerCase(a) != Character.toLowerCase(b)) {
                        return false;
                    }
                    head++;
                    tail--;
                }
            }
            return true;
        }
    }

    /**
     * 409 字符串能组成的最长回文
     */

    class Solution {
        public int longestPalindrome(String s) {
            int ans = 0;
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < s.length(); i++) {
                if (!set.contains(s.charAt(i))) {
                    set.add(s.charAt(i));
                } else {
                    set.remove(s.charAt(i));
                    ans += 2;
                }
            }
            if (set.size() != 0) {
                ans += 1;
            }
            return ans;
        }
    }





    /**
     * 567 判断字符串s1的排列是否存在s2里
     * 排序后的字符串一定相等
     * 所以将排序后的s1与每一s1长度小段排序后的s2子串比较
     * 一样，则说明存在
     *
     * 第二种方法
     * 记录每个字符的出现次数
     * 然后一小段一小段比较
     */

    class Solution1 {
        public boolean checkInclusion(String s1, String s2) {
            s1 = sort(s1);
            for (int i = 0; i <= s2.length() - s1.length(); i++) {
                if (s1.equals(sort(s2.substring(i, i + s1.length())))) {
                    return true;
                }
            }
            return false;
        }

        private String sort(String s) {
            char[] tem = s.toCharArray();
            Arrays.sort(tem);
            return new String(tem);
        }
    }

    class Solution {
        public boolean checkInclusion(String s1, String s2) {
            int[] s1map = new int[26];
            for (int i = 0; i < s1.length(); i++) {
                s1map[s1.charAt(i) - 'a']++;
            }
            for (int i = 0; i <= s2.length() - s1.length(); i++) {
                int[] s2map = new int[26];
                for (int j = 0; j < s1.length(); j++) {
                    s2map[s2.charAt(i + j) - 'a']++;
                }
                if (match(s1map, s2map)) {
                    return true;
                }
            }
            return false;
        }

        private boolean match(int[] array1, int[] array2) {
            for (int i = 0; i < array1.length; i++) {
                if (array1[i] != array2[i]) {
                    return false;
                }
            }
            return true;
        }
    }




}