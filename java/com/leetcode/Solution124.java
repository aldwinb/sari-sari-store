package com.leetcode;

import java.util.*;

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
        return maxSum(root).val;
    }

    private PathSum maxSum(TreeNode x) {
        if (x == null) return new PathSum(0, true);
        PathSum less = maxSum(x.left),
            more = maxSum(x.right),
            tmp = null;
      
        if (less.val > more.val) {
            tmp = more;
            more = less;
            less = tmp;
        }

        if (!less.connected && !more.connected) {
            if (x.val > more.val) return new PathSum(x.val, true);
            return more;
        }
        
        if (more.connected) {
            if (more.val+x.val >= more.val) {
                more.val += x.val;
                if (less.connected && more.val+less.val >= more.val) 
                    more.val += less.val;
                return more;
            } else if (x.val > more.val) return new PathSum(x.val, true); 
            more.connected = false;
            return more;
        }
            
        if (less.val+x.val >= less.val) {
            less.val += x.val;
            return less; 
        } else if (x.val > more.val) return new PathSum(x.val, true); 
        return more;
        
        /*
        if (!less.connected) {
            if (more.val+x.val >= more.val) {
                more.val += x.val;
                return more; 
            } else if (x.val > more.val) return new PathSum(x.val, false); 
            more.connected = false;
            return more;
        }
        */

    }

    public static void main(String[] args) {
        String[] s = args[0].split(",");
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        TreeNode t = new TreeNode(Integer.parseInt(s[0]));
        nodes.add(t);
        if (s.length > 1) {
            t.left = new TreeNode(Integer.parseInt(s[1]));
            nodes.add(t.left);
        }
        if (s.length > 2) {
            t.right = new TreeNode(Integer.parseInt(s[2]));
            nodes.add(t.right);
        }
        if (s.length > 3) {
            for (int i = 3, j = 2; i < s.length; i+=2, j++) {
                TreeNode node = new TreeNode(Integer.parseInt(s[i])),
                    parent = nodes.get(i-j);
                nodes.add(node);
                parent.left = node;
                if (i+1 < s.length) {
                    node = new TreeNode(Integer.parseInt(s[i+1]));
                    nodes.add(node);
                    parent.right = node;
                }
            }
        }
        System.out.println(String.format("maxPathSum = %s",
            new Solution124().maxPathSum(t)));
        //print(t);
        //System.out.println("");
    }

    private static void print(TreeNode x) {
        if (x == null) {
            System.out.print(".");
            return;
        }
        System.out.print(String.format("%s", x.val));
        System.out.print("--L-->"); 
        print(x.left);
        System.out.print("--R-->"); 
        print(x.right);
    }
}
