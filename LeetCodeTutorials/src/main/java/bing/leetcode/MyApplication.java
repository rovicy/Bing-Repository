package bing.leetcode;


import bing.leetcode.baseclass.TreeNode;
import bing.leetcode.solutions.BinaryTreeLevelOrderTraversalSolution;

import java.util.List;

/**
 * @Description:
 */

public class MyApplication {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        BinaryTreeLevelOrderTraversalSolution solution = new BinaryTreeLevelOrderTraversalSolution();
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node3 = new TreeNode(3, null, node6);
        TreeNode node1 = new TreeNode(1, node2, node3);
        List<List<Integer>> list = solution.levelOrderBottom(node1);
        System.out.println(list);
    }

}
