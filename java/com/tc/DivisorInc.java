package com.tc;

import java.util.*;

public class DivisorInc {
  private Set<Integer> marked;

  public DivisorInc() {
    marked = new HashSet<Integer>();
  }

  public int countOperations(int N, int M) {
    return dfs(N, M, 1);
  }

  private int dfs(int v, int N, int level) {
    marked.add(v);
    
    while (
    int d = ld(v);
    if (d == -1) return -1;
    
    int q = v / d;

    System.out.println(String.format("v = %s, d = %s, q = %s, N = %s, level = %s", v, d, q, N, level));
    if (q+v == N || d+v == N) {
      //System.out.println("Equals");
      return level;
    }

    if (v+d < N && N < q+v) {
      //System.out.println("Search");
      return search(v+d, q+v, v, N) ? level : -1;
    }

    //System.out.println("DFS");
    if (!marked.contains(q+v))
      return dfs(q+v, N, level+1);

    //return -1;
  }

  private int ld(int v) {
    if (v % 2 == 0) return 2;
    if (v % 3 == 0) return 3;
    for (int i = 5; i * i <= v; i += 6) {
      if (v % i == 0) return i;
      if (v % (i + 2) == 0) return i + 2;
    }
    return -1;
  }

  private boolean search(int lo, int hi, int v, int N) {
    int mid = (lo + hi) / 2;
    System.out.println(String.format("lo = %s, hi = %s, mid = %s, v = %s, N = %s", lo, hi, mid, v, N));
    if (N > mid) return search(mid+1, hi, v, N);
    if (N < mid) return search(lo, mid-1, v, N);
    return v % (N-v) == 0;
  }
}
