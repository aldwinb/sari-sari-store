package com.tc;

import java.util.*;

public class AddElectricalWires {
  private boolean marked[];
  private boolean gridConnected[];
  private int V[];
  private int E[];
  private Set<Integer> gridConnSet;
  private int cc;
 
  public AddElectricalWires() {
    cc = 0;
  }

  private class Undigraph<T> {
    private Hashtable<T, Set<T>> V;
    private int E;

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
        T v = (T)k.nextElement();
        sb.append(String.format("%s:", v));
        Set<T> edges = V.get(v);
        for (T e : edges)
          sb.append(String.format(" %s", e));
        sb.append("\n");
      }
      if (sb.length() > 0)
        sb.deleteCharAt(sb.length()-1);
      return sb.toString();
    }

  }

  public int maxNewWires(String[] wires, int[] gridConnections) {
    Integer[] vertices = new Integer[wires.length];
    gridConnSet = new HashSet<Integer>(gridConnections.length);
    
    for (int i = 0; i < wires.length; i++)
      vertices[i] = i;
    for (int gc : gridConnections)
      gridConnSet.add(gc);

    Undigraph g = new Undigraph(vertices);

    // build predefined edges
    for (int i = 0; i < g.V(); i++) {
      char[] xs = wires[i].toCharArray();
      for (int j = 0; j < g.V(); j++) {
        if (xs[j] == '1' && i != j) { 
          g.addEdge(i, j);
          g.addEdge(j, i);
        }
      }
    }

    marked = new boolean[g.V()];
    gridConnected = new boolean[g.V()];
    V = new int[g.V()];
    E = new int[g.V()];
    
    // check connected components
    int maxGridWires = 0, maxGridV = 0, maxGrid = 0;
    int maxNonGridV = 0, maxNonGridE = 0;
    boolean maxGridConnected = false;
    for (int i = 0; i < g.V(); i++) {
      if (!marked[i]) {
        dfs(g, i);
        if (!gridConnected[cc]) {
          maxNonGridV += V[cc];
          maxNonGridE += E[cc];
        } else {
          maxGrid += ((V[cc]*(V[cc]-1)) / 2) - (E[cc] / 2);
          maxGridV = Math.max(maxGridV, V[cc]);
        } 
        cc++;
      }
    }

    return (((maxNonGridV*(maxNonGridV-1)) / 2) - (maxNonGridE / 2)) + (maxNonGridV*maxGridV) + maxGrid;
  }

  private void dfs(Undigraph<Integer> g, int v) {
    marked[v] = true;
    V[cc]++;
    if (gridConnSet.contains(v))
      gridConnected[cc] = true;
    int edgeCount = 0;
    for (Integer w : g.adj(v)) {
      edgeCount++;
      if (!marked[w])
        dfs(g, w);
    }
    E[cc] += edgeCount;
  }
}
