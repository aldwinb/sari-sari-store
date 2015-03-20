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
    private Candidate findMajority(int[] num, int lo, int hi, int partition) {
        if (lo >= num.length) return null;
        if (lo == hi)
            return new Candidate(lo, lo);
        int majLimit = (int)Math.round(((hi+1-lo)/(partition*1.0)));
        Candidate[] parts = new Candidate[partition];
        for (int i = 0, j = lo; i < partition; i++, j += majLimit) {
            //System.out.format("p.lo = %s, p.hi = %s\n", parts[i].lo, parts[i].hi);
            parts[i] = findMajority(num,j,j+majLimit-1,partition);
            if (parts[i] != null)
                System.out.format("lo = %s, hi = %s, majLimit = %s\n", parts[i].lo, parts[i].hi, majLimit);
        }
        
        Candidate majority = parts[0];
        for (int i = 1; i < parts.length; i++) {
            //if (parts[i] != null)
            //    System.out.format("merge lo = %s, p.hi = %s\n", parts[i].lo, parts[i].hi);
            majority = merge(majority,parts[i],num,majLimit);
            if (majority != null)
                System.out.format("major lo = %s, hi = %s\n", majority.lo, majority.hi);
            else
                System.out.println("majority null");
            if (majority != null) return majority;
        }
            //if (majority != null)
            //    System.out.format("major lo = %s, hi = %s\n", majority.lo, majority.hi);
            //else
        //    System.out.println("majority null");

        if (majority != null && majority.hi+1-majority.lo > majLimit-1)
            return majority;
        return null;
    }

       private Candidate merge(Candidate left, Candidate right, int num[], int majority) {
        if (left != null) {
            int newLeftHi = left.hi+majority-(left.hi+1-left.lo)+1;
            System.out.format("merge left lo = %s, hi = %s, newLeftHi = %s, majority = %s\n", left.lo, left.hi, newLeftHi, majority);
            if (newLeftHi < num.length 
                && num[newLeftHi] == num[left.hi])
                left.hi = newLeftHi;
            if (left.hi+1-left.lo > majority)
                return left;
        }

        if (right != null) {
            int newRightLo = right.lo-(majority-(right.hi+1-right.lo))-1;
            System.out.format("merge right lo = %s, hi = %s, newRightLo = %s, majority = %s\n", right.lo, right.hi, newRightLo, majority);
            if (newRightLo >= 0 
                && num[newRightLo] == num[right.lo])
                right.lo = newRightLo;
            if (right.hi+1-right.lo > majority) 
                return right;
        }
    
        return null;
    }

    public static void main(String[] args) {
        String[] strNums = args[0].split(",");
        int[] num = new int[strNums.length];
        for (int i = 0; i < strNums.length; i++)
        num[i] = Integer.parseInt(strNums[i]);
        System.out.println(String.format("majority = %s", new Solution169().majorityElement(num)));
    }
}
