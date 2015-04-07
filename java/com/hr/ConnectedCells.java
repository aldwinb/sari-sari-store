package com.hr;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ConnectedCells {

    private class Range {
        public int x1, x2;
        public Range(int x1, int x2) {
            this.x1 = x1;
            this.x2 = x2;
        }
    }

    private class Region {
        //Range[][] ranges;
        Map<Integer, List<Range>> ranges;
        public int count;
        public Region(int lvl, Range e) {
            ranges = HashMap<Integer, List<Range>>();
            ranges.put(lvl, e);
            count = e.x2+1-e.x1;
        }
    }
    
    // 0 1 1 1 0
    // 1 1 0 1 0
    //
    // y: 0
    // x: 0-1
    //
    // y: 0
    // x: 3-3
    //
    // y: 1
    // x: 1-3
    //
    int max = 0;
    public int maxRegion(int[][] G) {
        if (G.length == 0 || G[0].length == 0) return 0;
        if (G.length == 1 && G[0].length == 1) return G[0][0];

        List<Region> regions = new ArrayList<Region>();
        for (int i = 0; i < G.length; i++) {
            int[] r = G[i];
            int s = -1;
            List<Range> newEnds = new ArrayList<Range>();
            for (int j = 0; j < r.length; j++) {
                if (r[j] == 1 && s == -1) s = j;
                else if (s != -1) {
                    newEnds.add(new Range(s, j-1));
                    s = -1;
                }
            }
            if (s != -1)
                newEnds.add(new Range(s, r.length-1));
            regions = expandRegions(regions, newEnds);
        }
        return max;
    }

    private void expandRegions(
        int lvl,
        Iterable<Region> existing, 
        Iterable<Range> newEnds) {
        //Set<Integer> marked = new HashSet<Integer>();
        //List<Region> newRegions = new ArrayList<Region>();
        
        for (Range ne : newEnds) {
            //Region r = new Region(ne);
            for (Region ex : existing) {
                if (connected(lvl, ex, ne))
                    merge(r, lvl, ne);
            }
        }
        
        //return newRegions;
    }
    /*
    private List<Region> expandRegions(
        List<Region> existing, 
        Iterable<Range> newEnds) {
        Set<Integer> marked = new HashSet<Integer>();
        List<Region> newRegions = new ArrayList<Region>();
        while (!existing.isEmpty()) {
            Region r = existing.remove(0);
            r.ranges.clear();
            int c = 0;
            for (Range e : newEnds) {
                if (marked.contains(e.x1)) continue;
                if (connected(r, e)) {
                    merge(r, e);
                    marked.add(e.x1);
                    c++;
                }
            }
            if (c > 0) newRegions.add(r);
        }
        for (Range e : newEnds)
            if (!marked.contains(e.x1))
                newRegions.add(new Region(e));
        
        return newRegions;
    }
    */

    private void merge(Region r, int lvl, Range e) {
        r.count += e.x2+1-e.x1;
        if (max < r.count)
            max = r.count;
        r.ranges.put(lvl, e);
    }

    private boolean connected(Region r, int lvl, Range e) {
        if (!r.containsKey(lvl)) return false;
        Range re = r.ranges.get(lvl);
        return !(re.x1 > e.x2+1 || re.x2 < e.x1-1);
    }

    public static void main(String[] args) {
    }
}
