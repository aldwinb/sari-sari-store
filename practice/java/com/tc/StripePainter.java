package com.tc;

import java.util.*;

public class StripePainter {
    // RGBGR 3
    // RGRG 3
    // ABACADA 4
    // AABBCCDDCCBBAABBCCDD 7
    public int minStrokes(String stripes) {
        if (stripes.length <= 1) return stripes.length;
        int[] cost = new int[stripes.length];
        Arrays.fill(cost,-1);
        cost[0] = 1;
        for (int i = 1; i < stripes.length; i++)
            if (stripes.charAt(i) == stripes.charAt(i-1))
                cost[i] = cost[i-1];
            else {
                for (int j = 1; j <= Math.max(; j++)
                    cost[i] = cost[i-1]+1;
                    if (stripes.charAt(i-j) != stripes.charAt(i))
            }
        
    }

    public static void main(String[] args) {
    
    }
}
