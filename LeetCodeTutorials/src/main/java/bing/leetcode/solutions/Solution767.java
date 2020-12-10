package bing.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: baobing
 * @Date: 2020-11-30
 */

public class Solution767 {
    public String reorganizeString(String str) {
        int length = str.length();
        int[] countNum = new int[26];
        //记录字符出现的次数；
        for (int i = 0; i < length; i++) {
            countNum[str.charAt(i) - 'a']++;
        }
        //查看出现最多次数的字符以及次数，若超过半数，则返回空；
        int countMax = 0, index = 0, threshold = (length + 1) >> 1;
        for (int i = 0; i < 26; i++) {
            if (countMax < countNum[i]) {
                countMax = countNum[i];
                index = i;
                if (countMax > threshold) {
                    return "";
                }
            }
        }
        //填充次数最多的字符；
        char[] res = new char[length];
        int pos = 0;
        while (countNum[index]-- > 0) {
            res[pos] = (char) (index + 'a');
            pos += 2;
        }
        //填充其它字符；
        for (int i = 1; i < length; ) {

        }

        return "";
    }
}
