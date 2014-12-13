package com.tc;

public class ChessMetric {
  private class Metric {
    public int X;
    public int L;
    
    public Metric(int X, int L) {
      this.X = X;
      this.L = L;
    }
  }

  private Metric[][][] pathCount;
  private int[] start;
  private int[] end;

  public long howMany(int size, int[] start, int[] end, int numMoves) {
    pathCount = new Metric[size][size][numMoves];
    this.start = start;
    this.end = end;

    for (int i = 0; i < size; i++)
      for (int j = 0; j < size; j++)
        for (int k = 0; k < numMoves; k++)
          pathCount[i][j][k] = new Metric(-1, -1);


    return 0;
  }

  private long count(int x, int y, int rem) {
    if (rem == 0 && x == end
  }
}
