package com.leetcode;

import java.util.*;
import java.util.Calendar;

/*
median: 16302.0
rtime = 9
*/
public class Solution4 {
    public double findMedianSortedArrays(int[] A, int[] B) {
        if (A.length == 0) return med2;
        if (B.length == 0) return med1;
        if (A.length == 1 && B.length == 1)
            return A[0] == B[0] ? A[0] : (A[0]+B[0])/2.0;

        int med = A[A.length/2];
        int bIdx = searchMedian(B,med);
    
        1 2 3 3 3 4 4
        1 1 1 1 1 1 1 4 5
        return N % 2 == 0 ? ((int)s[h-1]+(int)s[h])/2.0 : (int)s[h];
    }

    private double median(int[] a) {
        if (a.length == 0) return 0;
        if (a.length == 1) return a[0];
        if (a.length == 2) return (a[0]+a[1])/2.0;
        int h = a.length / 2;
        return a.length % 2 == 0 ? (a[h-1]+a[h])/2.0 : a[h];
    }

    public static void main(String[] args) {
        String[] s1 = args[0].length() == 0 ? new String[0] : args[0].split(",");
        int[] A = new int[s1.length];
        for (int i = 0; i < s1.length; i++)
            A[i] = Integer.parseInt(s1[i]);
        
        s1 = args[1].length() == 0 ? new String[0] : args[1].split(",");
        int[] B = new int[s1.length];
        for (int i = 0; i < s1.length; i++)
            B[i] = Integer.parseInt(s1[i]);

        /* A = new int[10000000];
        B = new int[10000000];

        for (int i = 0; i < 10000000; i++) {
            A[i] = (i+7) / 91;
            B[i] = (i+991) / 39;
        }

        Arrays.sort(A);
        Arrays.sort(B); */

        long start = Calendar.getInstance().getTimeInMillis();
        System.out.println(String.format("median: %s", new Solution4().findMedianSortedArrays(A, B)));
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println(String.format("rtime = %s", end-start));
    }
}
