package com.leetcode;

import java.util.*;

public class Solution17 {

    private String[] words = new String[] { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" }; 
    
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return new ArrayList<String>();
        List<String> combos = new ArrayList<String>();
        String word = words[digits.charAt(0)-48];
        for (int i = 0; i < word.length(); i++)
            combos.add(String.valueOf(word.charAt(i)));

        for (int i = 1; i < digits.length(); i++) {
            word = words[digits.charAt(i)-48];
            List<String> newCombos = new ArrayList<String>();
            for (int j = 0; j < word.length(); j++) {
                for (String c : combos) {
                    //System.out.format("new combo = %s\n",c+String.valueOf(word.charAt(j)));
                    newCombos.add(c+String.valueOf(word.charAt(j)));
                }
            }
            combos.clear();
            combos.addAll(newCombos);
        }

        return combos;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String[] testCase = s.nextLine().split("\\t");
            List<String> actual = 
                new Solution17().letterCombinations(testCase[0]);
            String[] expected = testCase[1].split(",");
            Collections.sort(actual);
            Arrays.sort(expected);
            boolean br = false;
            if (expected.length != actual.size()) {
                System.out.format("%s\texpected=%s\tactual=%s\n",
                    testCase[0],
                    testCase[1],
                    stringify(actual));
                br = true;
            }

            if (br)
                break;

            for (int i = 0; i < expected.length; i++) {
                if (!expected[i].equals(actual.get(i))) {
                    System.out.format("%s\texpected=%s\tactual=%s\n",
                        testCase[0],
                        testCase[1],
                        stringify(actual));
                    br = true;
                    break;
                }
            }

            if (br)
                break;
        }
    }

    private static String stringify(List<String> words) {
        StringBuilder sb = new StringBuilder();
        for (String w : words)
            sb.append(w).append(",");
        return sb.deleteCharAt(sb.length()-1).toString();
    }
}
