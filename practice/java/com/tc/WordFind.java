package com.tc;

import java.util.*;

public class WordFind {
    private class Node {
        char c;
        int x, y;
        Node left, mid, right;
        public Node(char c) {
            this.c = c;
            x = y = Integer.MAX_VALUE;
        }
    }

    public String[] findWords(String[] grid, String[] wordList) {
        if (grid == null || grid.length == 0) return grid;
        if (wordList == null || wordList.length == 0) return wordList;
        Iterable<Integer> wordSizes = 
            getWordSizes(wordList, Math.max(grid.length, grid[0].length()));
        Node x = buildDict(grid, wordSizes);
        return findWords(x, wordList);
    }

    private String[] findWords(Node x, String[] wordList) {
        String[] coords = new String[wordList.length];
        for (int i = 0; i < wordList.length; i++) {
            int n = wordList[i].length();
            coords[i] = n > 0 && n <= 50
                ? get(x, wordList[i], 0)
                : "";
        }
        return coords;
    }

    private String get(Node x, String key, int d) {
        if (x == null) return "";
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        if (c > x.c) return get(x.right, key, d);
        if (d < key.length()-1) return get(x.mid, key, d+1);
        return x.x != Integer.MAX_VALUE ? x.x +  " " + x.y : "";
    }

    private Node buildDict(String[] grid, Iterable<Integer> wordSizes) {
        int n = grid[0].length(),
            m = grid.length;
        Node root = null;
               
        // horizontal
        for (int s: wordSizes)
            for (int i = 0; i < m; i++)
                for (int j = 0; j+s <= n; j++) {
                    root = addKey(root, grid[i].substring(j, j+s), 0, i, j);
                }
        
        StringBuilder sb = null;
        // vertical
        for (int s: wordSizes)
            for (int i = 0; i < n; i++)
                for (int j = 0; j+s-1 < m; j++) {
                    sb = new StringBuilder(s);
                    for (int k = j; k < j+s; k++)
                        sb.append(grid[k].charAt(i));
                    root = addKey(root, sb.toString(), 0, j, i);
                }
       
        // diagonal
        for (int s: wordSizes)
            for (int i = 0; i+s-1 < m; i++)
                for (int j = 0; j+s-1 < n; j++) {
                    sb = new StringBuilder(s);
                    for (int k = j, l = i; k < j+s; k++, l++)
                        sb.append(grid[l].charAt(k));
                    root = addKey(root, sb.toString(), 0, i, j);
                }
       
        return root;
    }

    private Iterable<Integer> getWordSizes(String[] wordList, int M) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < wordList.length; i++) {
            int n = wordList[i].length();
            if (n <= M) set.add(n);
        }

        assert set.size() > 0;
        return set;
    }

    private Node addKey(Node n, String key, int d, int x, int y) {
        char c = key.charAt(d);
        if (n == null) n = new Node(c);
        if (c < n.c) n.left = addKey(n.left, key, d, x, y);
        else if (c > n.c) n.right = addKey(n.right, key, d, x, y);
        else if (d < key.length()-1) n.mid = addKey(n.mid, key, d+1, x, y);
        else if (n.x > x) {
            n.x = x;
            n.y = y;
        } else if (n.x == x && n.y > y)
            n.y = y;
        return n;
    }

    public static void main(String[] args) {
        String[] coords = new WordFind().findWords(args[0].split(","), args[1].split(","));
        for (String s : coords)
            System.out.format("%s,", s);
        System.out.println("");
    }
}
