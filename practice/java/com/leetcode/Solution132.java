package com.leetcode;

import java.util.*;
import java.util.Calendar;

public class Solution132 {
    int[] cost;
    public int minCut(String s) {
        if (s == null || s.length() < 2) return 0;
        int N = s.length();
        cost = new int[N]; 
        for (int i = 0; i < N; i++)
            cost[i] = Integer.MAX_VALUE;
        cut(s);
        return cost[N-1];
    }
    
    private void cut (String s) {
        if (s.length() > 0) cost[0] = 0;
        if (s.length() > 1) cost[1] = s.charAt(1) == s.charAt(0) ? 0 : 1;
        int k = 0, l = 0, ni = 0;
        for (int i = 2; i < s.length(); i++) {
            if (cost[i] == Integer.MAX_VALUE) cost[i] = cost[i-1]+1;
            for (int j = 1; j <= 2; j++) {
                for (k = i-j, l = i;
                    k >= 0 && l <= s.length()-1 && s.charAt(k) == s.charAt(l);
                    k--, l++) {
                    int c = k == 0 ? 0 : cost[k-1]+1;
                    if (cost[l] > c) cost[l] = c;
                }
            }
        }
    }

    public static void main(String[] args) {
        long start = Calendar.getInstance().getTimeInMillis();
        System.out.println(String.format("minCut = %s",
            new Solution132().minCut(args[0])));
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println(String.format("rtime = %s", end-start));
    }
}
