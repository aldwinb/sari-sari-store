package com.dst;

import java.util.*;

public class DFSPath<T extends Comparable<T>> extends GraphPath<T> {
  private Set<T> marked;
  private Dictionary<T, T> path;
  private int p;

  // constructor
  public DFSPath(Graph<T> g, T s) {
    marked = new HashSet<T>(g.V());
    path = new Hashtable<T, T>(g.V());
    p = 0;
    dfs(g, s, s);
  }
  
  // public methods
  public boolean hasPathTo(T v) {
    return marked.contains(v);
  }

  public Iterable<T> pathTo(T v) {
    ArrayDeque<T> pathTo = new ArrayDeque<T>(path.size());
    if (hasPathTo(v)) {
      pathTo.add(v);
      T w;
      for (w = path.get(v); w.compareTo(path.get(w)) != 0; w = path.get(w))
        pathTo.addFirst(w);
      pathTo.addFirst(w);
    }
    return pathTo;
  }

  // private methods
  private void dfs(Graph<T> g, T s, T v) {
    marked.add(v);
    path.put(v, s);
    for (T w : g.adj(v)) {
      if (!marked.contains(w))
        dfs(g, v, w);
    }
  }

  public static void main(String[] args) throws java.io.IOException {
    StringVertexScanner scanner = new StringVertexScanner(System.in, 1);
    String s = args[0], v = args[1];
    GraphPath<String> dfs = new DFSPath<String>(scanner.graph(), s);
    System.out.println(String.format("Vertex pair: %s %s", s, v));
    System.out.println(String.format("Connected: %s", dfs.hasPathTo(v) ? "yes" : "no"));
    if (dfs.hasPathTo(v)) {
      for (String w : dfs.pathTo(v))
        System.out.print(String.format("%s%s", w,  w == v ? "" : "-"));
      System.out.println("");
    }
  }
}
