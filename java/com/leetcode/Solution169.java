package com.leetcode;

import java.util.*;

public class Solution169 {

    // 2 4 4
    // 1 3 3 3
    // 1 3 3 3 4
    // 1 1 2 3 3 3 4 4 5 6 7
    public int majorityElement(int[] num) {
        if (num.length == 1) return num[0];
        int half = num.length/2;
        Arrays.sort(num);
        if (num[0] == num[Math.min(half,num.length-1)]) return num[0];
        if (num[Math.max(num.length-1-half,0)] == num[num.length-1]) 
            return num[num.length-1];
        for (int i = 1; i < half-1; i++)
            if (num[i] == num[i+half])
                return num[i];
        return -1;
    }

    public static void main(String[] args) {
        String[] strNums = args[0].split(",");
        int[] num = new int[strNums.length];
        for (int i = 0; i < strNums.length; i++)
        num[i] = Integer.parseInt(strNums[i]);
        System.out.println(String.format("majority = %s", new Solution169().majorityElement(num)));
    }
}
