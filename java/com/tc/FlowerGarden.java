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

  private class Block implements Comparable<Block> {
    private int first;
    private int left;
    private int right;
    private int last;
    private int maxHeight;
    
    public Block(int first, int left, int right, int last, int maxHeight) {
      this.first = first;
      this.left = left;
      this.right = right;
      this.last = last;
      this.maxHeight = maxHeight;
    }
   
    public int compareTo(Block b) {
      if (maxHeight > b.maxHeight) return -1;
      if (maxHeight == b.maxHeight) return 0;
      return 1;
    }

    public String toString() {
      return String.format("%s,%s,%s,%s,%s", first, left, right, last, maxHeight);
    }

  }

  private Flower[] flowers;
  private List<Block> parents;
  private int[] next;

  public int[] getOrdering(int[] height, int[] bloom, int[] wilt) {
    int N = height.length;
    flowers = new Flower[N];
    parents = new ArrayList<Block>();
    next = new int[N];

    for (int i = 0; i < N; i++) {
      flowers[i] = new Flower(height[i], bloom[i], wilt[i]);
      next[i] = -1;
    }

    for (int i = 0; i < N; i++) {
      sink(i);
    }

    int[] order = new int[N];
    int i = 0;
    Queue<Block> orderedParents = new PriorityQueue<Block>(parents);
    for (Block b : orderedParents) {
      for (int j = b.first; j != -1; j = next[j]) {
        //System.out.println(String.format("next = %s", j));
        order[i++] = flowers[j].height;
      }
    }
    return order;
  }

  private void sink(int f) {
    Flower n = flowers[f];
    Queue<Integer> ints = findInt(f);
    int b = ints.poll();
    //System.out.println(String.format("Selected block: %s", parents.get(b)));
    if (parents.get(b).first != f)
      insert(f, b);
    if (ints.size() == 1) 
        merge(ints.poll(), b);
  }

  private Queue<Integer> findInt(int f) {
    Flower n = flowers[f];
    Block b1, b2;
    int i = 0;
    int N = parents.size();
    Queue<Integer> ints = new ArrayDeque<Integer>();

    if (N == 0) {
      parents.add(0, new Block(f, f, f, f, n.height));
      ints.add(0);
      return ints;
    }

    for (i = 0; i < N; i++) {
      b1 = parents.get(i);
     
      //System.out.println(String.format("Block: %s", b1));
      if (i+1 == N
        && !free(n, flowers[b1.left].bloom, flowers[b1.right].wilt)) {
        ints.add(i);
        break;
      }

      if (i+1 < N) {
        b2 = parents.get(i+1);

        if (!free(n, flowers[b1.left].bloom, flowers[b1.right].wilt)) {
          if (n.wilt > flowers[b1.right].wilt 
            && !(n.wilt < flowers[b2.left].bloom)) {
            ints.add(i+1);
            ints.add(i);
          } else { 
            ints.add(i);
            if (i > 0) ints.add(i-1);
          }
          break;
        }

        if (n.bloom > flowers[b1.right].wilt
          && n.wilt < flowers[b2.left].bloom) {
          parents.add(i+1, new Block(f, f, f, f, n.height));
          ints.add(i+1);
          break;
        }
      }
    }

    if (i == N) {
      parents.add(i, new Block(f, f, f, f, n.height));
      ints.add(i);
      ints.add(i-1);
    }

    //System.out.print("Intervals:");
    //for (Integer j : ints)
    //  System.out.print(String.format(" %s", j));
    //System.out.println("");
    return ints;
  }

  private void insert(int f, int b) {
    Flower c = flowers[f];
    Block block = parents.get(b);
    int prev = -1;
    int parent = -1;
    int i = -1;
    int first = block.first;
    int last = block.last;

    for (i = first; i != -1; i = next[i]) {
      System.out.println(String.format("flowers[i] = %s, c = %s", flowers[i], c));
      if (c.height > flowers[i].height && free(c, flowers[i]) 
          && prev != -1
          && ((c.height > flowers[prev].height && !free(c, flowers[prev]))
            || (c.height < flowers[prev].height && free(c, flowers[prev])))) parent = prev;
      
      //System.out.println(String.format("i = %s", i));
      if (c.height < flowers[i].height && !free(c, flowers[i])) {
        //System.out.println(String.format("Inserting 1: c = %s, prev = %s, parent = %s, f = %s, i = %s, block = %s", c, prev, parent, f, i, block));
        //next[f] = i;
        //if (prev != -1) next[prev] = f; 
        //else first = f;
        break;
      }

      // if (c.height < flowers[i].height 
      //   && !(c.wilt < flowers[i].bloom || c.bloom > flowers[i].wilt)) {
      //   System.out.println(String.format("Inserting 2: c = %s, prev = %s, f = %s, i = %s", c, prev, f, i));
      //   if (next[i] != -1) next[f] = next[next[i]];
      //   next[i] = f;
      //   break;
      // }
      prev = i;
    }

    if (i == -1) {
      if (parent != -1 && c.height > flowers[prev].height && free(c, flowers[prev])) {
        System.out.println(String.format("Inserting 3: c = %s, prev = %s, parent = %s, f = %s, block = %s", c, prev, parent, f, block));
        next[f] = next[parent];
        next[parent] = f;
      } else {
        System.out.println(String.format("Inserting 2: c = %s, prev = %s, parent = %s, f = %s, block = %s", c, prev, parent, f, block));
        next[prev] = f;
        last = f;
      }
    } else {
    
    }
    updateBlock(block, first, f, f, last);
  }

  private void merge(int tallIdx, int shortIdx) {
    //System.out.println(String.format("Before merge: tallIdx = %s, shortIdx = %s", tallIdx, shortIdx));
    Block taller = parents.get(tallIdx);
    Block shorter = parents.get(shortIdx);
    //System.out.println(String.format("Before merge: taller = %s, shorter = %s", taller, shorter));
    if (!free(flowers[taller.right], flowers[shorter.left])
      && flowers[shorter.first].height < flowers[taller.first].height) {
      next[shorter.last] = taller.first;
      updateBlock(shorter, shorter.first, taller.left, taller.right, taller.last);
      parents.remove(taller);
    }
    //System.out.println(String.format("After merge: taller = %s, shorter = %s", taller, shorter));
  }

  private void updateBlock(Block b, int firstIdx, int bloomIdx, int wiltIdx, int lastIdx) {
    //System.out.println(String.format("Updating block: b = %s, first = %s, left = %s, right = %s, last = %s", b, firstIdx, bloomIdx, wiltIdx, lastIdx));
    Flower f_bloom = flowers[bloomIdx];
    Flower f_wilt = flowers[wiltIdx];
    if (flowers[b.left].bloom > f_bloom.bloom) b.left = bloomIdx;
    if (flowers[b.right].wilt < f_wilt.wilt) b.right = wiltIdx;
    b.first = firstIdx;
    b.last = lastIdx;
    b.maxHeight = flowers[b.first].height;
  }

  private boolean free(Flower m, Flower n) {
    return free(m, n.bloom, n.wilt); 
  }

  private boolean free(Flower m, int bloom, int wilt) {
    return m.wilt < bloom || m.bloom > wilt;
  }
}
