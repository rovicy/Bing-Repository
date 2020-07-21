package bing.leetcode.solutions;

import bing.leetcode.baseclass.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description:
 */

public class Solution111 {

    public int minDepth(TreeNode root) {
        Object obj = new Object();
        return levelTransfer(root);
    }

    private int levelTransfer(TreeNode treeNode){
        int level = 0;
        if (treeNode == null) return level;
        Queue<TreeNode>queue = new LinkedList<>();
        queue.offer(treeNode);
        while (!queue.isEmpty()){
            level ++;
            int levelSize = queue.size();
            for(int i = 0; i < levelSize; i++){
                if(queue.peek().left == null && queue.peek().right == null){
                    return level;
                }
                if(queue.peek().left != null){
                    queue.offer(queue.peek().left);
                }
                if (queue.peek().right != null){
                    queue.offer(queue.peek().right);
                }
                queue.poll();
            }
        }
        return  level;
    }
}
