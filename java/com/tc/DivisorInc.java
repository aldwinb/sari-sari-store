package com.tc;

import java.util.*;

public class DivisorInc {
  private Set<Integer> marked;
  private boolean pathFound;
  private int level;

  public DivisorInc() {
    marked = new HashSet<Integer>();
    level = 0;
  }

  public int countOperations(int N, int M) {
    if (N == M) return 0;
    dfs(N, M);
    return level;
    //return pathFound ? level : -1;
  }

  private void dfs(int v, int N) {
    marked.add(v);
    int d = 2, 
        half = (v / 2),
        q = 0;

    while (!pathFound && d <= half) {
      if (v % d == 0) {
        System.out.println(String.format("v = %s, d = %s, level = %s", v, d, level));
        q = v / d;
        if (v+q > N) break;
        else if (v+q == N) pathFound = true;
        else if (!marked.contains(v+q))
          dfs(v+q, N);
      }
      d++;
    }

    //if (!pathFound && v+q == N)
    //  pathFound = true;

    if (pathFound)
      level++;
  }
}
