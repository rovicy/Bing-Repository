package bing.leetcode.solutions;

import bing.leetcode.baseclass.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Description: Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7];
 * return its bottom-up level order traversal as:
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 */

public class BinaryTreeLevelOrderTraversalSolution {
    private Queue<TreeNode> queue = new LinkedBlockingDeque<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        push(root);
        while (queue.size() > 0) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size > 0) {
                TreeNode node = poll();
                list.add(node.getVal());
                if(node.getLeft() != null){
                    push(node.getLeft());
                }
                if(node.getRight() != null){
                    push(node.getRight());
                }
                size--;
            }
            lists.add(list);
        }
        for(int i = lists.size() -1; i >=0; i--){
            result.add(lists.get(i));
        }
        return result;
    }

    private TreeNode poll() {
        return queue.poll();
    }

    private void push(TreeNode node) {
        if (node != null) {
            queue.add(node);
        }
    }

}
