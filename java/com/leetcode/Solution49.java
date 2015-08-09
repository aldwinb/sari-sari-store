package com.leetcode;

import java.util.*;

public class Solution49 {
    public List<String> anagrams(String[] strs) {
        if (strs == null || strs.length <= 1) return new ArrayList<String>();
                    
        Map<String, List<String>> anaMap = new HashMap<String, List<String>>();
        List<String> anagrams= new ArrayList<String>();
                                    
        for (int i = 0; i < strs.length; i++) {
            char[] cs = strs[i].toCharArray();
            Arrays.sort(cs);
            String s1 = String.valueOf(cs);
            
            if (!anaMap.containsKey(s1))
                anaMap.put(s1, new ArrayList<String>());
            anaMap.get(s1).add(strs[i]);
        }

        for (String a : anaMap.keySet()) {
            List<String> grams = anaMap.get(a);
            if (grams.size() > 1)
                anagrams.addAll(grams);
        }
                                                    
        return anagrams;
    }

    public static void main(String[] args) {
  
    } 
}
