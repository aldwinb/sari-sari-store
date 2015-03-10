package com.tc;

import java.util.*;

public class Solution1812 {
    private class Cell {
        public int x;
        public int y;
        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private class SubMatrix implements Comparable<SubMatrix> {
        public int sum;
        public Cell start, end;
        public SubMatrix(int startX, int startY, int endX, int endY) {
            start = new Cell(startX, startY);
            end = new Cell(endX, endY);
        }
        public SubMatrix(int sum, int startX, int startY, int endX, int endY) {
            this(startX, startY, endX, endY);
            this.sum = sum;
        }
        public int compareTo(SubMatrix sm) {
            if (sum > sm.sum) return 1;
            if (sum == sm.sum) return 0;
            return -1;
        }
    }

    private class SubMatrixMaxComparator implements Comparator<SubMatrix> {
        public int compare(SubMatrix s1, SubMatrix s2) {
             return s1.compareTo(s2)*-1;
        }
    }

    public int[] maxSubmatrixSum(int[][] M) {
        if (M == null || M.length == 0) return new int[0];
        Queue<SubMatrix> q = initSmQueue(M);
        SubMatrix max = q.poll();
        while (!q.isEmpty()) {
            SubMatrix sm = q.poll();
            sm = expand(sm,M);
            if (max.compareTo(sm) == -1) {
                max = sm;
                if (expandable(max,M.length))
                    q.add(max);
            } else if (expandable(sm,M.length))
                q.add(sm);
        }
        return new int[] { max.start.x, max.start.y, max.end.x, max.end.y };
        /*
        return String.format("(%s,%s) (%s,%s)", 
            max.start.x, 
            max.start.y, 
            max.end.x, 
            max.end.y);
        */
    }

    private Queue<SubMatrix> initSmQueue(int[][] M) {
        int n = M.length;
        Queue<SubMatrix> q = new PriorityQueue<SubMatrix>(n*n, new SubMatrixMaxComparator());
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                q.add(new SubMatrix(M[i][j],i,j,i,j));
        return q;
    }

    private SubMatrix expand(SubMatrix m, int[][] M) {
        int n = M.length;
        if (!expandable(m,n)) return m;
        for (int i = m.start.x; i <= m.end.x+1; i++)
            m.sum += M[i][m.end.y+1];
        for (int i = m.start.y; i < m.end.y+1; i++)
            m.sum += M[m.end.x+1][i];
        m.end.x++;
        m.end.y++;
        return m;
    }

    private boolean expandable(SubMatrix m, int n) {
        return m.end.x+1 < n && m.end.y+1 < n;
    }

    public static void main(String[] args) {
        //int n = Integer.parseInt(args[1]);
        String[] rows = args[0].split(":");
        int[][] M = new int[rows.length][rows.length];
        for (int i = 0; i < M.length; i++) {
            String[] cells = rows[i].split(",");
            for (int j = 0; j < cells.length; j++)
                M[j][i] = Integer.parseInt(cells[j]);
        }

        System.out.println("Matrix:");
        printMatrix(M);

        int[] coords = new Solution1812().maxSubmatrixSum(M);
        System.out.println("Max Submatrix:");
        printSubMatrix(M, coords);
    }

    private static void printMatrix(int[][] M) {
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++)
                System.out.format("%s ", M[j][i]);
            System.out.println("");
        }
    }

    private static void printSubMatrix(int[][] M, int[] coords) {
        for (int i = coords[1]; i <= coords[3]; i++) {
            for (int j = coords[0]; j <= coords[2]; j++)
                System.out.format("%s ", M[j][i]);
            System.out.println("");
        }
    }
}
