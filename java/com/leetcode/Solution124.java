package com.leetcode;

public class Solution124 {
    private class PathSum {
        int val;
        boolean connected;
        public PathSum(int val, boolean connected) {
            this.val = val;
            this.connected = connected;
        }
    }

    public int maxPathSum(TreeNode root) {
        return maxSum(root);
    }

    private PathSum maxSum(TreeNode x) {
        if (x == null) return new PathSum(0, true);
        PathSum left = maxSum(x.left),
            right = maxSum(x.right);
        if (left.connected && right.connected)
            return Math.max(left.val+right.val, Math.max(left.val, right.val)) + x.val;
        if (!left.connected && !right.connected)
            return Math.max(x.val, Math.max(left.val, right.val));    
        if (left.connected)
            return Math.max(left.val, Math.max(x.val+right.val, Math.max(x.val, right
    }

    public static void main(String[] args) {
        
    }
}
