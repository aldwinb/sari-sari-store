package com.leetcode;

import java.util.*;

public class Solution22 {
    public List<String> generateParenthesis(int n) {
        List<String> parens = new ArrayList<String>();
        if (n == 0) return parens;
        for (String p : generate(n-1, n))
            parens.add("(" + p);
        return parens;
    }

    private Set<String> generate(int o, int c) {
        Set<String> parens = new HashSet<String>();
        if (o == 0 && c == 1) {
            parens.add(")");
            return parens;
        }
       
        Set<String> addlParens = null;
        if (o > 0) {
            addlParens = generate(o-1, c);
            for (String ap : addlParens)
                parens.add("(" + ap);
        }

        if (c > o) {
            addlParens = generate(o, c-1);
            for (String ap : addlParens)
                parens.add(")" + ap);
        }

        return parens;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String[] testCase = s.nextLine().split("\\t");
            String[] expected = testCase[1].split(",");
            List<String> actual = new Solution22().generateParenthesis(
                Integer.parseInt(testCase[0]));

            boolean br = false;
            if (expected.length != actual.size()) {
                System.out.format("%s\texpected=%s\tactual=%s\n",
                    testCase[0],
                    stringify(expected),
                    stringify(actual.toArray(new String[0])));
                br = true;
            }

            if (br) break;

            for (String e: expected) {
                if (!actual.contains(e))
                    System.out.format("%s\texpected=%s\tactual=%s\n",
                        testCase[0],
                        stringify(expected),
                        stringify(actual.toArray(new String[0])));
                br = true;
            }

            if (br) break;
        }
    }

    private static String stringify(String[] iter) {
        StringBuilder sb = new StringBuilder();
        for (String i : iter)
            sb.append(i).append(",");
        return sb.deleteCharAt(sb.length()-1).toString();
    }
}
