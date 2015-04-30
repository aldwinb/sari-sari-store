package com.hr;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ConnectedCells {
    private class Vertex {
        public int x, y;
        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private boolean[][] visited;
    public int maxRegion(int[][] G) {
        if (G.length == 0 || G[0].length == 0) return 0;
        if (G.length == 1 && G[0].length == 1) return G[0][0];
    
        visited = new boolean[G.length][G[0].length];
        for (int i = 0; i < G.length; i++)
            Arrays.fill(visited[i], false);
        
        int max = 0;
        for (int i = 0; i < G.length; i++) {
            for (int j = 0; j < G[0].length; j++) {
                if (visited[i][j]) continue;
                if (G[i][j] == 1)
                    max = Math.max(max, dfs(G, new Vertex(j, i))+1);
            }
        }
        return max;
    }
    
    private int dfs(int[][] G, Vertex v) {
        visited[v.y][v.x] = true;
        int count = 0;
        for (Vertex w : getAdj(v, G)) {
            if (visited[w.y][w.x]) continue;
            if (G[w.y][w.x] == 1)
                count += dfs(G, w)+1;
        }
        return count;
    }
    
    private Iterable<Vertex> getAdj(Vertex v, int[][] G) {
        int[] xc = new int[] { -1, 0, 1},
            yc = new int[] { -1, 0, 1 };
        
        List<Vertex> adj = new ArrayList<Vertex>();
        for (int yp : yc) {
            for (int xp : xc) {
                if (yp == 0 && xp == 0) continue;
                int nx = v.x+xp,
                    ny = v.y+yp;
                if (ny >= 0 && ny < G.length &&
                    nx >= 0 && nx < G[0].length)
                    adj.add(new Vertex(nx, ny));
            }
        }
        return adj;
    }

    public static void main(String[] args) {
        ConnectedCells cc = new ConnectedCells();
        int ctr = 1;
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String[] testCase = s.nextLine().split("\\t");
            String[] rows = testCase[0].split("\\|");
            int[][] G = new int[rows.length][rows[0].split(",").length];
            for (int i = 0; i < rows.length; i++) {
                String[] cols = rows[i].split(",");
                for (int j = 0; j < cols.length; j++)
                    G[i][j] = Integer.parseInt(cols[j]);
            }
            int actual = cc.maxRegion(G),
                expected = Integer.parseInt(testCase[1]);
            if (expected != actual) {
                printGrid(G);
                System.out.format("expected: %s\tactual: %s\n",
                    expected,
                    actual);
            }
        }
    }

    private static void printGrid(int[][] G) {
        for (int i = 0; i < G.length; i++) {
            for (int j = 0; j < G[i].length; j++)
                System.out.format("%s ", G[i][j]);
            System.out.println("");
        }
    }
}
