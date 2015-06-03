package com.hr;

import java.util.*;

public class RustMurderer {

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
            
            while (!q.isEmpty()) {
                Vertex v = q.poll();
                Set<Integer> ws = vertices.get(v.val);
                if (!ws.contains(start)) {
                    mins[s] = v.distTo+1;
                    break;
                }
                for (int i = 1; i <= citySize; i++) {
                    if (inQ[i]) continue;
                    if (ws.contains(i)) continue;
                    q.add(new Vertex(i, v.distTo+1));
                    inQ[i] = true;
                }

                /*
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
                */
            }
        }

        Integer[] res = new Integer[citySize-1];
        int j = 0;
        for (int i = 1; i <= citySize; i++) {
            if (i == start) continue;
            res[j++] = mins[i]; 
        }
        return res;
    }

    private Map<Integer, Set<Integer>> buildVertices(
        int citySize, 
        String[] mainRoads, 
        int start) {
    
        /*
        BitSet[] hasEdge = new BitSet[citySize+1];
        for (int i = 0; i < hasEdge.length; i++)
            hasEdge[i] = new BitSet(citySize+1);
        //Arrays.fill(hasEdge, new BitSet(citySize+1));

        for (int i = 1; i <= citySize; i++) 
            for (int j = 1; j <= citySize; j++) 
                System.out.format("i = %s, j = %s, hasEdge[i][j] = %s\n", i, j, hasEdge[i].get(j));
        */

        //System.out.format("hasEdge[0] = %s\n", hasEdge[0].toString());

        Map<Integer, Set<Integer>> vertices = 
            new HashMap<Integer, Set<Integer>>();
        
        for (String r : mainRoads) {
            String[] rs = r.trim().split(" ");
            if (rs[0].length() == 0) continue;
            int r1 = Integer.parseInt(rs[0]),
                r2 = Integer.parseInt(rs[1]);
            
            if (!vertices.containsKey(r1))
                vertices.put(r1, new HashSet<Integer>());
            if (!vertices.containsKey(r2))
                vertices.put(r2, new HashSet<Integer>());
            vertices.get(r1).add(r2);
            vertices.get(r2).add(r1);
            //hasEdge[r1].set(r2);
            //hasEdge[r2].set(r1);
        }

        return vertices;
        /*
        Map<Integer, Set<Integer>> antiVert = 
            new HashMap<Integer, Set<Integer>>();
        for (int k : vertices.keySet()) {
            if (k == start) continue;
            Set<Integer> adj = vertices.get(k),
                antiAdj = new HashSet<Integer>();
            for (int i = 1; i <= citySize; i++) {
                if (!adj.contains(i))
                    antiAdj.add(i);
            }
            antiVert.put(k, antiAdj);
        }

        return antiVert;
        */

        /*
        for (int i = 1; i <= citySize; i++) 
            for (int j = 1; j <= citySize; j++) 
                System.out.format("i = %s, j = %s, hasEdge[i][j] = %s\n", 
                    i, 
                    j, 
                    hasEdge[i].get(j) ? 1 : 0);

        Map<Integer, Set<Integer>> vertices = 
            new HashMap<Integer, Set<Integer>>();
        for (int i = 1; i <= citySize; i++) {
            if (i == start) continue;
            Set<Integer> ws = new HashSet<Integer>();
            for (int j = 1; j <= citySize; j++) {
                if (i == j) continue;
                //System.out.format("i = %s, j = %s, hasEdge[i][j] = %s\n", i, j, hasEdge[i].get(j));
                if (!hasEdge[i].get(j))
                    ws.add(j);
            }
            vertices.put(i, ws);
        }

        BitSet startEdges = hasEdge[start];
        Set<Integer> ws = new HashSet<Integer>();
        for (int i = 1; i <= citySize; i++)
            if (startEdges.get(i))
                ws.add(i);
        vertices.put(start, ws);

        return vertices;
        }

        boolean[] startEdges = hasEdge[start];
        Set<Integer> ws = new HashSet<Integer>();
        for (int i = 1; i <= citySize; i++)
            if (!startEdges[i])
                ws.add(i);
        vertices.put(start, ws);

        return vertices;
        */
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
