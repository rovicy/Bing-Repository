package bing.leetcode.solutions;

/**
 * @Date: 2020/9/21
 */

public class Solution136 {

    public Solution136(){

    }

    public int singleNumber(int[] nums) {
        int result = 0;
        for(int i : nums) {
            result ^= i;
            System.out.print(result + " ");
        }
        return result;
    }

}
