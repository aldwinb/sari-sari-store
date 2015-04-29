package com.leetcode;

import java.util.*;

public class Solution99 {
  
  private class LostTree {
    TreeNode parent, child;
    boolean less;
    public LostTree(TreeNode child, TreeNode parent, boolean less) {
      this.child = child;
      this.parent = parent;
      this.less = less;
    }
  }
  
  private LostTree m1, m2;
  
  public void recoverTree(TreeNode root) {
    m1 = m2 = null;
    searchMiss(root, false);
    
    /*
    System.out.format("child1 = %s, parent1 = %s, child2 = %s, parent2 = %s\n",
      m1.child.val, 
      m1.parent.val, 
      m2 != null ? m2.child.val : "--",
      m2 != null ? m2.parent.val : "--");
    */
    
    if (m1 != null && m2 != null) {
      if ((m1.less && m2.child.val < m1.parent.val)
        || !m1.less && m2.child.val > m1.parent.val)
        swap(m1.child, m2.child);
      else
        swap(m1.child, m2.parent);
    } else
      swap(m1.child, m1.parent);
  }


  /*
        5 
      1   *4
        *7
        
    x = 5, findMin = true
      x = 1, findMin = false
        x = 7, findMin = true
        ret = 7
      r = 7
      r.val < x.val = 7 < 1
      ret = 7
    l = 7
      x = 4, findMin = true
      ret = 4
    r = 4
    l.val > x.val = 7 > 5, m1 = { c:7, p:5, l:true }
    r.val < x.val = 4 < 5, m2 = { c:4, p:5, l:false }
    
    m2 == null - no
    true && 5 > 4 - yes
    swap (7, 4)
    
    *3
       2
        *1
      
    x = 3, findMin = true
      x = 2, findMin = true
        x = 1, findMin = true
        ret = 1
      r = 1
      r.val < x.val = 1 < 2, m1 = { c:1, p:2, l:false }
      ret = 2
    r = 2
    r.val < x.val = 2 < 3, m2 = { c:2, p:3, l:false }
    
    m2 == null - no
    false && 2 < 2 - no
    swap (1, 3)
    
          *1
         3
       2
    *4
    
    x = 1, findMin = true
      x = 3, findMin = false
        x = 2, findMin = false
          x = 4, findMin = false
          ret = 4 
        l = 4
        l.val > x.val = 4 > 2, m1 = { c:4, p:2, l:true }
        ret = 2
      l = 2
      l.val > x.val = 2 > 3
      ret = 3
    l = 3
    l.val > x.val = 3 > 1, m2 = { c:3, p:1, l:true }
    
    m2 == null - no
    true && 2 > 3 - no
    
    swap (4, 1)
    
        1
      2
    
    x = 1, findMin = true
      x = 2, findMin = false
      ret = 2
    l = 2
    l.val > x.val = 2 > 1, m1 = { c:2, p:1, l:true }
    
    swap (2, 1)
    
  */
  
  private TreeNode searchMiss(TreeNode x, boolean findMin) {
    if (x.left == null && x.right == null) return x;
    if (m1 != null && m2 != null) return null;
    TreeNode l = null,
      r = null;
    if (x.left != null) l = searchMiss(x.left, false);
    if (x.right != null) r = searchMiss(x.right, true);
    
    boolean leftIsLost = false, 
      rightIsLost = false;
    if (l != null) {
      if (l.val > x.val) {
        if (m1 == null)
          m1 = new LostTree(l, x, true);
        else
          m2 = new LostTree(l, x, true);
        leftIsLost = true;
      }
    }
    
    if (r != null) {
      if (r.val < x.val) {
        if (m1 == null)
          m1 = new LostTree(r, x, false);
        else
          m2 = new LostTree(r, x, false);
        rightIsLost = true;
      }
    }
    
    return findMin 
      ? minNode(x, minNode(!leftIsLost ? l : null, !rightIsLost ? r : null)) 
      : maxNode(x, maxNode(!leftIsLost ? l : null, !rightIsLost ? r : null));
  }

  private TreeNode minNode(TreeNode n1, TreeNode n2) {
    if (n1 != null && n2 != null)
      return n1.val <= n2.val ? n1 : n2;
    if (n1 == null)
      return n2;
    return n1;
  }
  
  private TreeNode maxNode(TreeNode n1, TreeNode n2) {
    if (n1 != null && n2 != null)
      return n1.val > n2.val ? n1 : n2;
    if (n1 == null)
      return n2;
    return n1;
  }
  
  private void swap(TreeNode n1, TreeNode n2) {
    int tmp = n1.val;
    n1.val = n2.val;
    n2.val = tmp;
  }
  
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
