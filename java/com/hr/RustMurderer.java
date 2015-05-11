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
        @Override
        public int hashCode() {
            return val;
        }
    }

    private class IndegreeComparator implements Comparator<Vertex> {
        public int compare(Vertex v1, Vertex v2) {
            if (v1.adjCount() < v2.adjCount()) return -1;
            if (v1.adjCount() == v2.adjCount()) return 0;
            return 1;
        }
    }

    public Integer[] minSideRoads(int citySize, String[] mainRoads, int start) {
        Vertex[] vertices = buildVertices(citySize, mainRoads, start);
        Vertex s = vertices[start];
        
        Queue<Vertex> vertexPq = new PriorityQueue<Vertex>(); 
        for (int i = 1; i <= citySize; i++) {
            Vertex v = vertices[i];
            if (!v.equals(s) && !v.isAdj(s))
                vertexPq.add(v);
        }

        for (Vertex a : sortAdj(s.adj)) {
            int distTo = 0;
            for (Vertex w : vertexPq) {
                if (!a.isAdj(w)) {
                    /*
                    if (start == 225)
                        System.out.format("a = %s, w = %s, dist = %s\n", a.val, w.val, w.distTo);
                    */
                    distTo = w.distTo;
                    break;
                }
            }
            a.distTo += distTo;
            vertexPq.add(a);
        }

        return getDists(vertices, start);
    }

    private Set<Vertex> sortAdj(Iterable<Vertex> vIter) {
        Set<Vertex> sorted = new TreeSet<Vertex>(new IndegreeComparator());
        for (Vertex v : vIter)
            sorted.add(v);
        return sorted;
    }

    private Vertex[] buildVertices(int citySize, 
        String[] mainRoads, 
        int start) {
        Vertex[] vertices = new Vertex[citySize+1];
        for (int i = 1; i <= citySize; i++)
            vertices[i] = new Vertex(i, 1);
        for (String r : mainRoads) {
            String[] rs = r.trim().split(" ");
            if (rs[0].length() == 0) continue;
            int r1 = Integer.parseInt(rs[0]),
                r2 = Integer.parseInt(rs[1]);
            vertices[r1].addAdj(vertices[r2]);
            vertices[r2].addAdj(vertices[r1]);
        }
        return vertices;
    }

    private Integer[] getDists(Vertex[] vertices, int start) {
        List<Integer> dists = new ArrayList<Integer>();
        for (int i = 1; i < vertices.length; i++) {
            Vertex v = vertices[i];
            if (v.val == start) continue;
            dists.add(v.distTo);
        }
        return dists.toArray(new Integer[0]);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = Integer.parseInt(s.nextLine());
        for (int i = 0; i < T; i++) {
            String[] g = s.nextLine().split(" ");
            int N = Integer.parseInt(g[0]),
                M = Integer.parseInt(g[1]);
            List<String> roadList = new ArrayList<String>(M);
            for (int j = 0; j < M; j++) 
                roadList.add(s.nextLine());
            int start = Integer.parseInt(s.nextLine());
            String expected = s.nextLine(),
                actual = stringify(
                    new RustMurderer().minSideRoads(
                        N,
                        roadList.toArray(new String[0]),
                        start));
            //System.out.format("actual=%s\n", actual);
            if (!expected.equals(actual)) {
                System.out.format("N = %s\n", N);
                System.out.format("M = %s\n", M);
                System.out.format("S = %s\n", start);
                System.out.format("expected = %s\n", expected);
                System.out.format("actual = %s\n", actual); 
            }
        }
        
        /*
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String[] testCase = s.nextLine().split("\\t");
            int citySize = Integer.parseInt(testCase[0]),
                start = Integer.parseInt(testCase[1]);
            String[] mainRoads = testCase[2].split(",");
            String actual = stringify(
                new RustMurderer().minSideRoads(citySize, mainRoads, start));
            if (!testCase[3].equals(actual))
                System.out.format("%s\texpected=%s\tactual=%s\n", 
                    testCase[2],
                    testCase[3],
                    actual);
        }
        */
    }
    
    private static String stringify(Integer[] dist) {
        StringBuilder sb = new StringBuilder(dist.length*2);
        for (int i = 0; i < dist.length; i++) {
            sb.append(dist[i]).append(" ");
        }
        return sb.deleteCharAt(sb.length()-1).toString();
    }

}
