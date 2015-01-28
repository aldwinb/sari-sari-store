package com.leetcode;

import java.util.*;
import java.util.Calendar;

/*
median: 16302.0
rtime = 9
*/
public class Solution4 {
    public double findMedianSortedArrays(int[] A, int[] B) {
        double med1 = median(A),
            med2 = median(B);

        if (A.length == 0) return med2;
        if (B.length == 0) return med1;
        if (med1 == med2) return med1;
        double minMed = Math.min(med1, med2),
               maxMed = Math.max(med1, med2);
        List<Integer> q = new ArrayList<Integer>();
        int i = 0,
            ao = 0,
            bo = 0,
            sz = 0;
        for (i = 0; i < A.length && A[i] <= maxMed; i++)
            if (A[i] >= minMed) q.add(A[i]);
        ao = Math.max(i-q.size(),0);
        if (i-q.size()-1 >= 0) {
            q.add(A[i-q.size()-1]);
            ao--;
        }
        if (i < A.length) q.add(A[i]);
        sz = q.size();
        
        for (i = 0; i < B.length && B[i] <= maxMed; i++)
            if (B[i] >= minMed) q.add(B[i]);
        bo = Math.max(i-(q.size()-sz),0);
        if (i-(q.size()-sz)-1 >= 0) {
            q.add(B[i-(q.size()-sz)-1]);
            bo--;
        }
        if (i < B.length) q.add(B[i]);

        Object[] s = q.toArray();
        Arrays.sort(s);

        for (int j = 0; j < s.length; j++)
            System.out.print(String.format(" %s", s[j]));
        System.out.println("");
        int N = A.length+B.length;
        int h = (N/2)-(ao+bo);
        System.out.println(String.format("N = %s, h = %s, ao = %s, bo = %s", N, h, ao, bo));
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
