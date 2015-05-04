package com.hr;

import java.util.*;

public class RustMurderer {
    private class Vertex implements Comparable<Vertex> {
        public int val, distTo;
        public Set<Vertex> adj;
        public Vertex(int val, int distTo) {
            this.val = val;
            this.distTo = distTo;
            this.adj = new HashSet<Vertex>();
        }
        public Vertex(int val, int distTo, Set<Vertex> adj) {
            this(val, distTo);
            this.adj = adj;
        }
        
        public void addAdj(Vertex w) {
            adj.add(w);
        }
        public boolean isAdj(Vertex w) {
            return adj.contains(w);
        }
        public int adjCount() {
            return adj.size();
        }
        public int compareTo(Vertex that) {
            if (distTo < that.distTo) return -1;
            if (distTo == that.distTo) return 0;
            return 1;
        }
    }

    public String minSideRoads(int citySize, String[] mainRoads, int start) {
        Vertex[] vertices = buildVertices(citySize, mainRoads, start);
        Queue<Vertex> vertexPq = new PriorityQueue<Vertex>(); 
        for (int i = 1; i <= citySize; i++)
            vertexPq.add(vertices[i]);
        Vertex s = vertices[start];
        for (Vertex a : s.adj) {
            int distTo = 0;
            for (Vertex w : vertexPq)
                if (!a.isAdj(w)) {
                    distTo = w.distTo;
                    break;
                }
            vertexPq.remove(a);
            a.distTo += distTo;
            vertexPq.add(a);
        }
        return stringify(vertices, start);
    }

    private Vertex[] buildVertices(int citySize, 
        String[] mainRoads, 
        int start) {
        Vertex[] vertices = new Vertex[citySize+1];
        for (int i = 1; i <= citySize; i++)
            vertices[i] = new Vertex(i, 1);
        for (String r : mainRoads) {
            String[] rs = r.split(" ");
            int r1 = Integer.parseInt(rs[0]),
                r2 = Integer.parseInt(rs[1]);
            vertices[r1].addAdj(vertices[r2]);
            vertices[r2].addAdj(vertices[r1]);
        }
        return vertices;
    }

    private String stringify(Vertex[] vertices, int start) {
        StringBuilder sb = new StringBuilder(vertices.length*2);
        for (int i = 1; i < vertices.length; i++) {
            Vertex v = vertices[i];
            if (v.val == start) continue;
            sb.append(v.distTo).append(" ");
        }
        return sb.deleteCharAt(sb.length()-1).toString();
    }

    public static void main(String[] args) {
        RustMurderer rm = new RustMurderer();
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String[] testCase = s.nextLine().split("\\t");
            int citySize = Integer.parseInt(testCase[0]),
                start = Integer.parseInt(testCase[1]);
            String[] mainRoads = testCase[2].split(",");
            String actual = rm.minSideRoads(citySize, mainRoads, start);
            if (!actual.equals(testCase[3]))
                System.out.format("%s\texpected=%s\tactual=%s\n", 
                    testCase[2],
                    testCase[3],
                    actual);
        }
    }
}
