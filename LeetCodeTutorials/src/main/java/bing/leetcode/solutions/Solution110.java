package bing.leetcode.solutions;

import bing.leetcode.baseclass.TreeNode;


/**
 * @Description: Given a binary tree, determine if it is height-balanced.
 */

public class Solution110 {

    private boolean balanced = true;
    public boolean isBalanced(TreeNode root) {
        maxHigh(root);
        return balanced;
    }
    public int maxHigh(TreeNode node) {
        int leftDeath;
        int rightDeath;
        if (node == null) return 0;
        else {
            leftDeath = maxHigh(node.left);
            rightDeath = maxHigh(node.right);
            if (Math.abs(leftDeath - rightDeath) > 1) balanced = false;
            return Math.max(leftDeath, rightDeath) + 1;
        }
    }

}
