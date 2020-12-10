package bing.leetcode.solutions;

/**
 * @Description:
 */

public class Solution121 {
    public int maxProfit(int[] prices) {
        int max = 0;
        for(int i = 0; i < prices.length -1; i++){
            if(prices[i] < prices[i+1]) {
                for(int j = i+1; j <prices.length ; j++){
                    if(prices[i] < prices[j]){
                        max = Math.max(max, prices[j] -prices[i]);
                    }
                }
            }
        }
        return max;
    }
}
