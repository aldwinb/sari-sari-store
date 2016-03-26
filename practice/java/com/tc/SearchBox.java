package com.tc;

public class SearchBox {
    public int find(String text, String search, String wholeWord, int start) {
        if (text == null || text.length() == 0) return -1;
        if (search == null || search.length() == 0) return -1;
        if (search.length() > text.length()) return -1;
       

        return kmp(text, search, wholeWord.equals("Y"), start, dfa(search)); 
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
                    if (!exact) return i-j;
                    if ((i == n || text.charAt(i) == ' ')
                        && (i-j == 0 || text.charAt(i-j-1) == ' '))
                        return i-j;
                    j = 0;
                }
            } else if (j > 0) j = dfa[j];
            else i++;
        }
    }

    private int[] dfa(String s) {
        int[] dfa = new int[s.length()+1];
        dfa[0] = dfa[1] = 0;
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

    public static void main(String[] args) {
        System.out.println(new SearchBox().find(
            args[0].replace(","," "), 
            args[1], 
            args[2], 
            Integer.parseInt(args[3]))); 
    }
}
