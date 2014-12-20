package com.tc;

import java.util.*;

public class Jewelry {
  private class Subseq {
    int count;
    Hashtable<Integer, Integer> mins;
    
    public Subseq(int count, int minsSize) {
      this.count = count;
      mins = new Hashtable<Integer, Integer>(minsSize);
      //Arrays.fill(mins, 0);
    }

    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(String.format("count = %s, mins = ", count));
      for (Integer k : ((Hashtable<Integer, Integer>)mins).keySet())
        sb.append(String.format(" {%s,%s}", k, mins.get(k)));
      return sb.toString();
    }
  }

  private Hashtable<Integer, Subseq> sums;
  private int[] max;
  private int[] values;

  public long howMany(int[] values) {
    this.values = values;
    sums = new Hashtable<Integer, Subseq>();
    max = new int[values.length];
    Arrays.sort(this.values);
    //Arrays.fill(max, -1);
    sums.put(0, new Subseq(0, 0));
    calcSums();

    // for (Integer k : sums.keySet()) {
    //   System.out.print(String.format("%s:", k));
    //   for (Integer v : sums.get(k))
    //     System.out.print(String.format(" %s", v));
    //   System.out.println("");
    // }
    return count();
    // return 0;
  }

  private void calcSums() {
    int maxSum = 0;
    for (int i = 0; i < values.length; i++) {
      Integer[] keySet = new Integer[sums.size()];
      keySet = sums.keySet().toArray(keySet);
      Arrays.sort(keySet);
      for (int j = keySet.length-1; j >= 0; j--) {
        int k = keySet[j];
        int sumKey = k+values[i];
        if (sumKey > maxSum) maxSum = sumKey;
        if (!sums.containsKey(sumKey)) sums.put(sumKey, new Subseq(0, values.length));
        Subseq seq = sums.get(sumKey);
        if (k == 0) { 
          if (!seq.mins.containsKey(values[i])) seq.mins.put(values[i], 1);
          else {
            int val = seq.mins.remove(values[i]);
            seq.mins.put(values[i], val+1);
          }
        } else { 
          seq.count++;
          Hashtable<Integer, Integer> mins = sums.get(k).mins;
          for (Integer k1 : mins.keySet()) {
            if (!seq.mins.containsKey(k1)) seq.mins.put(k1, mins.get(k1));
            else {
              int val = seq.mins.remove(k1);
              seq.mins.put(k1, val+mins.get(k1));
            }
          }
        }
      }
      max[i] = maxSum;
      for (Integer k : sums.keySet()) {
        System.out.println(String.format("%s: %s", k, sums.get(k)));
      }
      System.out.println("");
    }
  }

  private long count() {
    long c = 0;
    for (int i = 1; i < values.length; i++) {
      int right = values[i];
      for (int j = i; j >= 1 && max[j-1] >= right; j--) {
        if (sums.containsKey(right)) {
          Subseq seq = sums.get(right);
          c += seq.count;
          if (seq.mins.containsKey(right)) c += (seq.mins.get(right)-1);
          System.out.println(String.format("j = %s, values[j] = %s, right = %s, max[j-1] = %s, c = %s", j, values[j], right, max[j-1], c));
        }
        right += values[j-1];
      }
    }
    return c;
  }
}
