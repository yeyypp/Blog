public class Main {
    /**
     * excel 对照表
     * 类似于十进制转换，现在是26进制
     * CDA = 3 * 26 * 26 + 4 * 26 + 1
     *
     */

    class Solution {
        public int titleToNumber(String s) {
            int result = 0;
            for (int i = 0; i < s.length(); i++) {
                result = result * 26 + s.charAt(i) - 'A' + 1;
            }
            return result;
        }
    }
}