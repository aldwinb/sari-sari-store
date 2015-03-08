package com.tc;

import java.util.*;

public class DivisorInc {
    public int countOperations(int N, int M) {
        
        if (N == M) return 0;
       
        boolean found = false;
        int[] path = new int[M+1];
        boolean[] inQ = new boolean[M+1];
        Arrays.fill(path, -1);
        Arrays.fill(inQ, false);
        
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.add(N);

        while (!q.isEmpty() && !found) {
            int v = q.poll();
            Iterable<Integer> adj = adj(v, M);
            for (int w : adj) {
                if (w == M) {
                    path[M] = v;
                    found = true;
                    break;
                }
                if (!inQ[w]) {
                    path[w] = v;
                    q.add(w);
                    inQ[w] = true;
                }
            }
        }
        int j = 0;
        for (int i = M; path[i] != -1; i = path[i], j++);
        return j == 0 ? -1 : j;
    }

    private Iterable<Integer> adj(int v, int M) {
        List<Integer> adj = new ArrayList<Integer>();
        Iterable<Integer> factors = getFactors(v);
        for (int f : factors) {
            int w = v+f;
            if (w <= M) 
                adj.add(w);
        }
        return adj;
    }

    private Iterable<Integer> getFactors(int v) {
        int sqrt = (int)Math.round(Math.sqrt(v));
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 2; i <= sqrt; i++)
            if (v % i == 0) {
                set.add(v/i);
                set.add(i);
            }
        return set;
    }

    public static void main(String[] args) {
        System.out.println(String.format("count = %s",
            new DivisorInc().countOperations(
                Integer.parseInt(args[0]),
                Integer.parseInt(args[1]))));
    }
}
