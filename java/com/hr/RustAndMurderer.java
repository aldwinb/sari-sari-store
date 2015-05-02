package com.hr;

public class RustAndMurderer {
   
    public int[] minSideRoads(int citySize, String[] mainRoads, int start) {
        int[] sideRoads = new int[citySize];
        Arrays.fill(sideRoads, 1);
        sideRoads[start] = 0;
        Map<Integer, Set<Integer>> edges = getEdges(mainRoads);
        Set<Integer> startAdj = edges.get(start);
        for (int w : startAdj) {
            Set<Integer> adj = edges.get(w);
            for (int i = 0; i < sideRoads.length; i++)
                if (i == start) continue;
        }
    }

    public static void main(String[] args) {
        
    }
}
