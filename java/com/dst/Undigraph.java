package com.dst;

import java.util.*;

public class Undigraph<T> implements Graph<T> {
  Dictionary<T, Set<T>> V;
  int E;

  // constructor
  private Graph() {
    this.V = new Hashtable<T, HashSet<T>>();
    E = 0;
  }

  public Graph(T[] vertices) {
    this();
    for (T vertex : vertices)
      V.add(vertex, new HashSet<T>());
  }

  // public methods
  public int V() {
    return V.size();
  }

  public int E() {
    return E;
  }

  public void addEdge(T v, T w) {
    if (!V.containsKey(t) || !V.containsKey(w))
      return;
    V[v].add(w);
    V[w].add(v);
    E++;
  }

  public Iterable<T> adj(T v) {
    return V[v];
  }

  public String toString() {
    for (T k : V.keys()) {
      for (T v : V[k])
        System.out.print(String.format("%s-%s\t", k, v));
      System.out.println("");
    }
  }

}
