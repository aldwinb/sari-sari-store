package com.tc;

import java.util.*;

public class DivisorInc {
    public int countOperations(int N, int M) {
        if (N == M) return 0;
        boolean found = false; 
        boolean[] marked = new boolean[M];
        int[] path = new int[M+1];
        
        boolean[] primes = sieve(M);
        Arrays.fill(marked, false);
        Arrays.fill(path, -1);
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.add(N);
        while (!q.isEmpty() && !found) {
            int v = q.poll();
            List<Integer> adj = getAdj(v, M, primes);
            for (int w : adj) {
                //System.out.println(String.format("v = %s, adj = %s", v, w));
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

    private List<Integer> getAdj(int v, int M, boolean[] prime) {
        List<Integer> primes = getPrimeFactors(prime, v);
        List<Integer> adj = new ArrayList<Integer>();
        for (int p : primes) {
            for (int i = 1; p*i <= v/2; i++) {
                if (v % (p*i) == 0) {
                    int a = v+(v/(p*i));
                    if (a <= M) adj.add(a);
                }
            }
        }
        return adj;
    }

    private List<Integer> getPrimeFactors(boolean[] prime, int v) {
        List<Integer> primes = new ArrayList<Integer>();
        for (int i = 2; i <= v/2; i++)
            if (prime[i] && v % i == 0)
                primes.add(i);
        return primes;
    }

    private boolean[] sieve(int n) {
        boolean[] prime = new boolean[n+1];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
        for (int i = 2; i <= n/2; i++)
            if (prime[i])
                for (int k = i*i; k > 2 && k <= n; k += i)
                    //System.out.println(String.format("i = %s, k = %s", i, k));
                    prime[k] = false;
        return prime;
    }
    
    public static void main(String[] args) {
        System.out.println(String.format("count = %s",
            new DivisorInc().countOperations(
                Integer.parseInt(args[0]),
                Integer.parseInt(args[1]))));
    }
}
