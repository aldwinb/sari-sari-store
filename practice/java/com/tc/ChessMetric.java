package com.tc;

public class ChessMetric {
  private long[][][] pathCount;
  private int[] end;
  private int N;

  public long howMany(int size, int[] start, int[] end, int numMoves) {
    pathCount = new long[size][size][numMoves+1];
    this.end = end;
    N = size;

    for (int i = 0; i < size; i++)
      for (int j = 0; j < size; j++)
        for (int k = 1; k < numMoves+1; k++)
          pathCount[i][j][k] = -1;

    return count(start[0], start[1], numMoves);
  }

  private long count(int x, int y, int rem) {
    if (rem == 0) return x == end[0] && y == end[1] ? 1 : 0;
    if (pathCount[x][y][rem] != -1) return pathCount[x][y][rem];

    long cX = 0;
    // X visits
    if (x > 0) {
      cX += count(x-1, y, rem-1);
        if (y > 0) cX += count(x-1, y-1, rem-1);
        if (y < N-1) cX += count(x-1, y+1, rem-1);
    }
    if (y < N-1) cX += count(x, y+1, rem-1);
    if (x < N-1) {
      cX += count(x+1, y, rem-1);
        if (y > 0) cX += count(x+1, y-1, rem-1);
        if (y < N-1) cX += count(x+1, y+1, rem-1);
    }
    if (y > 0) cX += count(x, y-1, rem-1);
    
    long cL = 0;
    if (x > 1) {
      if (y > 0) cL += count(x-2, y-1, rem-1);
      if (y < N-1) cL += count(x-2, y+1, rem-1);
    }
    if (x > 0) { 
      if (y > 1) cL += count(x-1, y-2, rem-1);
      if (y < N-2) cL += count(x-1, y+2, rem-1);
    }
    if (x < N-2) {
      if (y > 0) cL += count(x+2, y-1, rem-1);
      if (y < N-1) cL += count(x+2, y+1, rem-1);
    }
    if (x < N-1) { 
      if (y > 1) cL += count(x+1, y-2, rem-1); 
      if (y < N-2) cL += count(x+1, y+2, rem-1);
    }

    pathCount[x][y][rem] = cX + cL;

    return cX + cL;
  }

}
