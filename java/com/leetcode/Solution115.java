package com.leetcode;

import java.util.*;

public class Solution115 {
    public int numDistinct(String S, String T) {
        if (T.length() > S.length()) return 0;
        int[] dp1 = new int[S.length()+1],
            dp2 = new int[S.length()+1];
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 0);
        for (int i = 0; i < T.length(); i++) {
            for (int j = 1; j <= S.length(); j++)
                if (T.charAt(i) == S.charAt(j-1))
                    dp2[j] = dp1[j-1]+dp2[j-1];
                else
                    dp2[j] = dp2[j-1];
            dp1 = dp2;
            dp2 = new int[S.length()+1];
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
                System.out.format(
                    "Test case = %s, expected = %s, actual = %s\n",
                    testCase[0],
                    expected,
                    actual);
        }
    }
}
