package com.tc;

import java.util.*;

public class DivisorInc {
  private Set<Integer> marked;

  public DivisorInc() {
    marked = new HashSet<Integer>();
    level = 0;
  }

  public int countOperations(int N, int M) {
    return dfs(N, M, 0);
  }

  private void dfs(int v, int N, int level) {
    marked.add(v);
    int d = ld(v);
    if (d == -1) return -1;
    
    int q = v / d;
    if (q+v == N || d+v == N)
      return level;
    
    if (v+d < N && N < q+d)
      return search(v+d, q+d, v, N);

    return dfs(q+v, N, level++);
  }

  private int ld(int v) {
    return 1;
  }

  private int search(int lo, int hi, int v, int N, int level) {
    int mid = (lo + hi) / 2;
    if (N > mid) search(mid+1, hi, v, N);
    if (N < mid) search(lo, m-1, v, N);
    return v % (N-v) == 0 ? level : -1;
  }
}
