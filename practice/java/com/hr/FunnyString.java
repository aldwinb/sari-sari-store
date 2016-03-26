package com.hr;

import java.util.*;

public class FunnyString {
    public String funny(String S) {
        if (S.length() == 1) return "NOT FUNNY";
        if (S.length() == 2) return "FUNNY";
        int n = S.length();
        for (int i = 1, j = n-2; i <= j; i++, j--)
            if (Math.abs(S.charAt(i)-S.charAt(i-1)) !=
                Math.abs(S.charAt(j)-S.charAt(j+1)))
                return "NOT FUNNY";
        return "FUNNY";
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        FunnyString fs = new FunnyString();
        int ctr = 0;
        while (s.hasNextLine()) {
            ctr++;
            String[] test = s.nextLine().split("\\t");
            String actual = fs.funny(test[0]);
            if (!actual.equals(test[1]))
                System.out.format("Test %s failed:" +
                    "\texpected\t%s\tactual\t%s\n", ctr, test[1], actual);
        }
    }
}
