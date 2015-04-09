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
        Map<Integer, List<Range>> ranges;
        public int count;
        private Region() {
            count = 0;
        }
        public Region(Range e, int lvl) {
            this();
            ranges = new HashMap<Integer, List<Range>>();
            addRange(e, lvl);
        }
        public void addRange(Range e, int lvl) {
            if (!ranges.containsKey(lvl))
                ranges.put(lvl, new ArrayList<Range>());
            ranges.get(lvl).add(e);
            count += e.x2+1-e.x1;
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
    public int maxRegion(int[][] G) {
        if (G.length == 0 || G[0].length == 0) return 0;
        if (G.length == 1 && G[0].length == 1) return G[0][0];

        int max = 0;
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
            max = Math.max(max, expandRegions(regions, newEnds, i));
        }
        return max;
    }

    private int expandRegions(
        List<Region> regions, 
        Iterable<Range> newEnds,
        int lvl) {
        Set<Integer> marked = new HashSet<Integer>();
        
        int max = 0;
        for (Range e : newEnds)
            for (Region r : regions)
                if (connected(r, e, lvl-1)) {
                    r.addRange(e, lvl);
                    marked.add(e.x2);
                    max = Math.max(max, r.count);
                }

        for (Range e : newEnds)
            if (!marked.contains(e.x2)) {
                Region r = new Region(e, lvl);
                regions.add(r);
                max = Math.max(max, r.count);
            }

        return max;
    }

    private boolean connected(Region r, Range e, int lvl) {
        if (!r.ranges.containsKey(lvl)) return false;
        for (Range re : r.ranges.get(lvl))
            if (!(re.x1 > e.x2+1 || re.x2 < e.x1-1)) return true;
        return false;
    }

    public static void main(String[] args) {
        ConnectedCells cc = new ConnectedCells();
        int ctr = 1;
        Scanner s = new Scanner(System.in);
        for (int c = 1; s.hasNextLine(); c++) {
            String line = s.nextLine();
            String[] testCase = line.split("\\t");
            String[] rows = testCase[0].split("|");
            int[][] G = new int[rows.length][rows[0].split(",").length];
            for (int i = 0; i < rows.length; i++) {
                String[] cols = rows[i].split(",");
                for (int j = 0; j < cols.length; j++)
                    G[i][j] = Integer.parseInt(cols[j]);
            }
            int actual = cc.maxRegion(G),
                expected = Integer.parseInt(testCase[1]);
            if (expected != actual)
                System.out.format("Line %s:\texpected: %s\tactual: %s\n",
                    c,
                    expected,
                    actual);
        }
    }
}
