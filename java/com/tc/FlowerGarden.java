package com.tc;

import java.util.*;

public class FlowerGarden {

  private class Flower {
    private int height;
    private int bloom;
    private int wilt;
    public Flower(int height, int bloom, int wilt) {
      this.height = height;
      this.bloom = bloom;
      this.wilt = wilt;
    }

    public String toString() {
      return String.format("%s,%s,%s", height, bloom, wilt);
    }
  }

  private class Block {
    private int left;
    private int right;
    private int longest;
    private List<Integer> order;

    public Block() {
      left = -1;
      right = -1;
      longest = -1;
      order = new ArrayList<Integer>();
    }
  }

  private Flower[] flowers;
  //private Flower[] max;
  //private Flower[] left;
  //private Flower[] right;
  //private Flower cmax;
  private List<Integer> order;
  //private Queue<Block> blocks;
  private boolean[] marked;
  private ArrayDeque<Integer> topo;

  public int[] getOrdering(int[] height, int[] bloom, int[] wilt) {
    int N = height.length;
    flowers = new Flower[N];
    //max = new Flower[N];
    order = new ArrayList<Integer>(N);
    //cmax = new Flower(-1, 1000, -1);
    //blocks = new ArrayDeque<Block>();
    marked = new boolean[N];
    topo = new ArrayDeque<Integer>();

    for (int i = 0; i < N; i++) {
      flowers[i] = new Flower(height[i], bloom[i], wilt[i]);
      //max[i] = new Flower(-1, 1000, -1);
    }

    for (int i = 0; i < N; i++) {
      if (!marked[i]) {   
        dfs(flowers, i, N);
        //while (!topo.isEmpty()) {
        //  int o = topo.poll();
        //  System.out.println(String.format("Adding to order: %s", o));
        //  order.add(o);
        //}
      }
      //sink(i, 0, order.size());
      //System.out.println("Maxes: ");
      //for (int j = 0; j < N; j++) {
        //if (max[j].height > -1) //System.out.println(String.format(" %s", max[j]));
      //}
      //System.out.println("");
    }

    int[] orderArr = new int[topo.size()];
    int i = 0;
    while (!topo.isEmpty()) {
      int o = topo.pop();
      //System.out.println(String.format("Adding to order: %s", o));
      //order.add(o);
      orderArr[i++] = flowers[o].height;
    }
    //for (int i = 0; i < N; i++)
    //  orderArr[i] = flowers[order.get(i)].height;
    return orderArr;
  }

  // private void sink(int f, int lo, int N) {
  //   Flower n = flowers[f];

  //   if (lo == N) { 
  //     order.add(lo, f);
  //     setMax(max[f], n, n);
  //     //System.out.println(String.format("Max (reached N): %s", max[f]));
  //     return;
  //   }

  //   Flower m = max[order.get(lo)];
  //   Flower first = flowers[order.get(lo)];
  //   //System.out.println(String.format("f = %s, lo = %s, n = %s m = %s",f, lo, n, m));
  //   if ((n.height > m.height && (n.wilt < m.bloom || n.bloom > m.wilt))
  //       || (n.height < first.height && !(n.wilt < first.bloom || n.bloom > first.wilt))) {
  //     order.add(lo, f);
  //     setMax(max[f], n, m);
  //     return;
  //   }
  //  
  //   //System.out.println("Sinking");
  //   setMax(m, n, m);
  //   sink(f, lo+1, N);
  // }

  // private void setMax(Flower c, Flower n, Flower m) {
  //   c.height = Math.max(n.height, m.height);
  //   c.bloom = Math.min(n.bloom, m.bloom);
  //   c.wilt = Math.max(n.wilt, m.wilt);
  // }

  private void dfs(Flower[] fl, int s, int N) {
    marked[s] = true;
    for (int v = 0; v < N; v++) {
      if (marked[v]) continue;
      if (s == v) continue;
      if (fl[s].height < fl[v].height && overlaps(fl, s, v)) {
        //System.out.println(String.format("DFS: %s", v));
        dfs(fl, v, N);
      }
    }
    //System.out.println(String.format("Pushing to stack: %s", s));
    topo.push(s);
  }

  private boolean overlaps(Flower[] fl, int s, int v) {
    return !(fl[s].wilt < fl[v].bloom || fl[s].bloom > fl[v].wilt);
  }
}
