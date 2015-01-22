package com.leetcode;

public class Solution72 {
    int[][] ed;
    public int minDistance(String word1, String word2) {
        
    }

    private int edit(String word1, String word2, int i, int j) {
        if (i == word2.length() && j == word2.length()) return 0;

        if (ed[i][j] != Integer.MIN_VALUE) return ed[i][j];

        if (i >= word1.length()) return edit(word1, word2, i, j+1);
    }
}
