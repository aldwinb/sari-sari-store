package com.leetcode;

import java.util.*;

public class FindMedianSortedArrays {
  public double findMedianSortedArrays(int[] A, int[] B) {
    int N = A.length+B.length;
    int mid = (N/2);
    int m1 = 0, m2 = 0;
    int current = 0;
    int i = 0, j = 0, k = 0;

    while (i < A.length && j < B.length && k <= mid) {
      if (A[i] <= B[j]) current = A[i++];
      else current = B[j++];
      if (k == mid-1) m1 = current;
      else if (k == mid) m2 = current;
      k++;
    }

    //System.out.println(String.format("A.length = %s, mid = %s, k = %s, j = %s", A.length, mid, k, j));
    if (k <= mid-1) {
      if (i == A.length) j += mid-1-k;
      else i += mid-1-k;
      m1 = i == A.length ? B[j++] : A[i++];
      k = mid;
    }
    if (k == mid) m2 = i == A.length ? B[j] : A[i];

    //System.out.println(String.format("m1 = %s, m2 = %s", m1, m2));
    return N % 2 == 0 ? (m1+m2)/2.0 : m2;
  }

  public static void main(String[] args) {
    String[] s1 = args[0].split(",");
    int[] A = new int[s1.length];
    for (int i = 0; i < s1.length; i++)
      A[i] = Integer.parseInt(s1[i]);
    
    s1 = args[1].split(",");
    int[] B = new int[s1.length];
    for (int i = 0; i < s1.length; i++)
      B[i] = Integer.parseInt(s1[i]);

    System.out.println(String.format("median: %s", new FindMedianSortedArrays().findMedianSortedArrays(A, B)));
  }
}
