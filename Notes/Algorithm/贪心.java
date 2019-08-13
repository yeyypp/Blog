public class Main {
    /**
     * 122.买股票最佳算法2
     * 能买卖多次
     */

    class Solution {
        public int maxProfit(int[] prices) {
            if (prices.length == 0 || prices == null) {
                return 0;
            }
            int diff = 0;
            int[] profit = new int[prices.length];
            profit[0] = 0;
            for (int i = 1; i < prices.length; i++) {
                diff = prices[i] - prices[i - 1];
                profit[i] = diff > 0 ? profit[i - 1] + diff : profit[i - 1];
            }
            return profit[prices.length - 1];
        }
    }
}