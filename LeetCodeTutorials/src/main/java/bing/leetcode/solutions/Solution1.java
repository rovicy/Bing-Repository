package bing.leetcode.solutions;

/**
 * @author: baobing
 * @Date: 2020-11-27
 */

public class Solution1 {
    public int[] twoSum(int[] numArr, int target) {

        int size = numArr.length;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (numArr[j] == target - numArr[i]) {
                    int[] res = new int[2];
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return new int[1];
    }
}
