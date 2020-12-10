package bing.leetcode.dailychallenge;

/**
 * @author: baobing
 * @Date: 2020-11-26
 */

public class NovSixSolution {
    public int smallestDivisor(int[] nums, int threshold) {
        int small = 0;
        int sum;

        do {
            sum = 0;
            small++;
            for (int div : nums) {
                sum += division(div, small);
                if(sum > threshold){
                    break;
                }
            }

        } while (sum > threshold);


        return small;
    }

    private static int division(int a, int b) {
        int res = a / b;
        int mod = a % b;
        return (mod == 0) ? res : res + 1;
    }
}
