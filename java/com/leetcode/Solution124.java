package com.leetcode;

import java.util.*;

public class Solution124 {
    private class SumNode {
        int val;
        int dcval;

        public SumNode(int val, int dcval) {
            this.val = val;
            this.dcval = dcval;
        }
    }

    public int maxPathSum(TreeNode root) {
        SumNode n = maxSums(root);
        return Math.max(n.val, n.dcval);
    }

    private SumNode maxSums(TreeNode x) {
        if (x == null) return null;
        
        SumNode l = maxSums(x.left),
            m = maxSums(x.right),
            mid = new SumNode(x.val, Integer.MIN_VALUE);
     
        if (l == null && m == null) return mid;
        if (l == null) return maxNode(mid, m);
        if (m == null) return maxNode(mid, l);

        int val = getVal(mid.val, 
            Math.max(l.val, m.val));
        int dcval = getDcval(mid.val, 
            Math.max(l.val, m.val),
            Math.min(l.val, m.val),
            Math.max(l.dcval, m.dcval));

        //System.out.println(String.format("val = %s, dcval = %s", val, dcval));
        return new SumNode(val, dcval);
    }

    private SumNode maxNode(SumNode p, SumNode c1) {
        return new SumNode(getVal(p.val, c1.val), getDcval(p.val, c1.val, c1.dcval));
    }

    private int getDcval(int pval, int mval, int lval, int cmpval) {
        int sum = pval+mval+lval;
        if (sum > cmpval && sum > pval) return sum;
        return getDcval(pval, mval, cmpval);
    }

    private int getDcval(int pval, int mval, int cmpval) {
        int sum = pval+mval;
        if (sum > cmpval && sum > pval) return sum;
        return Math.max(pval, Math.max(mval, cmpval));
    }

    private int getVal(int pval, int mval) {
        return Math.max(pval+mval, pval);
    }

    /*
    private SumNode maxSum(TreeNode x) {
        if (x == null) return null;
        SumNode less = maxSum(x.left),
            more = maxSum(x.right),
            mid = new SumNode(x.val, true);
      
        if (less == null && more == null) return mid;
        if (less == null) return choose(more, mid);
        if (more == null) return choose(less, mid);
    
        SumNode tmp = null;
        if (less.val > more.val) {
            tmp = more;
            more = less;
            less = tmp;
        }

        if (!less.connected && !more.connected) 
            return greater(more, mid);
        
        if (more.connected)
            return choose(more, mid, less);
           
        if (less.val+mid.val >= less.val
            && less.val+mid.val >= mid.val
            && less.val+mid.val >= more.val) {
            less.val += mid.val;
            return less;
        }
        return greater(more, mid);
    }

    private SumNode choose(SumNode node, SumNode root, SumNode aux) {
        if (node.connected 
            && aux.connected 
            && node.val+root.val+aux.val >= node.val+root.val
            && node.val+root.val+aux.val >= node.val
            && node.val+root.val+aux.val >= root.val) {
            node.val += root.val+aux.val;
            return node;
        }
        return choose(node, root);
    }

    private SumNode choose(SumNode child, SumNode parent) {
        if (child.val+parent.val >= child.val
            && child.val+parent.val >= parent.val) {
            child.val += parent.val;
            child.dcval
            return child;
        }
        return greater(child, parent);
    }

    private SumNode greater(SumNode s1, SumNode s2) {
        return s1.val >= s2.val ? s1 : s2;
    }
    */

    public static void main(String[] args) {
        String[] s = args[0].split(",");
        List<TreeNode> nodes = new ArrayList<TreeNode>(s.length);
        nodes.add(null);
        TreeNode t = new TreeNode(Integer.parseInt(s[0]));
        nodes.add(t);
        if (s.length > 1) {
            if (s[1].compareTo("#") != 0) {
                t.left = new TreeNode(Integer.parseInt(s[1]));
                nodes.add(t.left);
            } 
        }
        if (s.length > 2) {
            if (s[2].compareTo("#") != 0) {
                t.right = new TreeNode(Integer.parseInt(s[2]));
                nodes.add(t.right);
            } 
        }
        if (s.length > 3) {
            for (int i = 3; i < s.length; i += 2) {
                TreeNode parent = nodes.get((i+1)/2),
                    node = null;
                if (s[i].compareTo("#") != 0) {
                    node = new TreeNode(Integer.parseInt(s[i]));
                    parent.left = node;
                    nodes.add(node);
                }
                if (i+1 < s.length && s[i+1].compareTo("#") != 0) {
                    node = new TreeNode(Integer.parseInt(s[i+1]));
                    parent.right = node;
                    nodes.add(node);
                }
            }
        }
        System.out.println(String.format("maxPathSum = %s",
            new Solution124().maxPathSum(t)));
        //print(t, 0);
        //System.out.println("");
    }

    private static void print(TreeNode x, int lvl) {
        for (int i = 0; i < lvl; i++)
            System.out.print("-");
        System.out.println(String.format(" %s", x.val));
        if (x.left != null) {
            print(x.left, lvl+1);
        }
        if (x.right != null) {
            print(x.right, lvl+1);
        }
    }
}
