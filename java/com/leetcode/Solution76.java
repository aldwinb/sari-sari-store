package com.leetcode;

public class Solution76 {
    public String minWindow(String S, String T) {
        int R = S.length(),
            M = S.length(),
            Q = 997;
        System.out.println(String.format("hash = %s", hash(S, R, M, Q)));
        return "";
    }

    private int hash(String p, int R, int M, int Q) {
        int h = 0;//p.charAt(0) % Q;
        for (int i = 0; i < M; i++) {
            h = (h * R + p.charAt(i)) % Q;
            System.out.println(String.format("charAt = %s, h = %s", p.charAt(i), h));
        }
        return h;
    }

    public static void main(String[] args) {
        new Solution76().minWindow(args[0], args[1]);    
    }
}
