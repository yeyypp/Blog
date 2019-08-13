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

    /**
     * x的平方根
     *
     */

    class Solution {
        public int sqrt(int x) {
            if (x == 0) {
                return 0;
            }
            int low = 1;
            int high = x;
            int ans = 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (mid == x / mid) {
                    return mid;
                }
                //注意mid * mid可能会大于最大int，所以要用x / mid
                if (mid < x / mid) {
                    ans = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return ans;
        }
    }
}