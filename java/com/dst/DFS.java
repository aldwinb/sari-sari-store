package com.dst;

import java.util.*;

public class DFS<T> extends GraphSearch<T> {
  private Set<T> marked;
  
  // constructor
  public DFS(Graph<T> g, T s) {
    marked = new HashSet<T>(g.V());
    dfs(g, s);
  }
  
  // public methods
  public boolean isConnected(T v) {
    return marked.contains(v);
  }

  public int count() {
    return marked.size();
  }

  // private methods
  private void dfs(Graph<T> g, T v) {
    marked.add(v);
    for (T w : g.adj(v)) {
      if (!marked.contains(w))
        dfs(g, w);
    }
  }

  public static void main(String[] args) throws java.io.IOException {
    StringVertexScanner scanner = new StringVertexScanner(System.in, 1);
    GraphSearch<String> dfs = new DFS<String>(scanner.graph(), args[0]);
    System.out.println(String.format("Vertex pair: %s %s", args[0], args[1]));
    System.out.println(String.format("Connected: %s", dfs.isConnected(args[1]) ? "yes" : "no"));
    System.out.println(String.format("How many: %s", dfs.count()));
  }
}
