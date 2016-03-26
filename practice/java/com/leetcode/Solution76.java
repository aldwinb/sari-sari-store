package com.leetcode;

import java.util.*;

public class Solution76 {
    public String minWindow(String S, String T) {
        if (S.length() == 0 || T.length() == 0) return "";

        int[] pt = patTbl(T),
            rpt = Arrays.copyOf(pt, pt.length),
            ptr2 = txtPtr(S, pt);

        int t = T.length(), j = 1, s = 0, e = Integer.MAX_VALUE;
        for (int i = 0; i < ptr2.length; i++) {
            char c = S.charAt(ptr2[i]);
            if (rpt[c] > 0) { 
                rpt[c]--;
                if (i-(j-1)+1 == t) {
                    if (e-s > (ptr2[i]+1)-ptr2[j-1]) {
                        e = (ptr2[i]+1); s = ptr2[j-1];
                    }
                    rpt = Arrays.copyOf(pt, pt.length);
                    i = j-1;
                    j = i+2;
                }
            } else { 
                rpt = Arrays.copyOf(pt, pt.length);
                i = j-1;
                j = i+2;
            }
        }

        if (e == Integer.MAX_VALUE) return "";
        return S.substring(s, e);
    }

    private int[] txtPtr(String S, int[] pt) {
        List<Integer> ptr = new ArrayList<Integer>();
        for (int i = 0; i < S.length(); i++)
            if (pt[S.charAt(i)] != -1)
                ptr.add(i);

        int[] ptrArr = new int[ptr.size()];
            for (int i = 0; i < ptr.size(); i++)
                ptrArr[i] = ptr.get(i);
        return ptrArr;
    }

    private int[] patTbl(String T) {
        int[] alpha = new int[256];
        for (int i = 0; i < 256; i++)
            alpha[i] = -1;

        for (int i = 0; i < T.length(); i++) {
            if (alpha[T.charAt(i)] == -1)
                alpha[T.charAt(i)] = 0;
            alpha[T.charAt(i)]++;
        }
        return alpha;
    }

    public static void main(String[] args) {
        System.out.println(String.format("window = %s", 
            new Solution76().minWindow(args[0], args[1])));    
    }
}
