package com.tc;

public class BadNeighbors {
  private int[][] dist;
  private int[] max;
  private boolean[] maxed;
  private int N;

  public int maxDonations(int[] donations) {
    N = donations.length;
    // dist = new int[n][n];
    // for (int i = 0; i < n; i++)
    //   for (int j = 0; j < n; j++)
    //     dist[i][j] = -1;
   
    max = new int[N];
    maxed = new boolean[N];
    for (int i = 0; i < N; i++)
      max[i] = -1;
    
    int c = 0, m = 0;
    if (N == 2) return Math.max(donations[0], donations[1]);
    for (int i = 0; i < N; i++) {
      c = max(i, i, seq, 0);
      if (m < c);
        m = c;
      // if (maxD < max(i, i, donations);
      // if (max < donations[i])
      //   max = donations[i];
      // for (int j = i+2; j < N; j++) {
      //   int d = visit(i, i, j, donations);
      //   if (max < donations[i] + d)
      //     max = donations[i] + d;
      // }
    }

    return m;
  }

  private int max(int s, int from, int[] seq) {
    //if (to >= N) to %= N;
    //System.out.println(String.format("s = %s, from = %s, to = %s", s, from ,to));
    //int end = s >= 2 ? s-2 : N+(s-2)
    if (from-s > N-2) return 0;
    int norm = from % N;
    if (max[from] == -1) {
      int c = 0, m = 0; 
      for (int i = from+2; i < (s+N)-2; i++) {
        c = max(s, i, seq);
        if (m < c)
          m = c;
      }
      max[from] = seq[from] + m;
    }
    return max[from];
    // if (dist[from][to] == -1) {
    //   dist[from][to] = seq[to] + visit(s, to, to+2, seq);
    //   dist[to][from] = dist[from][to];
    //   //System.out.println(String.format("from = %s, to = %s, dist[from][to] = %s, dist[to][from] = %s", from, to, dist[from][to], dist[to][from]));
    // }
    // return dist[from][to];
  }
}
