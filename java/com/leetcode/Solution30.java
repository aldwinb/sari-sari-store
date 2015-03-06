package com.leetcode;

import java.util.*;

public class Solution30 {
    public List<Integer> findSubstring(String S, String[] L) {
        if (L == null || L.length == 0
            || S == null || S.length() == 0) return new ArrayList<Integer>();
  
        Map<String, Integer> c = getCounts(L);
        int n = L[0].length();
        List<Integer> idxs = new ArrayList<Integer>();
        Queue<String> q = new LinkedList<String>();
        for (int i = 0; i+L.length <= S.length(); i++) {
            Queue<String> q = queueMatches(S,i,c);
            if (q.size() == L.length) idxs.add(i);
            putBack(q,c);
            /*
            while (!q.isEmpty()) {
                String s2 = q.poll();
                c.put(s2, c.remove(s2)+1);
            }
            */
        }
        
        return idxs;
    }

    private Queue<String> queueMatches(String S, int i, Map<String,Integer> c) {
            /*
            String s1 = S.substring(i,i+n);
            //System.out.format("s1 = %s\n", s1);
             
            if (c.containsKey(s1) 
            int j = i+n;
            while (c.containsKey(s1) && c.get(s1) > 0) {
                q.add(s1);
                c.put(s1, c.remove(s1)-1);
                if (j+n < S.length()) {
                    s1 = S.substring(j,j+n);
                    j += n;
                } else s1 = null;
            }ri
            */
    }

    private Map<String, Integer> getCounts(String[] L) {
        Map<String, Integer> c = new HashMap<String, Integer>();
        for (String l : L) {
            if (!c.containsKey(l))
                c.put(l,0);
            c.put(l, c.remove(l)+1);
        }
        return c;
    }

    public static void main(String[] args) {
        List<Integer> idxs = new Solution30().findSubstring(args[0], args[1].split(","));
        if (idxs.size() == 0) return;
        for (int i : idxs)
            System.out.format("%s,", i);
        System.out.println("");
    }
}
