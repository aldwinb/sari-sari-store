package com.tc;

public class SortEstimate {
    public double howMany(int c, int time) {
        double x = time / (c*1.0),
            err = Math.pow(1.0,-9.0);
        double lo = 0, hi = x, n = 0;

        while (hi > lo) {
            n = (hi+lo)/2;
            if (n == lo) return lo;
            double m = n*(Math.log(n)/Math.log(2));
            //System.out.format("lo = %s, hi = %s, n = %s, m = %s\n", lo, hi, n, m);
            if (x < m) hi = n;
            else if (x > m) lo = n;
            else return n;
        }

        return n; 
    }

    public static void main(String[] args) {
        System.out.format("estimate = %s\n", 
            new SortEstimate().howMany(Integer.parseInt(args[0]),
                Integer.parseInt(args[1])));
    }
}
