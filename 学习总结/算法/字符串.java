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


}