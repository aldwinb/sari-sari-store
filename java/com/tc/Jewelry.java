package com.tc;

import java.util.*;

public class Jewelry {
  private Hashtable<Integer, NavigableSet<Integer>> sums;
  private int[] max;
  private int[] values;

  public long howMany(int[] values) {
    this.values = values;
    sums = new Hashtable<Integer, NavigableSet<Integer>>();
    max = new int[values.length];
    Arrays.sort(this.values);
    Arrays.fill(max, -1);
    sums.put(0, null);
    return count(this.values.length-1);
  }

  private int count(int v) {
    if (v == 0) {
      NavigableSet<Integer> n = new TreeSet<Integer>();
      n.add(1);
      sums.put(values[v], n);
      max[v] = values[v];
      return 0;
    }

    int c = count(v-1);
    int right = values[v];
    for (int i = values.length-1; i >= 0; i--) {
      if (max[i] < right) break;
      if (sums.containsKey(right)) {
        NavigableSet<Integer> lows = sums.get(right);
        c += lows.size();
        for (Integer l : lows.descendingSet()) {
          if (l < right) break;
          if (l == right) c++;
        }
      }
      right += values[i];
    }

    int maxSum = 0;
    Set<Integer> keySet = (Set<Integer>)sums.keySet();
    for (int k : keySet) {
      int sum = values[k]+values[v];
      if (sum > maxSum) maxSum = sum;
      if (!sums.containsKey(sum)) sums.put(sum, 1);
      else {
        sums.get(sum)
        sums.remove(sum);
        sums.put(sum, val+1);
      }
    }

    max[v] = maxSum;
    return c;
  }
}
