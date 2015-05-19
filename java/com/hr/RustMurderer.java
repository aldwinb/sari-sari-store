package com.hr;

import java.util.*;

public class RustMurderer {
    /*
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
    */

    private class Vertex {
        public int val, distTo;
        public Vertex(int val) {
            this.val = val;
            this.distTo = 0;
        }
        public Vertex(int val, int distTo) {
            this(val);
            this.distTo = distTo;
        }
    }

    /*
    private class IndegreeComparator implements Comparator<Vertex> {
        public int compare(Vertex v1, Vertex v2) {
            if (v1.adjCount() < v2.adjCount()) return -1;
            if (v1.adjCount() == v2.adjCount()) 
                return v1.val < v2.val ? -1 : 1;
            return 1;
        }
    }
    */

    public Integer[] minSideRoads(
        int citySize, 
        String[] mainRoads, 
        int start) {
       
        Integer mins[] = new Integer[citySize+1];
        Arrays.fill(mins, 1);
        Map<Integer, Set<Integer>> vertices = buildVertices(
            citySize,
            mainRoads,
            start);

        Set<Integer> startAdj = vertices.get(start);
        for (int s : startAdj) {
            Queue<Vertex> q = new LinkedList<Vertex>();
            boolean[] inQ = new boolean[citySize+1];
            boolean foundMin = false;

            q.add(new Vertex(s));
            Arrays.fill(inQ, false);
            
            while (!q.isEmpty() && !foundMin) {
                Vertex v = q.poll();
                for (int w : vertices.get(v.val)) {
                    if (w == s) continue;
                    //System.out.format("s = %s, v = %s, w = %s\n", s, v.val, w);
                    if (w == start) {
                        mins[s] = v.distTo+1;
                        foundMin = true;
                        break;
                    }
                    if (inQ[w]) continue;
                    q.add(new Vertex(w, v.distTo+1));
                    inQ[w] = true;
                }
            }
        }

        Integer[] res = new Integer[citySize-1];
        int j = 0;
        for (int i = 1; i <= citySize; i++) {
            if (i == start) continue;
            res[j++] = mins[i]; 
        }
        return res;
        /*
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
                
                    if (start == 1 && citySize == 5)
                        System.out.format("a = %s, w = %s, dist = %s\n", a.val, w.val, w.distTo);
                if (!a.isAdj(w)) {
                    
                    
                    distTo = w.distTo;
                    break;
                }
            }
            a.distTo += distTo;
            vertexPq.add(a);
        }

        return getDists(vertices, start);
        */
    }

    private Map<Integer, Set<Integer>> buildVertices(
        int citySize, 
        String[] mainRoads, 
        int start) {

        /*
                for (int i = 1; i <= citySize; i++) {
            Set<Integer> ws = new HashSet<Integer>();
            for (int j = 1; i <= citySize; j++) {
                if (i == j) continue;
                ws.add(j);
            }
            vertices.put(i, ws);
        }
        */
        
        boolean[][] hasEdge = new boolean[citySize+1][citySize+1];
        for (int i = 1; i <= citySize; i++)
            Arrays.fill(hasEdge[i], true);

        for (String r : mainRoads) {
            String[] rs = r.trim().split(" ");
            if (rs[0].length() == 0) continue;
            int r1 = Integer.parseInt(rs[0]),
                r2 = Integer.parseInt(rs[1]);
            hasEdge[r1][r2] = false;
            hasEdge[r2][r1] = false;
        }

        Map<Integer, Set<Integer>> vertices = 
            new HashMap<Integer, Set<Integer>>();
        for (int i = 1; i <= citySize; i++) {
            if (i == start) continue;
            Set<Integer> ws = new HashSet<Integer>();
            for (int j = 1; j <= citySize; j++) {
                if (i == j) continue;
                if (hasEdge[i][j])
                    ws.add(j);
            }
            vertices.put(i, ws);
        }

        boolean[] startEdges = hasEdge[start];
        Set<Integer> ws = new HashSet<Integer>();
        for (int i = 1; i <= citySize; i++)
            if (!startEdges[i])
                ws.add(i);
        vertices.put(start, ws);

        return vertices;
    }

    /*
    private Set<Vertex> sortAdj(Iterable<Vertex> vIter) {
        Set<Vertex> sorted = new TreeSet<Vertex>(new IndegreeComparator());
        for (Vertex v : vIter)
            sorted.add(v);
        return sorted;
    }
    */

    /*
    private Integer[] getDists(Vertex[] vertices, int start) {
        List<Integer> dists = new ArrayList<Integer>();
        for (int i = 1; i < vertices.length; i++) {
            Vertex v = vertices[i];
            if (v.val == start) continue;
            dists.add(v.distTo);
        }
        return dists.toArray(new Integer[0]);
    }
    */

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
                System.out.format("e = %s\n", expected);
                System.out.format("a = %s\n", actual);
                System.out.println("");
            }
        }
    }
    
    private static String stringify(Integer[] dist) {
        StringBuilder sb = new StringBuilder(dist.length*2);
        for (int i = 0; i < dist.length; i++) {
            sb.append(dist[i]).append(" ");
        }
        return sb.deleteCharAt(sb.length()-1).toString();
    }

}
