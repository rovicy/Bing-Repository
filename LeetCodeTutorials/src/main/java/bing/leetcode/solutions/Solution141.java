package bing.leetcode.solutions;

import bing.leetcode.baseclass.ListNode;

/**
 * @Date: 2020/9/21
 */

public class Solution141 {

    public Solution141(){

    }

    public boolean hasCycle(ListNode head) {
        ListNode walker = head;
        ListNode runner = head;
        while(runner!=null && runner.next!=null) {
            walker = walker.next;
            runner = runner.next.next;
            if(walker==runner) return true;
        }
        return false;
    }
}
