package com.tc;

import java.util.*;

public class WordFind {
    //50*(50)(51)
    private class Node {
        char c;
        int val, x, y;
        Node left, mid, right;
        public Node(char c) {
            this.c = c;
            x = y = val = -1;
        }
    }

    public String[] findWords(String[] grid, String[] wordList) {
        if (grid == null || grid.length == 0) return grid;
        if (wordList == null || wordList.length == 0) return wordList;
        Iterable<Integer> wordSizes = 
            getWordSizes(wordList, Math.max(grid.length, grid[0].length()));
        Node x = buildDict(grid, wordSizes);
        return findWords(x, wordList);
        //return new String[0];
    }

    private String[] findWords(Node x, String[] wordList) {
        String[] coords = new String[wordList.length];
        for (int i = 0; i < wordList.length; i++)
            coords[i] = wordList[i] == "" ? "" : get(x, wordList[i], 0);
        return coords;
    }

    private String get(Node x, String key, int d) {
        if (x == null) return "";
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        if (c > x.c) return get(x.right, key, d);
        if (d == key.length()-1) return get(x.mid, key, d+1);
        return x.val == 1 ? x.x +  " " + x.y : "";
    }

    private Node buildDict(String[] grid, Iterable<Integer> wordSizes) {
        int n = grid[0].length(),
            m = grid.length;
        Node root = null;
               
        // horizontal
        for (int s: wordSizes)
            for (int i = 0; i < m; i++)
                for (int j = 0; j+s <= n; j++) {
                    //System.out.format("vert = %s\n", grid[i].substring(j, j+s));
                    root = addKey(root, grid[i].substring(j, j+s), 0, i, j);
                }
      
        /*
        TEST 
        GOAT 
        BOAT 
        ROWS 
        JEAN
        */

        StringBuilder sb = null;
        // vertical
        for (int s: wordSizes)
            for (int i = 0; i < n; i++)
                for (int j = 0; j+s-1 < m; j++) {
                    sb = new StringBuilder(s);
                    for (int k = j; k < j+s; k++)
                        sb.append(grid[k].charAt(i));
                    System.out.format("vert = %s\n", sb.toString());
                    root = addKey(root, sb.toString(), 0, j, i);
                }
       
        // diagonal
        for (int s: wordSizes)
            for (int i = 0; i+s-1 < m; i++)
                for (int j = 0; j+s-1 < n; j++) {
                    sb = new StringBuilder(s);
                    for (int k = j, l = i; k < j+s; k++, l++)
                        sb.append(grid[l].charAt(k));
                    System.out.format("diag = %s\n", sb.toString());
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

    /*
    private char[][] toCharGrid(String[] grid) {
        char[][] gridArr = new char[grid.length][grid[0].length()];
        for (int j = 0; j < grid.length; j++) {
            String s = grid[j];
            for (int i = 0; i < s.length(); i++)
                gridArr[j][i] = s.charAt(i);
        }
        return gridArr;
    }
    */

    private Node addKey(Node n, String key, int d, int x, int y) {
        char c = key.charAt(d);
        if (n == null) n = new Node(c);
        if (c < n.c) n.left = addKey(n.left, key, d, x, y);
        else if (c > n.c) n.right = addKey(n.right, key, d, x, y);
        else if (d == key.length()-1) n.mid = addKey(n.mid, key, d+1, x, y);
        else if (n.val != 1) {
            n.val = 1;
            n.x = x;
            n.y = y;
        };
        return n;
    }

    /*
    4 abcde
    3 fghij
    2 klmno
    1 pqrst
    0 uvwxy
      01234
    

    private Node addWord(Node n, 
        char[][] wordGrid, 
        int xp,
        int yp,
        int x, 
        int y,
        int end,
        int mode) {
        char c = wordGrid[x][y];
        if (n == null) n = new Node(c);
        if (c < n.c) n.left = addWord(n.left, wordGrid, xp, yp, x, y, end, mode);
        else if (c > n.c) n.right = addWord(n.right, wordGrid, xp, yp, x, y, end, mode);
        else if (mode == 1 && x < end-1) 
            n.mid = addWord(n.mid, wordGrid, xp, yp, x+1, y, end, mode);
        else if (mode == 2 && y > end+1) 
            n.mid = addWord(n.mid, wordGrid, xp, yp, x, y-1, end, mode);
        else if (mode == 3 && y > end+1) 
            n.mid = addWord(n.mid, wordGrid, xp, yp, x+1, y-1, end, mode);
        else {
            n.val = 1;
            n.x = xp;
            n.y = yp;
        };
        return n;
    }
    */

    public static void main(String[] args) {
        new WordFind().findWords(args[0].split(","), args[1].split(","));
    }
}
