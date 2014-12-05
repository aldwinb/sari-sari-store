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

  private class Block  {
    private int left;
    private int right;
    private int last;
    
    public Block(int left, int right, int last) {
      this.left = left;
      this.right = right;
      this.last = last;
    }
  }

  private Flower[] flowers;
  private List<Integer> order;
  private Dictionary<Integer,Block> parents;
  private int[] next;

  public int[] getOrdering(int[] height, int[] bloom, int[] wilt) {
    int N = height.length;
    flowers = new Flower[N];
    order = new ArrayList<Integer>(N);
    parents = new Hashtable<Integer,Block>();
    next = new int[N];

    for (int i = 0; i < N; i++) {
      flowers[i] = new Flower(height[i], bloom[i], wilt[i]);
      next[i] = -1;
      //max[i] = new Flower(-1, 1000, -1);
    }

    for (int i = 0; i < N; i++) {
        sink(i);
        //while (!reversePost.isEmpty()) {
        //  int o = reversePost.poll();
        //  System.out.println(String.format("Adding to order: %s", o));
        //  order.add(o);
        //}
     // }
      //sink(i, 0, order.size());
      //System.out.println("Maxes: ");
      //for (int j = 0; j < N; j++) {
        //if (max[j].height > -1) //System.out.println(String.format(" %s", max[j]));
      //}
      //System.out.println("");
    }

    int[] orderArr = new int[reversePost.size()];
    int i = 0;
    while (!reversePost.isEmpty()) {
      int o = reversePost.pop();
      //System.out.println(String.format("Adding to order: %s", o));
      //order.add(o);
      orderArr[i++] = flowers[o].height;
    }
    //for (int i = 0; i < N; i++)
    //  orderArr[i] = flowers[order.get(i)].height;
    return orderArr;
  }

  private void sink(int f) {
    Flower n = flowers[f];
    boolean foundInt = false;
    Block b1, b2;
    for (Enumeration<Integer> e = parents.keys(); e.hasMoreElements()) {
      int k = e.nextElement();
      b1 = parents.get(k);
      if (!(n.wilt < flowers[b1.left].bloom || n.bloom > flowers[b2.right].wilt)
        && n.height > flowers[k].height) {
        foundInt = true;
        if (k.hasMoreElements()) b2 = parents.get(k.nextElement());
        break;
      }
    }

    if (!foundInt) { 
      parents.add(f, new Block(f, f, f));
      return;
    }
    
    insert(f, b1);
    if (b2 != null) merge(b1, b2);
  }

  private void insert(int f, int b) {
    Flower c = flowers[f];
    int prev = -1
    for (int i = b; i != -1; i = next[i]) {
      if (c.height > flowers[i].height && (c.wilt < flowers[i].bloom || c.bloom > flowers[i].wilt))
        next[f] = i;
        if (prev != -1) next[prev] = f; 
        else {
          Block block = parents.remove(b);
          parents.add(f, block);
        }
        break;
      } else if (c.height < flowers[i].height && !(c.wilt < flowers[i].bloom || c.bloom > flowers[i].wilt)) {
        if (next[i] != -1) next[f] = next[next[i]];
        next[i] = f;
        break;
      }
      prev = i;
    }
    updateBlock(block, f, f, f);
  }

  private void merge(int tallIdx, int shortIdx) {
    Block taller = parents.get(tallIdx);
    Block shorter = parents.get(shortIdx);
    if (flowers[taller.right].wilt <= flowers[shorter.left].bloom) {
      next[shorter.last] = tallIdx;
      updateBlock(shorter, tallIdx.left, tallIdx.right, tallIdx.last);
      parents.remove(tallIdx);
    }
  }

  private void updateBlock(Block b, int bloomIdx, int wiltIdx, int lastIdx) {
    Flower f_bloom = flowers[bloomIdx];
    Flower f_wilt = flowers[wiltIdx];
    if (flowers[b.left].bloom > f_bloom.bloom) b.left = bloomIdx;
    if (flowers[b.right].wilt < f_wilt.wilt) b.right = wiltIdx;
    b.last = lastIdx;
  }
}
