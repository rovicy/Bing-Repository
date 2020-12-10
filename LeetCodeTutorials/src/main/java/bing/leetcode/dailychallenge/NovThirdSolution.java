package bing.leetcode.dailychallenge;

/**
 * @author: baobing
 * @Date: 2020-11-26
 */

public class NovThirdSolution {

    public int maxPower(String s) {
        if(s.length() == 0){
            return 0;
        }
        int max = 0;
        int length = s.length();
        int up = 0, down = 0, count = 0;
        while (down < length) {
            if (s.charAt(up) == s.charAt(down)) {
                count++;
                down++;
            } else {
                max = Math.max(max, count);
                count = 0;
                up = down;
            }
        }
        return Math.max(max, count);
    }
}
