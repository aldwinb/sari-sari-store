package com.tc;

import java.util.*;

public class Jewelry {
  private Hashtable<Integer, Integer> sums;
  private int[] max;
  private int[] values;

  public long howMany(int[] values) {
    this.values = values;
    sums = new Hashtable<Integer, Integer>();
    max = new int[values.length];
    Arrays.sort(this.values);
    Arrays.fill(max, -1);
    return count(this.values.length-1);
  }

  private int count(int v) {
    if (v == 0) {
      sums.put(values[v], 1);
      max[v] = values[v];
      return 0;
    }

    int c = count(v-1);
    int right = values[v];
    for (int i = max.length-1; i >= 0; i--) {
      if (max[i] < right) break;
      if (sums.containsKey(right)) {
        c += sums.get(right);
        break;
      }
      right += max[i];
    }

    int maxSum = 0;
    Set<Integer> keySet = (Set<Integer>)sums.keySet();
    for (int k : keySet) {
      int sum = values[k]+values[v];
      if (sum > maxSum) maxSum = sum;
      if (!sums.containsKey(sum)) sums.put(sum, 1);
      else {
        int val = sums.remove(sum);
        sums.put(sum, val+1);
      }
    }

    max[v] = maxSum;
    return c;
  }
}
