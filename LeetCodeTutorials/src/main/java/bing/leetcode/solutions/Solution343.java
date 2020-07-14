package bing.leetcode.solutions;

/**
 * @Description:
 */

public class Solution343 {

    public Solution343() {

    }

    public int integerBreak(int n) {
        if (n <= 2) {
            return 1;
        }
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            int res = breakOnce(n, i);
            sum = Math.max(sum, res);
        }
        return sum;
    }

    private int breakOnce(int n, int m) {
        int sum = 1;
        int count = n / m;
        int other = n % m;
        for (int i = 1; i <= m; i++) {
            if(i <= other){
                sum *= (count +1);
            }
            else {
                sum *= count;
            }
        }
        System.out.println("The sum is: " + sum + ", the m is:" + m);
        return sum;
    }

}
