package com.tc;

public class SearchBox {
    public int find(String text, String search, String wholeWord, int start) {
        if (text == null || text.length() == 0) return -1;
        if (search == null || search.length() == 0) return -1;
        if (search.length() > text.length()) return -1;
        
        boolean exact = wholeWord == "Y";
        int j = 0,
            m == search.length();
        for (int i = start, j = 0; i+m < text.length() && j < m; i++, j++)
            if (text.charAt(i) != search.charAt(j))
    }
   
    private int kmp(String text, String search, boolean exact, int start, int[] dfa) {
        int i = start,
            j = 0,
            n = text.length(),
            m = search.length();
        for (;;) {
            if (i == n) return -1;
            if (text.charAt(i) == search.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    if ((i == n-1 || text.charAt(i+1) == ' ')
                        && (i-j == 0 || text.charAt(i-j) == ' ')
                        && exact)
                        return i;
                    else j = 0;
                }
            } else if (i > 0) i = dfa[i];
            else j++;
        }
    }

    private int[] dfa(String s) {
        int[] dfa = new dfa[s.length()+1];
        dfa[0] = dfa[1] = 0;
        // ababac
        for (int i = 2; i < s.length(); i++) {
            int j = dfa[i-1];
            for (;;) {
                if (s.charAt(j) == s.charAt(i-1)) {
                    dfa[i] = j+1;
                    break;
                }
                if (j == 0) {
                    dfa[i] = 0;
                    break;
                }
                j = dfa[j];
            }
        }
        return dfa;
    }
}
