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

  private int[] order;
  private Flower[] flowers;
  private int N;

  public int[] getOrdering(int[] height, int[] bloom, int[] wilt) {
    N = height.length;
    order = new int[N];
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

  private void sink(int f) {
    if (f == N-1) return;

    System.out.println(String.format("f = %s, f1 = %s f2 = %s",f,flowers[f], flowers[f+1]));
    // no overlaps
    if (flowers[f].bloom < flowers[f+1].bloom 
        && flowers[f].wilt < flowers[f+1].wilt
        && flowers[f].wilt < flowers[f+1].bloom
        && flowers[f].height > flowers[f+1].height) return;
    
    // overlapping
    if (flowers[f].wilt >= flowers[f+1].bloom 
        && flowers[f].height < flowers[f+1].height) return;
    if (flowers[f].wilt >= flowers[f+1].wilt
        && flowers[f].height < flowers[f+1].height) return;
    
    System.out.println(String.format("Sinking: f = %s, f1 = %s f2 = %s",f,flowers[f], flowers[f+1]));
    
    Flower tmp = flowers[f+1];
    flowers[f+1] = flowers[f];
    flowers[f] = tmp;
    sink(f+1);
  }
}
