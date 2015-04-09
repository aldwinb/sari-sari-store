package com.leetcode;

import java.util.*;

public class Solution115 {
    public int numDistinct(String S, String T) {

        /*
        arrd
         fayr2njar1d
      a  0111 1122 2
      r2 0001 1115 5
      d     000003

         fayrrnjard_
       a 0111111222
       r 0001222244
       r 0000111133
       d 0000000003

        _arrr__ard
        0111111222
          01333366
             00006
        
        ard
        farnjard

        arrrjard
        ard
        */
        if (T.length() > S.length()) return 0;
        int[] dp1 = new int[S.length()+1],
            dp2 = new int[S.length()+1];
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 0);
        for (int i = 0; i < T.length(); i++) {
            for (int j = 1; j <= S.length(); j++)
                if (T.charAt(i) == S.charAt(j))
                    dp2[j] = dp1[j-1]+dp2[j-1];
                else
                    dp2[j] = dp2[j-1];
            dp1 = dp2;
        }

        return dp1[S.length()];
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Solution115 soln = new Solution115();
        while (s.hasNextLine()) {
            String[] testCase = s.nextLine().split("\\t");
            String[] input = testCase[0].split(",");
            int expected = Integer.parseInt(testCase[1]),
                actual = soln.numDistinct(input[0], input[1]);
            if (expected != actual)
                System.out.format("Test case = %s, expected = %s, actual = %s",
                    testCase[0],
                    expected,
                    actual);
        }
    }
}
