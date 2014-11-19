package com.tc;

public class DivisorInc {
  private Set<Integer> marked;
  private boolean pathFound;
  private int level;

  public DivisorInc() {
    marked = new HashSet<Integer>();
    level = 0;
  }

  public int countOperations(int N, int M) {
    dfs(N, M);
    return level;
  }

  private void dfs(int v, int N) {
    marked.add(v);
    int d = 2
      , half = v / 2;
      , q = 0;

    while (!pathFound) {
      while (v % d != 0 && d <= half)
        d++;
      if (d > half) break;
      q = v / d;
      if (!marked.contains(v+q))
        dfs(v+q);
    }

    if (!pathFound && n+q == N)
      pathFound = true;

    if (pathFound)
      level++;
  } 
}
