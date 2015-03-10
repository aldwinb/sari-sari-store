package com.tc;

public class SearchBox {
    public int find(String text, String search, String wholeWord, int start) {
    
    }

    private int[] kmp(String s) {
        int[] kmp = new kmp[s.length()+1];
        kmp[0] = kmp[1] = 0;

        for (int i = 2; i < s.length(); i++) {
            int j = kmp[i-1];
            for (;;) {
                if (s.charAt(j) == s.charAt(i)) {
                    kmp[i] = j+1; 
                }

                if (j > 0) j = kmp[j];
                i++;
            }
        }
    }
}
