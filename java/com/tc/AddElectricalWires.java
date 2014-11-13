package com.dst;

public class AddElectricalWires {
 
  private class Undigraph<T> {
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

  private class Node {  
    public int id;
    public boolean isGridNode;

    public Node(int id, boolean isGridNode) {
      this.id = id;
      this.isGridNode = isGridNode;
    }
  }

  public int maxNewWires(String[] wires, int[] gridConnections) {
    int[] vertices = new [wires.length];
    Undigraph<Integer> g = new Undigraph<Integer>();

    // build predefined edges
    for (int i = 0; i < vertices.length; i++) {
      char[] xs = wires[i].toCharArray();
      for (int j = 0; j < vertices.length; j++) {
        if (xs[j] = '1' && i != j)
          g.addEdge(j, i);
      }
    }

    System.out.println(g);
    return 0;
  } 
}
