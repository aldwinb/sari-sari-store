package com.dst;

public abstract class GraphPath<T extends Comparable> {
  //protected GraphSearch(Graph v, T s) { }
  public abstract boolean hasPathTo(T v);
  public abstract Iterable<T> pathTo(T v);
}
