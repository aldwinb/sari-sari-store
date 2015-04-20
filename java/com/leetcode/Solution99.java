package com.leetcode;

import java.util.*;

public class Solution99 {
  private class MisplacedTree {
    TreeNode parent, miss;
    public MisplacedTree(TreeNode parent, TreeNode miss) {
      this.parent = parent;
      this.miss = miss;
    }
  }

  public void recoverTree(TreeNode root) {
    MisplacedTree mt = searchMiss(root);
    if (!searchMiss(root, root.val))
      swap(mt.parent, mt.miss);
  }

  /*
   *          6
   *        4    8
   *      2 *7 *5 9
   *     1 3       10
   *
   *  1 3 2 7 4 
   *  5 10 9 8
   *
   *  3
   *    2
   *      1
   */

  private void searchMiss(TreeNode x, int rootVal) {
    if (x == null) return null;
    TreeNode l = searchMore(x.left, rootVal),
      r = searchLess(x.right, rootVal);
    if (l != null && r != null) {
      swap(l, r);
      return;
    }
    if (l != null)
      searchMiss(x.left, x.left.val);
    else
      searchMiss(x.right, x.right.val);
  }

  private TreeNode searchLess(TreeNode x, int val) {
    if (x == null) return null;
    TreeNode less = searchLess(x.left, val);
    if (less != null) return less;
    less = searchLess(x.right, val);
    if (less != null) return less;
    if (x.val < val) return x;
    return null;
  }

  private TreeNode searchMore(TreeNode x, int val) {
    if (x == null) return null;
    TreeNode less = searchMore(x.left, val);
    if (less != null) return less;
    less = searchMore(x.right, val);
    if (less != null) return less;
    if (x.val > val) return x;
    return null;
  }
  /*
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

    boolean fixed = false;
    if (x.left != null) {
      fixed = tryFix(x.left, mt);
      if (fixed) return fixed;
      if (x.equals(mt.miss)) return false;
      if (x.val > mt.miss.val) {
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
  */
  
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    Solution99 soln = new Solution99();
    while (s.hasNextLine()) {
      String[] testCase = s.nextLine().split("\\t");
      List<TreeNode> nodes = getNodeList(testCase[0].split(","));
      TreeNode t = buildTree(nodes);
      soln.recoverTree(t);
      String actual = stringify(nodes);
      if (testCase[1].compareTo(actual) != 0)
        System.out.format("%s\texpected=%s\tactual=%s\n", 
          testCase[0],
          testCase[1],
          actual);
    }
  }

  private static List<TreeNode> getNodeList(String[] s) {
    List<TreeNode> nodes = new ArrayList<TreeNode>(s.length);
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
    for (int i = 1; i < nodes.size(); i += 2) {
        TreeNode parent = nodes.get(i/2);
        if (parent == null) continue;
        parent.left = nodes.get(i);
        parent.right = nodes.get(i+1);
    }

    return t;
  }

  private static String stringify(List<TreeNode> nodes) {
    StringBuilder sb = new StringBuilder();
    for (TreeNode n : nodes) {
      if (n != null)
        sb.append(n.val);
      else
        sb.append("#");
      sb.append(",");
    }
    return sb.deleteCharAt(sb.length()-1).toString();
  }

}
