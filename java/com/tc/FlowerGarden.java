package com.tc;

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
  private int N;

  public int[] getOrdering(int[] height, int[] bloom, int[] wilt) {
    N = height.length;
    order = new ArrayList<Integer>(N);
    flowers = new Flower[height.length];
    for (int i = 0; i < N; i++)
      flowers[i] = new Flower(height[i], bloom[i], wilt[i]);

    for (int i = N-1; i >= 0; i--) {
      sink(i);
      //order[i] = flowers[i].height;
    }

    for (int i = 0; i < N; i++)
      order[i] = flowers[i].height;
    return order;
  }

  private void sink(int f, int lo, int hi) {

    System.out.println(String.format("f = %s, f1 = %s f2 = %s",f,flowers[f], flowers[f+1]));
    // no overlaps
    if ((flowers[f].bloom < flowers[order.get(lo)].bloom 
        && flowers[f].wilt < flowers[order.get(lo)].wilt
        && flowers[f].wilt < flowers[order.get(lo)].bloom
        && flowers[f].height > flowers[order.get(lo)].height)
        || 
      ((flowers[f].wilt >= flowers[order.get(lo)].bloom
        || flowers[f].wilt >= flowers[order.get(lo)].wilt)
        && flowers[f].height < flowers[order.get(lo)].height)
      order.add(lo-1, f);
    else if ((flowers[f].bloom > flowers[order.get(hi)].bloom 
        && flowers[f].wilt > flowers[order.get(hi)].wilt
        && flowers[f].wilt > flowers[order.get(hi)].bloom
        && flowers[f].height <= flowers[order.get(hi)].height)
        || 
      ((flowers[f].wilt < flowers[order.get(hi)].bloom
        || flowers[f].wilt < flowers[order.get(hi)].wilt)
        && flowers[f].height >= flowers[order.get(hi)].height)
      order.add(hi+1, f);
    else
      sink(f, lo+1, hi-1);
    System.out.println(String.format("Sinking: f = %s, f1 = %s f2 = %s",f,flowers[f], flowers[f+1]));
    
    // Flower tmp = flowers[f+1];
    // flowers[f+1] = flowers[f];
    // flowers[f] = tmp;
    // sink(f+1);
  }
}
