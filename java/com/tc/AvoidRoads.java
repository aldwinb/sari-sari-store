package com.tc;

public class AvoidRoads {
  
  long[][] w;
  boolean[][] b;

  public long numWays(int width, int height, String[] bad) {
    int M = (width*2)+1
      ,N = (height*2)+1;
    w = new long[M][N];
    b = new boolean[M][N];

    for (int i = 0; i < M; i++)
      for (int j = 0; j < N; j++) {
        w[i][j] = -1;
        b[i][j] = false;
      }

    for (String s : bad) {
      String[] coords = s.split(" ");
      int x1 = (Integer.parseInt(coords[0])*2)
        ,y1 = (Integer.parseInt(coords[1])*2)
        ,x2 = (Integer.parseInt(coords[2])*2)
        ,y2 = (Integer.parseInt(coords[3])*2);
      
      if (y1 == y2)
        for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++)
          b[i][y1] = true;
      else
        for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++)
          b[x1][i] = true;
    }

    return countWays(M-1, N-1);
  }

  private long countWays(int x, int y) {
    if (x == 0 && y == 0) return 1;
    if (w[x][y] != -1) return w[x][y];
   
    long count = 0;
    if (x > 1 && !b[x-1][y]) count += countWays(x-2, y);
    if (y > 1 && !b[x][y-1]) count += countWays(x, y-2);
    w[x][y] = count;
    return count;
  }

}
