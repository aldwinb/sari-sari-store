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

  private List<Integer> order;
  private Flower[] flowers;
  //private int N;

  public int[] getOrdering(int[] height, int[] bloom, int[] wilt) {
    int N = height.length;
    order = new ArrayList<Integer>(N);
    flowers = new Flower[height.length];
    for (int i = 0; i < N; i++)
      flowers[i] = new Flower(height[i], bloom[i], wilt[i]);

    order.add(0, 0);
    for (int i = 1; i < N; i++) {
      sink(i, 0, order.size()-1);
      //order[i] = flowers[i].height;
    }

    int[] orderArr = new int[N];
    for (int i = 0; i < N; i++)
      orderArr[i] = flowers[order.get(i)].height;
    return orderArr;
  }

  private void sink(int f, int lo, int hi) {
    if (lo > hi) { 
      order.add(lo, f);
      System.out.print("Order:");
      for (int c : order)
        System.out.print(String.format(" %s", c));
      System.out.println("");
      return;
    }
    //if (lo == f) order.add(lo, f);
    //else if (hi-lo == 1)order.add(lo+1, f);
    //if (flowers[f].bloom > flowers[lof].bloom && flowers[f].wilt < flowers[hof].wilt)
    //  sink (f, lo+1, hi-1);
    // no overlapsl
    
    int lof = order.get(lo), hif = order.get(hi);
    System.out.println(String.format("f = %s, lo = %s, hi = %s, f1 = %s f2 = %s f3 = %s",f, lo, hi,flowers[f], flowers[lof], flowers[hif]));
    if ((flowers[f].height > flowers[hif].height
          && (flowers[f].wilt < flowers[lof].bloom
            || flowers[f].bloom > flowers[hif].wilt))
        || flowers[f].height < flowers[lof].height
          && !(flowers[f].wilt < flowers[lof].bloom
            || flowers[f].bloom > flowers[hif].wilt))
      order.add(lo, f);
    // else if ((flowers[f].height < flowers[hif].height
    //       && (flowers[f].wilt < flowers[lof].bloom
    //         || flowers[f].bloom > flowers[hif].wilt))
    //     || flowers[f].height > flowers[hif].height)
    //   order.add(hi+1, f);
    else sink(f, lo+1, hi);
    //     || 
    //     (flowers[f].height < flowers[lof].height 
    //       && (flowers[f].bloom < flowers[lof].bloom 
    //         && flowers[f].wilt >= flowers[lof].bloom
    //         && flowers[f].wilt <= flowers[hif].wilt)
    //       || (flowers[f].bloom == flowers[lof].bloom
    //         && flowers[f].wilt == flowers[hif].wilt))
    //   order.add(lo, f);
    // else if (flowers[f].height < flowers[lof].height
    //     && flowers[f].bloom > flowers[lof].bloom
    //     && flowers[f].wilt == flowers[hif].wilt)
    //   order.add(lo, f);
    
    // else if ((flowers[f].height < flowers[hif].height 
    //     && flowers[f].bloom > flowers[hif].bloom 
    //     && flowers[f].wilt > flowers[hif].wilt
    //     && flowers[f].wilt > flowers[hif].bloom
    //       
    //       flowers[f].height > flowers[hif].height
    //     && flowers[f].wilt >= flowers[hif].wilt 
    //     && flowers[f].bloom >= flowers[hif].wilt)
    //     ||
    //   ()
    //   order.add(hi+1, f);
    //else if (flowers[f].height > flowers[order.get(hi)].height
    //    && flowers[f].bloom <
    //else
    //  sink(f, lo+1, hi-1);
    //System.out.println(String.format("Sinking: f = %s, f1 = %s f2 = %s",f,flowers[f], flowers[f+1]));
  }
}
