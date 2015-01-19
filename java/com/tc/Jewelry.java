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
        int max = 0;
        Arrays.sort(values);
        for (int i = 0; i < N-1; i++)
            max += values[i];

        int[] waysLo = new int[max],
            waysHi = new int[max];
        Arrays.fill(waysLo, 0);
        Arrays.fill(waysHi, 0);
        for (int i = 0; i < N-1; i++) {
            calcSums(waysLo, values, i);
            calcSums(waysHi, values, i+1);
            for (int
        }
        return 0;
    }

    private void calcSums(int[] sums, int[] items, int start) {
        for (int i = start; i < items.length; i++)
            for (int j = sums.length-1; j > 0; j--)
                sums[j] += sums[j-items[i]];
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
            System.out.println(String.format("seq = %s, right = %s", seq, right));
            count += (left * right);
            seq = key*i;
        }
        System.out.println(String.format("count = %s", count));
        return count;
    }
    
    private long combo(int n, int r) {
        return fact(n) / (fact(r)*fact(n-r));
    }
    
    private long fact(int n) {
        long f = 1;
        for (int i = 2; i <= n; i++) f *= i;
        //System.out.println(String.format("n = %s, fact = %s", n, f));
        return f;
    }
    
    public static void main(String[] args) {
        String[] s1 = args[0].split(",");
        int[] values = new int[s1.length];
        for (int i = 0; i < s1.length; i++) values[i] = Integer.parseInt(s1[i]);
        long count = new Jewelry().howMany(values);
        System.out.println(String.format("howMany = %s", count));
    }
    
}
