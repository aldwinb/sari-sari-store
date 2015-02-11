package com.leetcode;

public class Solution76 {
    public String minWindow(String S, String T) {
    
    }

    private int hash(String p, int Q) {
        int h = p.charAt(0) % Q;
        for (int i = 1; i < p.length(); i++)
            h = (h * 10) + (p.charAt(i) % Q);
        return h;
    }
}
