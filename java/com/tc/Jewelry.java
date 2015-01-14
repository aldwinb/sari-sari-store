package com.tc;

import java.util.*;
import java.math.BigInteger;

public class Jewelry {
    private class Group {
        public long key;
        public long size;
        public long nFact;
        
        public Group(long key, long size, long nFact) {
            this.key = key;
            this.size = size;
            this.nFact = nFact;
        }
    }
    
    private List<Group> grps;
    private List<Hashtable<Integer, Long>> seqs;
    private long[][] combos;

    public long howMany(int[] values) {
        int N = values.length;
        grps = new ArrayList<Group>();
        seqs = new ArrayList<Hashtable<Integer, Long>>(N);
        combos = new long[N+1][N+1];
        
        for (int i = 0; i < N; i++)
            seqs.add(new Hashtable<Integer, Long>());
        
        Arrays.sort(values);
        
        calcSubs(values);
        return 0;
    }

    private void calcSubs(int[] values) {
        int cval = values[0],
            csize = 0,
            i = 1,
            N = values.length;
        long count = 0;
        
        for (i = 1; i < N; i++) {
            if (values[i] != cval) {
                //calcSeqs(cval, i-csize);
                count += countSame(cval, i-csize);
                cval = values[i];
                csize = i;
            }
        }
        
        //calcSeqs(cval, i-csize);
        count += countSame(cval, i-csize);
    }
    
    private void buildSeqs(int key, int size, int sidx) {
        int seq = key;
        for (int i = 2; i <= size+1; i++) {
            long right = combo(size, i-1);
            //System.out.println(String.format("left = %s", left));
            //System.out.println(String.format("seq = %s, right = %s, size = %s", seq, right));
            //count += left * right;
            //if ()
            //seqs.get(sidx+i-1).
            seq = key*i;
        }
    }
    
    private long countSame(int key, int size) {
        int seq = key,
            maxseq = key*size;
        long count = 0;
        for (int i = 2; i <= size+1; i++) {
            long left = combo(maxseq-seq, seq),
                right = combo(size, i-1);
            //System.out.println(String.format("left = %s", left));
            System.out.println(String.format("seq = %s, right = %s", seq, right));
            count += (left * right);
            seq = key*i;
        }
        System.out.println(String.format("count = %s", count));
        return count;
    }
    
    private long combo(int n, int r) {
        return (long)(fact(n) / (fact(r)*fact(n-r)));
    }
    
    private BigInteger fact(int n) {
        BigInteger f = new BigInteger("1");
        for (int i = 2; i <= n; i++) f = f.multiply(new BigInteger(i));
        System.out.println(String.format("n = %s, fact = %s", n, f));
        return f;
    }
    
    public static void main(String[] args) {
        String[] s1 = args[0].split(",");
        int[] values = new int[s1.length];
        for (int i = 0; i < s1.length; i++) values[i] = Integer.parseInt(s1[i]);
        long count = new Jewelry().howMany(values);
        System.out.println(String.format("howMany = %s", count));
    }
    
        
    /*  
    for (int i = 0; i < N+1; i++)
        for (int j = 0; j < N+1; j++)
            combos[i][j] = -1;
    */  
    
    /*
    calcSeqs(values);
    for (int i = 0; i < N; i++) {
        System.out.print(String.format("%s :", values[i]));
        Hashtable<Integer, Long> s = seqs.get(i);
        for (int k : s.keySet())
            System.out.print(String.format(" %s,%s", k, s.get(k)));
        System.out.println("");
    }
    */
    
    /*
    private void calcSeqs(int[] values) {
        //Hashtable<Integer, Long> seq = new Hashtable
        seqs.get(0).put(values[0], (long)1);
        for (int i = 1; i < values.length; i++) {
            Hashtable<Integer, Long> prev = seqs.get(i-1),
                curr = seqs.get(i);
            for (int k : prev.keySet())
                curr.put(k, prev.get(k));
            for (int k : prev.keySet()) {
                int nk = k+values[i];
                long c = 0;
                if (curr.containsKey(nk)) c = curr.remove(nk);
                curr.put(nk, c+1);
            }
            int nk = values[i];
            long c = 0;
            if (curr.containsKey(nk)) c = curr.remove(nk);
            curr.put(nk, c+1);
        }
    }
    
    private void calcSeqs(int key, int size) {
        
        for (int j = 1; j <= size; j++) {
            int seq = key*j; 
            long c = 0;
            if (seqs.containsKey(seq)) c = seqs.remove(seq);
            seqs.put(seq, c+combo(size, j));
        }
    }
    */
}
