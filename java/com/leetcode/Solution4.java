package com.leetcode;

import java.util.*;

public class Solution4 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        
        char[] c = s.toCharArray();
        Queue<Character> cq = new ArrayDeque<Character>();
        int max = 1;
            
        cq.add(c[0]);
        for (int i = 1; i < c.length; i++) {
            if (cq.contains(c[i])) {
                max = Math.max(cq.size(), max);
                while (cq.poll() != c[i]) continue;
                //System.out.println(String.format("cq.size = %s", cq.size()));
            }
            cq.add(c[i]);
        }
        
        return Math.max(cq.size(), max);
    }
    
    public static void main(String[] args) {
        System.out.println(String.format("length = %s", 
            new Solution4().lengthOfLongestSubstring(args[0])));
    }
}