package com.leetcode;

import java.util.*;

public class TwoSum {
  private class IndexedNum implements Comparable<IndexedNum> {
    public int num;
    public int index;
    
    public IndexedNum(int index, int num) {
      this.index = index;
      this.num = num;
    }

    public int compareTo(IndexedNum in) {
      if (num > in.num) return 1;
      if (num == in.num) return index > in.index ? 1 : -1;
      return -1;
    }
  }

  public int[] twoSum(int[] numbers, int target) {
    int[] s = new int[2];
    int N = numbers.length;
    Set<Integer> visited = new HashSet<Integer>();
    Queue<IndexedNum> numPq = new PriorityQueue<IndexedNum>();
    for (int i = 0; i < N; i++)
      numPq.add(new IndexedNum(i, numbers[i]));

    IndexedNum[] numArr = new IndexedNum[numPq.size()];
    int k = 0;
    while (!numPq.isEmpty())
      numArr[k++] = numPq.poll();
    
    int i = 0, j = -1;
    for (i = 0; i < numArr.length-1 && j == -1; i++) {
      if (numArr[i].num+numArr[numArr.length-1].num < target) continue;
      if (visited.contains(numArr[i].num)) continue;
      j = bs(numArr, i+1, numArr.length-1, target-numArr[i].num);
      visited.add(numArr[i].num);
    }

    s[0] = Math.min(numArr[i-1].index, numArr[j].index)+1;
    s[1] = Math.max(numArr[i-1].index, numArr[j].index)+1;
    return s;
  }

  private int bs(IndexedNum[] a, int lo, int hi, int v) {
    if (lo == hi) return a[lo].num == v ? lo : -1;
    int mid = (lo+hi)/2; 
    if (v < a[mid].num) return bs(a, lo, mid, v);
    if (v > a[mid].num) return bs(a, mid+1, hi, v);
    return mid;
  }
  
  public static void main(String[] args) {
    String[] s1 = args[0].split(",");
    int[] numbers = new int[s1.length];
    for (int i = 0; i < s1.length; i++)
      numbers[i] = Integer.parseInt(s1[i]);
    int target = Integer.parseInt(args[1]);
  
    int[] twoSum = new TwoSum().twoSum(numbers, target);
    System.out.println(String.format("twoSum: %s %s", twoSum[0], twoSum[1]));
  }
}
