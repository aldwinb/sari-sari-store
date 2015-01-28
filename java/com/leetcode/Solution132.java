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
        System.out.println("cost:");
        for (int i = 0; i < N; i++)
            System.out.print(cost[i]);
        System.out.println("");
        return cost[N-1];
        //cut(s, 0);
    }
    
    private void cut (String s) {
        if (s.length() > 0) cost[0] = 0;
        if (s.length() > 1) cost[1] = s.charAt(1) == s.charAt(0) ? 0 : 1;
        int k = 0, l = 0, ni = 0;
        for (int i = 2; i < s.length(); i = ni) {
            System.out.println(String.format("i = %s", i));
            cost[i] = cost[i-1]+1;
            for (int j = 1; j <= 2; j++) {
                int m = 0;
                for (k = i-j, l = i;
                    k >= 0 && l <= s.length()-1 && s.charAt(k) == s.charAt(l);
                    k--, l++) {
                    int c = k == 0 ? 0 : cost[k-1]+1;
                    System.out.println(String.format("k = %s, l = %s, cost[k-1] = %s, c = %s", k, l, k == 0?0:cost[k-1], c));
                    if (cost[l] > c) cost[l] = c;
                }
                if (l == i) ++l;
                ni = Math.max(ni, l);
            }
        }
    }

    /*
    private int match(String s, int i, int j) {
        return s.charAt(i) == s.charAt(j) ? 0 : 1;
    }

    private int cut(String s1, int i) {
        if (cost[i] != Integer.MAX_VALUE) return cost[i];

        if (s1.length() == 1) cost[i] = 0;
        else if (s1.length() == 2) { 
            cost[i] = isPal(s1) ? 0 : 1;
            cost[i+1] = 0;
        }
        else {
            cost[i] = cut(s1.substring(1, s1.length()), i+1)+1;
            //System.out.println(String.format("i = %s, cost[i] = %s", i, cost[i]));
            for (int j = 2; j < s1.length(); j++) {
                if (isPal(s1.substring(0, j)))
                    //int c = cut(s1.substring(j, s1.length()), i+j)
                    if (cost[i] > cost[i+j]+1) cost[i] = cost[i+j]+1;
                //System.out.println(String.format("i = %s, cost[i] = %s, j = %s, subs = %s", i, cost[i], j, s1.substring(0, j)));
                //}
            }
            if (isPal(s1)) cost[i] = 0;
        }

        return cost[i];
    }

    private boolean isPal(String s) {
        int i = 0,
            j = s.length()-1;
        if (s.length() == 1) return true;
        for (i = 0, j = s.length()-1; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }
    */

    public static void main(String[] args) {
        long start = Calendar.getInstance().getTimeInMillis();
        System.out.println(String.format("minCut = %s",
            new Solution132().minCut(args[0])));
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println(String.format("rtime = %s", end-start));
    }
}
