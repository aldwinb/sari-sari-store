package com.dst;

public abstract class GraphSearch<T> {
  //protected GraphSearch(Graph v, T s) { }
  public abstract boolean isConnected(T v);
  public abstract int count();
}
