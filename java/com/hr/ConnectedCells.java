package com.hr;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ConnectedCells {

    private class Endpoint {
        public int x1, x2;
        public Endpoint(int x1, int x2) {
            this.x1 = x1;
            this.x2 = x2;
        }
    }

    private class Region {
        List<Endpoint> endpoints;
        public int count;
        public Region(Endpoint e) {
            endpoints = new ArrayList<Endpoint>();
            endpoints.add(e);
        }
    }

    int max = 0;
    // 0 1 0 0 1
    // 0 1 0 0 1
    // 0 1 1 1 0
    // 0 1 0 1 0
    public int maxRegion(int[][] G) {
        if (G.length == 0 || G[0].length == 0) return 0;
        if (G.length == 1 && G[0].length == 1) return G[0][0];

        List<Region> regions = new ArrayList<Region>();
        for (int i = 0; i < G.length; i++) {
            int[] r = G[i];
            int s = -1;
            List<Endpoint> newEnds = new ArrayList<Endpoint>();
            for (int j = 0; j < r.length; j++) {
                if (r[j] == 1 && s == -1) s = j;
                else if (s != -1) {
                    newEnds.add(new Endpoint(s, j-1));
                    s = -1;
                }
            }
            if (s != -1)
                newEnds.add(new Endpoint(s, r.length-1));
            regions = expandRegions(regions, newEnds);
        }
        return max;
    }

    private List<Region> expandRegions(
        List<Region> existing, 
        Iterable<Endpoint> newEnds) {
        Set<Integer> marked = new HashSet<Integer>();
        List<Region> newRegions = new ArrayList<Region>();
        while (!existing.isEmpty()) {
            Region r = existing.remove(0);
            r.endpoints.clear();
            int c = 0;
            for (Endpoint e : newEnds) {
                if (marked.contains(e.x1)) continue;
                if (connected(r, e)) {
                    merge(r, e);
                    marked.add(e.x1);
                    c++;
                }
            }
            if (c > 0) newRegions.add(r);
        }
        for (Endpoint e : newEnds)
            if (!marked.contains(e.x1))
                newRegions.add(new Region(e));
        
        return newRegions;
    }

    private void merge(Region r, Endpoint e) {
        r.count += e.x2+1-e.x1;
        if (max < r.count)
            max = r.count;
        r.endpoints.add(e);
    }

    private boolean connected(Region r, Endpoint e) {
        for (Endpoint re : r.endpoints)
            if (!(re.x1 > e.x2+1 || re.x2 < e.x1-1)) return true;
        return false;
    }

    public static void main(String[] args) {
    }
}
