package com.tc;

public class BadNeighbors {
  private int[] max;
  private boolean[] maxed;
  private int N;

  public int maxDonations(int[] donations) {
    N = donations.length;
       
    max = new int[N];
    
    int c = 0, m = 0;
    if (N == 2) return Math.max(donations[0], donations[1]);
    for (int i = 0; i < N-2; i++) {
      for (int j = 0; j < N; j++)
        max[j] = -1;
      c = max(i, i, donations);
      //System.out.println(String.format("Outside m = %s, c = %s", m, c));
      if (m < c) m = c;
    }

    return m;
  }

  private int max(int s, int from, int[] seq) {
    System.out.println(String.format("s = %s, from = %s", s, from));
    if (from-s > N-2) return 0;
    int norm = from % N;
    if (max[norm] == -1) {
      int c = 0, m = 0; 
      for (int i = from+2; i < (s+N)-1; i++) {
        c = max(s, i, seq);
        if (m < c) m = c;
        System.out.println(String.format("i = %s, norm = %s, c = %s, m = %s", i, norm, c, m));
      }
      max[norm] = seq[norm] + m;
      System.out.println(String.format("norm = %s, max[norm] = %s", norm, max[norm]));
    }
    return max[norm];
  }
}
