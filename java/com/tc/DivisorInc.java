package com.tc;

import java.util.*;

public class DivisorInc {
  private Set<Integer> marked;

  public DivisorInc() {
    marked = new HashSet<Integer>();
  }

  public int countOperations(int N, int M) {
    if (N == M) return 0;
    return dfs(N, M, 1);
  }

  private int dfs(int v, int N, int level) {
    marked.add(v);
    int returnLevel = -1;

    int d = 0;
    while (returnLevel == -1 && d != -1) {
      d = ld(v, d);
      if (d == -1) break;
      
      int q = v / d;
      System.out.println(String.format("v = %s, d = %s, q = %s, N = %s, level = %s", v, d, q, N, level));
      if (q+v == N || d+v == N) {
        //System.out.println("Equals");
        returnLevel = level;
      } else if (v+d < N && N < q+v) {
        //System.out.println("Search");
        returnLevel = search(v+d, q+v, v, N) ? level : -1;
      } else if (!marked.contains(q+v)) {
        returnLevel = dfs(q+v, N, level+1);
      } else
        continue;
    }

    return returnLevel;  
  }

  private int ld(int v, int d) {
    if (d < 2 && v % 2 == 0) return 2;
    if (d < 3 && v % 3 == 0) return 3;
    for (int i = 5; i * i <= v; i += 6) {
      if (d < i && v % i == 0) return i;
      if (d < (i + 2) && v % (i + 2) == 0) return i + 2;
    }
    return -1;
  }

  private boolean search(int lo, int hi, int v, int N) {
    int mid = (lo + hi) / 2;
    //System.out.println(String.format("lo = %s, hi = %s, mid = %s, v = %s, N = %s", lo, hi, mid, v, N));
    if (N > mid) return search(mid+1, hi, v, N);
    if (N < mid) return search(lo, mid-1, v, N);
    return v % (N-v) == 0;
  }
}
