package com.dst;

import java.util.*;

public class BFSPath<T extends Comparable<T>> extends GraphPath<T> {
  private Set<T> marked;
  private Dictionary<T, T> path;
  private int p;

  // constructor
  public BFSPath(Graph<T> g, T s) {
    marked = new HashSet<T>(g.V());
    path = new Hashtable<T, T>(g.V());
    p = 0;
    bfs(g, s, s);
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
  private void bfs(Graph<T> g, T s, T v) {
    Queue<T> vs = new ArrayDeque<T>();
    marked.add(v);
    path.put(v, s);
    vs.add(v);
    while (!vs.isEmpty()) {
      T v1 = vs.poll();
      for (T w : g.adj(v1)) {
        if (!marked.contains(w)) {
          marked.add(w);
          path.put(w, v1);
          vs.add(w);
        }
      }
    }
  }

  public static void main(String[] args) throws java.io.IOException {
    StringVertexScanner scanner = new StringVertexScanner(System.in, 1);
    String s = args[0], v = args[1];
    GraphPath<String> bfs = new BFSPath<String>(scanner.graph(), s);
    System.out.println(String.format("Vertex pair: %s %s", s, v));
    System.out.println(String.format("Connected: %s", bfs.hasPathTo(v) ? "yes" : "no"));
    if (bfs.hasPathTo(v)) {
      for (String w : bfs.pathTo(v))
        System.out.print(String.format("%s%s", w, w == v ? "" : "-"));
      System.out.println("");
    }
  }
}
