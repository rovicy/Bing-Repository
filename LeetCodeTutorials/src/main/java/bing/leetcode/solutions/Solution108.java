package bing.leetcode.solutions;

import bing.leetcode.baseclass.TreeNode;

/**
 * @Description:
 */

public class Solution108 {

    public Solution108(){

    }
    public TreeNode sortedArrayToBST(int[] numArray) {
        return sortOnce(numArray, 0, numArray.length -1);
    }

    private TreeNode sortOnce(int[] numArray, int lo, int hi){
        if(lo > hi) return null;
        int mid = (hi - lo)/2 + lo;
        TreeNode root = new TreeNode(numArray[mid]);
        root.left = sortOnce(numArray, lo, mid -1);
        root.right = sortOnce(numArray, mid +1, hi);
        return root;
    }

    public void Traverse(TreeNode root){
        if(root != null){
            System.out.print(root.val + " ");
            Traverse(root.left);
            Traverse(root.right);
        }
    }

}
