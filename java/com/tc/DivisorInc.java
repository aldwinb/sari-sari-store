package com.tc;

import java.util.*;

public class DivisorInc {
    public int countOperations(int N, int M) {
        if (N == M) return 0;
        int lvl = 0;
        boolean found = false;
        
        boolean[] marked = new boolean[100000];
        for (int i = 0; i < 100000; i++)
            marked[i] = false;

        Queue<Integer> q = new ArrayDeque<Integer>();
        q.add(N);
        while (!q.isEmpty() && !found) {
            int v = q.poll();
            List<Integer> adj = new ArrayList<Integer>();
            for (int i = 2; i <= v/2; i++) {
                if (v % i == 0) 
                    adj.add(v+(v/i));
            }
            for (int w : adj) {
                //System.out.println(String.format("v = %s, adj = %s", v, w));
                if (w == M) {
                    found = true;
                    break;
                }
                if (!marked[w]) {
                    q.add(w);
                    marked[w] = true;
                }
            }
            if (adj.size() > 0) lvl++;
        }
        return lvl;
    }

    public static void main(String[] args) {
        System.out.println(String.format("count = %s",
            new DivisorInc().countOperations(
                Integer.parseInt(args[0]),
                Integer.parseInt(args[1]))));
    }

    /*
  private int dfs(int v, int N, int level) {
    marked.add(v);
    int returnLevel = -1;

    int d = ld(v);
    if (d == -1) return -1;

    int hid = v / d;
    while (returnLevel == -1 && d != -1) {
      int q = v / d;      
      System.out.println(String.format("v = %s, d = %s, q = %s, N = %s, level = %s", v, d, q, N, level));
      if (q+v == N || d+v == N) {
        //System.out.println("Equals");
        return level;
      } else if (v+d < N && N < q+v) {
        //System.out.println("Search");
        return search(v+d, q+v, v, N) ? level : -1;
      } else if (N > q+v) {
          if (!marked.contains(q+v))
            returnLevel = dfs(q+v, N, level+1);
      }

      if (returnLevel == -1)
        d = nextd(v, d+1, hid);
    }

    return returnLevel;  
  }

    private int ld(int v) {
        if (v % 2 == 0) return 2;
        if (v % 3 == 0) return 3;
        for (int i = 5; i * i <= v; i += 6) {
            if (v % i == 0) return i;
            if (v % (i + 2) == 0) return i + 2;
        }
        return -1;
    }

  private int nextd(int v, int d, int hid) {
    while (v % d != 0 && d <= hid) {
      d++;
    }
    return d > hid ? -1 : d;
  }

  private boolean search(int lo, int hi, int v, int N) {
    int mid = (lo + hi) / 2;
    if (N > mid) return search(mid+1, hi, v, N);
    if (N < mid) return search(lo, mid-1, v, N);
    return v % (N-v) == 0;
  }
    */
}
