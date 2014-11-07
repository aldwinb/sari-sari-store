package com.dst;

import java.util.*;

public class Undigraph<T> implements Graph<T> {
  Hashtable<T, Set<T>> V;
  int E;

  // constructor
  private Undigraph() {
    this.V = new Hashtable<T, Set<T>>();
    E = 0;
  }

  public Undigraph(T[] vertices) {
    this();
    for (T vertex : vertices)
      V.put(vertex, new HashSet<T>());
  }

  // public methods
  public int V() {
    return V.size();
  }

  public int E() {
    return E;
  }

  public void addEdge(T v, T w) {
    if (!V.containsKey(v) || !V.containsKey(w))
      return;
    V.get(v).add(w);
    V.get(w).add(v);
    E++;
  }

  public Iterable<T> adj(T v) {
    return V.get(v);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(100);
    for (Enumeration k = V.keys(); k.hasMoreElements();) {
      T key = (T)k.nextElement();
      for (T v : V.get(key))
        sb.append(String.format("%s-%s ", key, v));
    }
    return sb.toString();
  }

}
