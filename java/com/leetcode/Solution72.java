package com.leetcode;

public class Solution72 {
    int[][] dist;
    public int minDistance(String word1, String word2) {
        int N = Math.max(word1.length(), word2.length());
        dist = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                dist[i][j] = Integer.MAX_VALUE;
        
        return compare(word1, word2, word1.length()-1, word2.length()-1);
    }

    private int compare(String p, String t, int i, int j) {
        if (i == -1) return 0;
        if (j == -1) return 0;
        if (dist[i][j] != Integer.MAX_VALUE) return dist[i][j];

        int cost = Math.min(
            compare(p, t, i-1, j-1) + match(p.charAt(i), t.charAt(j)), 
            //compare(p, t, i-1, j) + 1); 
            Math.min(compare(p, t, i, j-1), compare(p, t, i-1, j)) + 1);
        
        System.out.println(String.format("p[i] = %s, t[j] = %s, i = %s, j = %s, cost = %s", p.charAt(i), t.charAt(j), i, j, cost));
        dist[i][j] = cost;
        return cost;
    }

    private int match(char c, char d) {
        return c == d ? 0 : 1;
    }

    public static void main(String[] args) {
        System.out.println(String.format("minDistance = %s", 
            new Solution72().minDistance(args[0], args[1])));
    }
}
