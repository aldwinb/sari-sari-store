package com.leetcode;

import java.util.*;
import java.util.Calendar;


public class Solution4 {
    private class SubArray {
        int start, end;
        public SubArray(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public double findMedianSortedArrays(int[] A, int[] B) {
        if (A.length == 0) return median(B);
        if (B.length == 0) return median(A);
        if (A[A.length-1] <= B[0])
            return median(A,B);
        if (B[B.length-1] <= A[0])
            return median(B,A);

        int mid = (A.length+B.length)/2,
            aInB = bs(B, A[A.length/2]),
            bInA = bs(A, B[B.length/2]);
     
        return 0;
    }

    // 2 2 2 3 9
    // 1 4 5 5 6 7
    // mid = 2, midOppo = 1
    // 3 < 5
    // s = 3, oppo = 2
    //
    // 3 9
    // 4 5 5 6 7
    // ptr = 3
    // mid = 4, midOppo = 6
    private void search(int[] A, 
        int B[], 
        SubArray subLess,
        SubArray subMore,
        int out, 
        int target) {
        int n = less.length+more.length;
        while (1) {
            int mid = (subLess.start+subLess.end)/2,
                midOppo = bs(B,subLess.start,subLess.end,A[mid]);
            if (mid+midOppo == target)
                return median(A[mid],Math.min(A[mid-1],B[midOppo-1]),n%2 == 0);
            if (mid+midOppo == target-1)
                return median(Math.min(A[mid+1],B[midOppo+1]),A[mid],n%2 == 0);
            if (midA+midOppo < target) {
                subLess.start = mid+1;
                subMore.start = midOppo+1;
            } else {
                subLess.end = mid-1;
                subMore.end = midOppo-1;
            }
        }
    }

    private double median(int[] a) {
        return median(new int[0], a);
    }

    private double median(int[] less, int[] more) {
        int n = less.length+more.length,
            mid = n/2;
        //System.out.format("mid = %s, less.length = %s\n", mid, less.length);
        if (mid <= less.length-1)
            return median(less[mid],
                less[mid-1],
                n%2 == 0);
        if (mid == less.length)
            return median(more[0], 
                less[less.length-1],
                n%2 == 0);
        return median(more[mid-less.length],
                more[mid-less.length-1],
                n%2 == 0);
    }

    private double median(int a, int b, boolean both) {
        System.out.format("a = %s, b = %s, both = %s\n", a, b, both);
        return !both ? a : (a+b)/2.0;
    }

    private int bs(int[] B, int val) {
        return bs(B,0,B.length-1,val);
    }

    private int bs(int[] B, int lo, int hi, int val) {
        while (hi > lo) {
            int mid = (hi+lo)/2;
            if (val < B[mid]) hi = mid;
            else if (val > B[mid]) lo = mid+1;
            else return mid;
        }
            
        return B[lo] > val ? lo : lo+1;
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

        //new Solution4().findMedianSortedArrays(A, B);
        /* A = new int[10000000];
        B = new int[10000000];

        for (int i = 0; i < 10000000; i++) {
            A[i] = (i+7) / 91;
            B[i] = (i+991) / 39;
        }

        Arrays.sort(A);
        Arrays.sort(B);

        */
        long start = Calendar.getInstance().getTimeInMillis();
        System.out.println(String.format("median: %s", 
            new Solution4().findMedianSortedArrays(A, B)));
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println(String.format("rtime = %s", end-start));
    }
}
