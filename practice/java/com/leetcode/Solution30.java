package com.leetcode;

import java.util.*;

public class Solution30 {
    private class Node {
        char c;
        int val;
        Node[] links;
        public Node() {
            val = 0;
            links = new Node[256];
        }
        public Node(char c) {
            this();
            this.c = c;
        }
    }

    private Node root;

    public String get(String key) {
        if (key == null || key.length() < 0) return key;
        if (get(root, key, 0))
            return key;
        return "";
    }

    private boolean get(Node x, String key, int d) {
        if (x == null) return false;
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        if (c > x.c) return get(x.right, key, d);
        if (d < key.length()-1) return get(x.mid, key, d+1);
        return x.val != -1;
    }

    public void add(String key) {
        root = add(root, key, 0);
    }

    public Node add(Node x, String key, int d) {
        char c = key.charAt(d);
        if (x == null) x = new Node(c);
        if (c > x.c) x.right = add(x.right, key, d);
        else if (c < x.c) x.left = add(x.left, key, d);
        else if (d < key.length()-1) x.mid = add(x.mid, key, d+1);
        else x.val = 1;
        return x;
    }
    public List<Integer> findSubstring(String S, String[] L) {
        if (L == null || L.length == 0
            || S == null || S.length() == 0) return new ArrayList<Integer>();
  
        List<Integer> idxs = new ArrayList<Integer>();
        return idxs;
    }

    private long hash(String key, int R, int Q, int M) {
        long h = 0;
        for (int i = 0; i < M; i++) {
            h = (R*h + key.charAt(i)) % Q;
        }
    }

    private static void Main() {
        
    }
}
