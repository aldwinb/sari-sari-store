package com.leetcode;

public class Solution115 {
    public int numDistinct(String S, String T) {

        
        arrd
        farrrnjard
      a 0111111222
      r   12333355
      r    1222233
      d     000003

        _arrr__ard
        0111111222
          01333366
             00006
        
        ard
        farnjard

        arrrjard
        ard

        if (T.length() > S.length()) return 0;
       
        int[] subs = subseqs(T.charAt(0), S),
            dp = new int[subs.length];
        Arrays.fill(dp, 0);

        int n = 0;
        for (int i = 0; i < T.length(); i += n) {
            n = consec(T, i);
            for (int j = 0; j < subs.length; j++) {
            /*    
            for (int k = i; k < S.length(); k++) {
                if (T.charAt(i) == S.charAt(k)) r++;
                dp[k] *= r;
            }
            */
                if (dp[j] > 0) {
                    int c = ncr(n, matches(T.charAt(i), S.substring(subs[j]), subs[j]+i));
                    if (c > dp[j]) dp[j] = c;
                }
            }
        }

        int min = 0;
        for (int i = 0; i < dp.length; i++)
            min += dp[i];
        return min;
    }

    private int[] subseqs(char c1, String S) {
        Set<Integer> set = new HashSet<Integer>();
        int prev = c1;
        for (int i = 0; i < S.length(); i++)
            if (c1 == S.charAt(i)) 
            
    }

    private int matches(char c, String S, int s) {
        int r = 0; 
        for (int k = s; k <= S.length(); k++)
            if (c == S.charAt(k)) r++;
        return r;
    }

    private int consec(String T, int s) {
        char c = T.charAt(s);
        int i = s+1;
        for (; i < T.length() && c == T.charAt(i); i++);
        return i-s;
    }

    private int ncr(int n, int r) {
      /*
            1.2.3.4.5

            1.(4.3.2.1) 5 / 1
            1.2.(3.2.1) 5.4 / 1.2
            1.2.3.(2.1) 5.4.3 / 1.2.3
            1.2.3.4.(1)
            1.2.3.4.5.()
            */
        if (r > n || r == 0) return 0;
        int np = 1,
            rp = 1;
        for (int i = n, j = 1; j <= r; i--, j++) {
            np *= n;
            rp *= j;
        }
        return np/rp;
    }
}
