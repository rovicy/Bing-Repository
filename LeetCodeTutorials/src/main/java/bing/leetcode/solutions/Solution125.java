package bing.leetcode.solutions;

/**
 * @Date: 2020/9/18
 */

public class Solution125 {
    public boolean isPalindrome2(String s) {
        if (s.isEmpty()) {
            return true;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        char[] allChar = s.toCharArray();
        for (char ch : allChar) {

            if (ch >= 'A' && ch <= 'z') {
                sb.append(ch);
            }
        }
        String str = sb.toString();
        if (str.isEmpty()) {
            return false;
        }
        return judge(str);
    }

    private boolean judge(String str) {
        char[] allChar = str.toCharArray();
        int size = allChar.length;
        for (int i = 0, j = size - 1; i <= j; i++, j--) {
            if (allChar[i] != allChar[j]) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int head = 0, tail = s.length() - 1;
        char cHead, cTail;
        while (head <= tail) {
            cHead = s.charAt(head);
            cTail = s.charAt(tail);
            if (!Character.isLetterOrDigit(cHead)) {
                head++;
            } else if (!Character.isLetterOrDigit(cTail)) {
                tail--;
            }else {
                if(Character.toLowerCase(cHead) != Character.toLowerCase(cTail)){
                    return false;
                }
                head++;
                tail--;
            }

        }
        return true;
    }
}
