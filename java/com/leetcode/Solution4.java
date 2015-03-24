package com.leetcode;

import java.util.*;
import java.util.Calendar;

/*
median: 16302.0
rtime = 9
*/
public class Solution4 {
    public double findMedianSortedArrays(int[] A, int[] B) {
        if (A.length == 0) return median(B);
        if (B.length == 0) return median(A);
        if (A.length == 1 && B.length == 1)
            return A[0] == B[0] ? A[0] : (A[0]+B[0])/2.0;
        
        int mid = (A.length+B.length)/2,
            medToSearch = A.length > B.length ? B[B.length/2] : A[A.length/2],
            j = bs(A.length > B.length ? A : B, medToSearch);

        // System.out.format("mid = %s, med = %s, j = %s\n", mid, medToSearch, j);
        return j;
    }

    private double median(int[] a) {
        if (a.length == 1) return a[0];
        int mid = a.length / 2;
        return a.length % 2 == 0 ? (a[mid-1]+a[mid])/2.0 : a[mid];
    }

    /* 

      2 2 2 3           9
    1         4 5 5 6 7
    loA = 2
    loB = 1
    private double findMedian(int[] A, int[] B, int loA, int loB, int mid) {
        int n = A.length+B.length;
        if (loA+loB < mid) {
            int next = bs(B, A[loA+1]);
            if (next+ < mid) return B[B.length
        } else if (loA+loB > mid) {
        
        } else {
            int nextLo = bs(B,A[loA-1]);
            if (nextLo+(loA-1) == mid-1) return A[loa]+A[loa-1] / 2.0;
            return A[loA]+B[loB-1] / 2.0;
        }
    }
    */

    private int bs(int[] B, int val) {
        return bs(B,0,B.length-1,val);
    }

    private int bs(int[] B, int lo, int hi, int val) {
        //System.out.format("lo = %s, hi = %s\n", lo, hi);
        if (lo == hi) 
            return B[lo] > val ? lo : lo+1;
        int mid = (hi+lo)/2;
        if (val < B[mid]) return bs(B,lo,mid,val);
        if (val > B[mid]) return bs(B,mid+1,hi,val);
        return mid;
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
