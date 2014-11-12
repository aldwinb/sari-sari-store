package com.dst;

import java.util.*;

public class Maze {
  private final int NORTH = 0;
  private final int EAST = 1;
  private final int SOUTH = 2;
  private final int WEST = 3;
  private final int CELL_DIST = 2;

  private int[][] maze;
  private boolean[][] visited;
  private boolean[][] marked;
  private Dictionary <Integer, Set<Integer>> wallST;
  private int N;

  public Maze(int N) {
    this.N = N+CELL_DIST;
    maze = new int[this.N][this.N];
    visited = new boolean[this.N][this.N];
    marked = new boolean[this.N][this.N];
    wallST = new Hashtable<Integer, Set<Integer>>();

    int k = 0;
    for (int i = 0; i < this.N; i++) {
      for (int j = 0; j < this.N; j++) {
        maze[i][j] = k;
        wallST.put(k++, new HashSet<Integer>());
      }
    }
    dfs(0, 0);
  }

  public boolean[][] maze() {
    return marked;
  }

  public int size() {
    return N;
  }

  private void dfs(int x, int y) {
    visited[x][y] = true; 
    Set<Integer> crumbled = wallST.get(maze[x][y]);
    
    if (y+CELL_DIST >= N) crumbled.add(NORTH);
    if (y-CELL_DIST < 0) crumbled.add(SOUTH);
    if (x+CELL_DIST >= N) crumbled.add(EAST);
    if (x-CELL_DIST < 0) crumbled.add(WEST);

    //System.out.println(String.format("x-CELL_DIST = %s, y = %s", x-CELL_DIST, y));
    while (crumbled.size() < 4) {     
      int r = new Random().nextInt(3);
      while (crumbled.contains(r % 4))
        r += 3;

      int nextWall = r % 4;
      if (nextWall == NORTH && visited[x][y+CELL_DIST]) crumbled.add(NORTH);
      else if (nextWall == SOUTH && visited[x][y-CELL_DIST]) crumbled.add(SOUTH);
      else if (nextWall == EAST && visited[x+CELL_DIST][y]) crumbled.add(EAST);
      else if (nextWall == WEST && visited[x-CELL_DIST][y]) crumbled.add(WEST);

      if (crumbled.size() == 4) break;

      System.out.println(String.format("x = %s, y = %s, nextWall = %s", x, y, nextWall));
      for (int i : crumbled)
        System.out.print(String.format("%s ", i));
      System.out.println("");
      
      int x1 = x
        , x2 = x
        , y1 = y
        , y2 = y;
      if (nextWall == NORTH) { 
        y2 += CELL_DIST;
        y1++;
      } else if (nextWall == SOUTH) { 
        y2 -= CELL_DIST;
        y1--;
      } else if (nextWall == EAST) { 
        x2 += CELL_DIST;
        x1++;
      } else {
        x2 -= CELL_DIST;
        x1--;
      }
     
      crumbled.add(nextWall);
      marked[x1][y1] = true;
      wallST.get(maze[x2][y2]).add((nextWall+CELL_DIST) % 4);
      dfs(x2, y2);

      System.out.println(String.format("After DFS, crumbled.size() = %s", crumbled.size()));
    }

    marked[x][y] = true;
  }

  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    Maze m = new Maze(N);
    boolean[][] maze = m.maze();
    for (int i = m.size()-1; i >= 0; i--) {
      for (int j = 0; j < m.size(); j++) {
        System.out.print(String.format("%s ", maze[i][j] ? "o" : "."));   
      }
      System.out.println("");
    }
  }
}
