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

    private Candidate findSemiMajority(int[] num, int lo, int hi) {
        if (lo == hi) return new Candidate(lo,lo);
        int s = 0, e = 0, j = 0, i = lo+1;
        for (; i <= hi; i++)
            if (num[i-1] != num[i]) {
                if (e+1-s < i-j) {
                    s = j;
                    e = i-1;
                }
                j = i;
            }
        if (e+1-s < i-j) return new Candidate(j,i-1);
        return new Candidate(s,e);
    }

    private Candidate findMajority(int[] num, int lo, int hi, int partition) {
        System.out.format("lo = %s, hi = %s, hi/lo = %s, x = %s, num[lo] = %s\n", 
                lo, 
                hi, 
                (hi+1-lo)/partition,
                (hi+1-lo)%partition,
                num[lo]);
        if (hi+1-lo < partition)
            return findSemiMajority(num,lo,hi);
            /*
            
            if (lo == hi)
                return new Candidate(lo, lo);
            */
        //int majLimit = (int)Math.round(((hi+1-lo)/(partition*1.0)));
        int n = hi+1-lo,
            jump = n / partition,
            xJump = n % partition;
        Candidate[] parts = new Candidate[partition];
        int k = 0;
        for (int i = 0, j = lo; i < partition; i++, j += k-j) {
            k = j+jump+xJump;
            //System.out.format("k = %s\n", k);
            parts[i] = findMajority(num,j,Math.min(k-1,hi),partition);
            if (xJump > 0) xJump--;
            /*
            if (parts[i] != null)
                System.out.format("lo = %s, hi = %s, majLimit = %s\n", parts[i].lo, parts[i].hi, jump);
            */
        }
        
        for (int i = 0; i < parts.length; i++) {
            //if (parts[i] != null)
            //    System.out.format("merge lo = %s, p.hi = %s\n", parts[i].lo, parts[i].hi);
            if (parts[i] == null) continue;
            Candidate maj = parts[i];
            expand(maj, num, jump, lo, hi);
            if (maj.hi+1-maj.lo > jump) return maj;
            
            /*
            if (majority != null)
                System.out.format("major lo = %s, hi = %s\n", majority.lo, majority.hi);
            else
                System.out.println("majority null");
            if (majority != null) return majority;
            */

        }
            //if (majority != null)
            //    System.out.format("major lo = %s, hi = %s\n", majority.lo, majority.hi);
            //else
        //    System.out.println("majority null");

        //if (majority != null && majority.hi+1-majority.lo > jump)
        //    return majority;
        return null;
    }

    /*
    private Candidate choose(
        Candidate left, 
        Candidate right,  
        int majority)
            if (left != null && left.hi+1-left.lo > majority) 
                return left;
            if (right != null && right.hi+1-right.lo > majority) 
                return right;
            return null;
            //if (left.hi-left.lo > right.hi-right.lo) return left;
            //return right;
    }
    */

    private void expand(Candidate cand, 
        int[] num, 
        int majority, 
        int lo, 
        int hi) {
        if (cand == null) return;
        int newHi = Math.min(cand.hi+majority-(cand.hi+1-cand.lo)+1,hi);
        if (num[newHi] == num[cand.hi])
            cand.hi = newHi;
        int newLo = Math.max(cand.lo-(majority-(cand.hi+1-cand.lo))-1,lo);
        if (num[newLo] == num[cand.lo])
            cand.lo = newLo;
    }

    public static void main(String[] args) {
        String[] strNums = args[0].split(",");
        int[] num = new int[strNums.length];
        for (int i = 0; i < strNums.length; i++)
        num[i] = Integer.parseInt(strNums[i]);
        System.out.println(String.format("majority = %s", new Solution169().majorityElement(num)));
    }
}
