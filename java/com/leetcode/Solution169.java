package com.leetcode;

import java.util.*;

public class Solution169 {
    private class Candidate {
        int lo, hi;
        public Candidate(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }
    }

    public int majorityElement(int[] num) {
        Arrays.sort(num);
        Candidate majority = findMajority(num,0,num.length-1,2);
        return num[majority.lo];
    }

    /*
    1 2 2 2
   
    lo = 0, hi = 2, limit = 2
      s = 0, e = 2
      lo = 0, hi
    */
    private Candidate findMajority(int[] num, int lo, int hi, int limit) {
        //System.out.format("lo = %s, hi = %s, limit = %s\n", lo, hi, limit);
        if (hi-lo < limit) return null;    
        int majLimit = (hi+1-lo)/limit;
        if (majLimit == 0 || lo == hi)
            //System.out.format("cand = %s\n", num[lo]);
            return new Candidate(lo, lo);

        Candidate[] parts = new Candidate[limit];
        for (int i = lo; i <= hi; i += majLimit) {
            System.out.format("p.lo = %s, p.hi = %s\n", parts[i].lo, parts[i].hi);
            parts[i] = findMajority(num,i,Math.min(i+majLimit-1,hi),limit);
            System.out.format("p.lo = %s, p.hi = %s\n", parts[i].lo, parts[i].hi);
        }
        Candidate majority = parts[0];
        for (int i = 1; i < parts.length; i++) {
            merge(majority,parts[i],num,majLimit);
            if (majority != null) return majority;
        }
        return null;
    }

    /*
    1 2 3  
    0 
    1 2 3 3   3 3 4 4 6 6 6 7   7 7 7 8
    0     3   4     7 8     11 12     15
    */
    private void merge(Candidate left, Candidate right, int num[], int majority) {
        if (left != null) {
            int newLeftHi = left.hi+1+majority-(left.hi+1-left.lo);
            if (num[newLeftHi] == num[left.hi])
                left.hi = newLeftHi;
            if (left.hi+1-left.lo > majority)
                return;
        }

        if (right != null) {
            int newRightLo = right.lo-majority-(right.hi+1-right.lo);
            if (num[newRightLo] == num[right.lo])
                right.lo = newRightLo;
            if (right.hi+1-right.lo > majority) {
                left = right;
                return;
            }
        }
    
        left = null;
    }

    public static void main(String[] args) {
        String[] strNums = args[0].split(",");
        int[] num = new int[strNums.length];
        for (int i = 0; i < strNums.length; i++)
        num[i] = Integer.parseInt(strNums[i]);
        System.out.println(String.format("%s", new Solution169().majorityElement(num)));
    }
}
