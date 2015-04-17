package com.leetcode;

public class Solution98 {
  private class MisplacedTree {
    TreeNode parent, miss;
    public MisplacedTree(TreeNode parent, TreeNode miss) {
      this.parent = parent;
      this.miss = miss;
    }
  }

  public void recoverTree(TreeNode root) {
    MisplacedTree mt = searchMiss(root);
    if (!tryFix(root, mt))
      swap(mt.parent, mt.miss);
  }

  private MisplacedTree searchMiss(TreeNode x) {
    if (x.left == null && x.right == null) return null;
    
    MisplacedTree mt;
    if (x.left != null) {
      mt = searchMiss(x.left);
      if (mt != null) return mt;
      if (x.val < x.left.val) return new MisplacedTree(x,x.left);
    }

    mt = searchMiss(x.right);
    if (mt != null) return mt;
    if (x.val > x.right.val) return new MisplacedTree(x,x.right);

    return mt;
  }

  private boolean tryFix(TreeNode x, MisplacedTree mt) {
    if (x.left == null && x.right == null) return false;
    if (x.equals(mt.parent)) return false;

    boolean fixed = false;
    if (x.left != null) {
      fixed = tryFix(x.left, mt);
      if (fixed) return fixed;
      if (x.val > mt.miss.val && mt.parent.val > x.left.val) {
        swap(x.left, mt.miss);
        return true;
      }
    }

    fixed = tryFix(x.right, mt);
    if (fixed) return fixed;
    if (x.val < mt.miss.val && mt.parent.val < x.right.val) {
      swap(x.right, mt.miss);
      return true;
    }

    return fixed;
  }

  private void swap(TreeNode a, TreeNode b) {
    int temp = a.val;
    a.val = b.val;
    b.val = temp;
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    Solution98 soln = new Solution98();
    while (!s.hasNextLine()) {
      String[] testCase = s.nextLine.split("\\t");
      List<TreeNode> nodes = getNodeList(testCase[0].split(","));
      
    }
  }

  private static List<TreeNode> getNodeList(String[] s) {
    List<TreeNode> nodes = new ArrayList<TreeNode>(s.length);
    //nodes.add(null);
    nodes.add(new TreeNode(Integer.parseInt(s[0])));
    for (int i = 1; i < s.length; i++) {
      if (s[i].compareTo("#") != 0)
        nodes.add(new TreeNode(Integer.parseInt(s[i])));
      else
        nodes.add(null);
    }
    return nodes;
  }

  private static TreeNode buildTree(List<TreeNode> nodes) {
    TreeNode t = nodes.get(0);
    /*
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
    */
    for (int i = 1; i < nodes.size(); i += 2) {
        TreeNode parent = nodes.get(i/2);
        if (parent == null) continue;
        parent.left = nodes[i];
        parent.right = nodes[i+1];
    }

    return t;
  }
}
