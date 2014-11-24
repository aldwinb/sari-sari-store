package com.tc;

public class BadNeighbors {
  private int[][] dist;
  private int N;

  public int maxDonations(int[] donations) {
    N = donations.length;
    dist = new int[N][N];
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        dist[i][j] = -1;

    int max = 0;
    if (N == 2) return Math.max(donations[0], donations[1]);
    for (int i = 0; i < N-(N/2); i++) {
      if (max < donations[i])
        max = donations[i];
      for (int j = i+2; j < N; j++) {
        int d = visit(i, i, j, donations);
        if (max < donations[i] + d)
          max = donations[i] + d;
      }
    }

    return max;
  }

  private int visit(int s, int from, int to, int[] seq) {
    if (to >= N) to %= N;
    System.out.println(String.format("s = %s, from = %s, to = %s", s, from ,to));
    if (to >= (s == 0 ? N-1 : s-1) || to == s) return 0;
    if (dist[from][to] == -1) {
      dist[from][to] = seq[to] + visit(s, to, to+2, seq);
      dist[to][from] = dist[from][to];
      System.out.println(String.format("from = %s, to = %s, dist[from][to] = %s, dist[to][from] = %s", from, to, dist[from][to], dist[to][from]));
    }
    return dist[from][to];
  }
}
