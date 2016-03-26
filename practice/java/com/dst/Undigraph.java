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
    for (T vertex : vertices) {
      if (!V.containsKey(vertex))
        V.put(vertex, new HashSet<T>());
    }
  }

  public Undigraph(T[] v1, T[] v2) {
    this(v1);
    for (T v : v2) {
      if (!V.containsKey(v))
        V.put(v, new HashSet<T>());
    }
    
    for (int i = 0; i < v1.length; i++)
      addEdge(v1[i], v2[i]);
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
    //System.out.println(String.format("Add edge: %s %s", v, w));
    //System.out.println(String.format("Add edge: %s %s", w, v));
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
      T v = (T)k.nextElement();
      sb.append(String.format("%s:", v));
      Set<T> edges = V.get(v);
      for (T e : edges)
        sb.append(String.format(" %s", e));
      sb.append("\n");
    }
    sb.deleteCharAt(sb.length()-1);
    return sb.toString();
  }

}
