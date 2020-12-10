package bing.leetcode.dailychallenge;

import bing.leetcode.baseclass.ListNode;

/**
 * @Date: 2020/11/24
 * Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1. The linked list holds the binary representation of a number.
 * <p>
 * Return the decimal value of the number in the linked list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: head = [1,0,1]
 * Output: 5
 * Explanation: (101) in base 2 = (5) in base 10
 * <p>
 * <p>
 * Example 2:
 * Input: head = [0]
 * Output: 0
 */

public class NovFirstSolution {
    public int getDecimalValue(ListNode head) {
        int sum = 0;
        while (head != null && head.val == 0) {
            head = head.next;
        }
        while (head != null) {
            sum = sum *2 + head.val;
            head=head.next;
        }

        return sum;
    }

}
