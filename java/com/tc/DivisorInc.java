package com.tc;

import java.util.*;

public class DivisorInc {
    public int countOperations(int N, int M) {
        if (N == M) return 0;
        boolean found = false; 
        boolean[] marked = new boolean[M];
        int[] path = new int[M+1];
        
        Arrays.fill(marked, false);
        Arrays.fill(path, -1);
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.add(N);
        while (!q.isEmpty() && !found) {
            int v = q.poll();
            List<Integer> adj = getAdj(v, M);
            for (int w : adj) {
                System.out.println(String.format("v = %s, adj = %s", v, w));
                if (w == M) {
                    path[M] = v;
                    found = true;
                    break;
                }
                if (!marked[w]) {
                    q.add(w);
                    path[w] = v;
                    marked[w] = true;
                }
            }
        }
        int j = 0;
        for (int i = M; path[i] != -1; i = path[i], j++);
        return j == 0 ? -1 : j;

    }

    private List<Integer> getAdj(int v, int M) {
        List<Integer> adj = new ArrayList<Integer>();
        for (int i = 2; i <= v/2; i++) {
            if (v % i == 0) {
                int a = v+(v/i);
                if (a <= M) adj.add(a);
            }
        }
        return adj;
    }

    private boolean[] sieve(int n) {
        boolean[] prime = new boolean[n+1];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;

        for (int i = 2; i <= n; i++)
            if (prime[i])
                for (int k = i*i; k <= n; k += i)
                    prime[k] = false;
    }

    /*
    private int ld(int v) {
        if (v % 2 == 0) return 2;
        if (v % 3 == 0) return 3;
        for (int i = 5; i * i <= v; i += 6) {
            if (v % i == 0) return i;
            if (v % (i + 2) == 0) return i + 2;
        }
        return -1;
    }
    */


    public static void main(String[] args) {
        System.out.println(String.format("count = %s",
            new DivisorInc().countOperations(
                Integer.parseInt(args[0]),
                Integer.parseInt(args[1]))));
    }

}
