package com.dst;

interface Graph<T> {
  int V();
  int E();
  void addEdge(T v, T w);
  Iterable<T> adj(T v);
}
