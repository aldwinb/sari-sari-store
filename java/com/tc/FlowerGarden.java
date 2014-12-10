package com.tc;

import java.util.*;

public class FlowerGarden {

  private class Flower implements Comparable<Flower> {
    private int height;
    private int bloom;
    private int wilt;

    public int indeg;
    public List<Integer> adj;

    public Flower(int height, int bloom, int wilt) {
      this.height = height;
      this.bloom = bloom;
      this.wilt = wilt;
      adj = new ArrayList<Integer>();
      indeg = 0;
    }

    public int compareTo(Flower f) {
      if (indeg < f.indeg) return -1;
      if (indeg == f.indeg) return height > f.height ? -1 : 1;
      return 1;
    }
  }

  private Flower[] flowers;

  public int[] getOrdering(int[] height, int[] bloom, int[] wilt) {
    int N = height.length;
    flowers = new Flower[N];

    for (int i = 0; i < N; i++) {
      flowers[i] = new Flower(height[i], bloom[i], wilt[i]);
    }

    for (int i = 0; i < N; i++) {
      for (int j = i; j < N; j++) {
        if (!free(flowers[i], flowers[j])) {
          boolean iLess = flowers[i].height < flowers[j].height;
          flowers[iLess ? i : j].adj.add(iLess ? j : i);
          flowers[iLess ? j : i].indeg++;
        }
      }
    }

    PriorityQueue<Flower> pq = new PriorityQueue<Flower>();
    for (int i = 0; i < N; i++)
      pq.add(flowers[i]);
    
    int[] order = new int[N];
    int i = 0;
    while (!pq.isEmpty()) {
      Flower f = pq.poll();
      order[i++] = f.height;
      for (Integer v : f.adj) {
        if (pq.remove(flowers[v])) {
          flowers[v].indeg--;
          pq.add(flowers[v]);
        }
      }
    }

    return order;
  }
  
  private boolean free(Flower m, Flower n) {
    return m.wilt < n.bloom || m.bloom > n.wilt;
  }
}
