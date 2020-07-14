package bing.leetcode.solutions;

import bing.leetcode.baseclass.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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


    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> sourceList = new LinkedList<>();

        if(root == null) return sourceList;

        queue.offer(root);
        while (!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i<levelSize; i++){
                if(queue.peek().getLeft() != null) queue.offer(queue.peek().getLeft());
                if(queue.peek().getRight() != null) queue.offer(queue.peek().getRight());
                list.add(queue.poll().getVal());

            }
            sourceList.add(0, list);
        }
        return sourceList;
    }

}
