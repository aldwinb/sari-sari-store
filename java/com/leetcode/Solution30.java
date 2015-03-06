package com.leetcode;

import java.util.*;

public class Solution30 {
    public List<Integer> findSubstring(String S, String[] L) {
        if (L == null || L.length == 0
            || S == null || S.length() == 0) return new ArrayList<Integer>();
  
        Map<String, Integer> c = getCounts(L);
        int n = L[0].length();
        List<Integer> idxs = new ArrayList<Integer>();
        for (int i = 0; i+L.length <= S.length(); i++) {
            Queue<String> q = queueMatches(S,i,n,c);
            //System.out.format("q.size = %s\n", q.size());
            if (q.size() == L.length) idxs.add(i);
            putBack(q,c);
        }
        return idxs;
    }

    private void putBack(Queue<String> q, Map<String,Integer> c) {
        while (!q.isEmpty()) {
            String s2 = q.poll();
            c.put(s2, c.remove(s2)+1);
        }
    }

    private Queue<String> queueMatches(String S, 
        int i, 
        int n,
        Map<String,Integer> c) {
        Queue<String> q = new LinkedList<String>();
        int j = i,
            m = S.length();
        if (j+n > m) return q;
        String s1 = S.substring(j,j+n);
        while (c.containsKey(s1) && c.get(s1) > 0) {
            q.add(s1);
            c.put(s1, c.remove(s1)-1);
            j += n;
            //System.out.format("j = %s\n", j);
            if (j+n > m) break;
            s1 = S.substring(j,j+n);
        }
        return q;
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
