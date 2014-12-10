package com.tc;

import java.util.*;

public class FlowerGarden {

  private class Flower {
    private int height;
    private int bloom;
    private int wilt;

    public int outdeg;

    public Flower(int height, int bloom, int wilt) {
      this.height = height;
      this.bloom = bloom;
      this.wilt = wilt;
    }

    public String toString() {
      return String.format("%s,%s,%s", height, bloom, wilt);
    }
  }

  private Flower[] flowers;
  private int[][] marked;
  private int[] outdeg;

  public int[] getOrdering(int[] height, int[] bloom, int[] wilt) {
    int N = height.length;
    flowers = new Flower[N];
    marked = new int[N][N];
    outdeg = new int[N];

    for (int i = 0; i < N; i++) {
      flowers[i] = new Flower(height[i], bloom[i], wilt[i]);
    }

    for (int i = 0; i < N; i++) {
      for (int j = i; j < N; j++) {
        if (!free(flowers[i], flowers[j]))
          if (
      }
    }

    for (int i = 0; i < N; i++) {
      sink(i);
    }

    int[] order = new int[N];
    int i = 0;
    Queue<Block> parentsPq = new PriorityQueue<Block>(parents);
    while (!parentsPq.isEmpty()) {
      Block b  = parentsPq.poll();
      for (int j = b.first; j != -1; j = next[j]) {
        //System.out.println(String.format("next = %s", j));
        order[i++] = flowers[j].height;
      }
    }
    return order;
  }
  
  private boolean free(Flower m, Flower n) {
    return m.wilt < n.bloom || m.bloom > n.wilt;
  }
