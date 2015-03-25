package com.tc;

public class SortEstimate {
    public double howMany(int c, int time) {
        // c*n*(log(n)/log(2)) <= time
        // n*log(n) / log(2) <= time / c
        // n*
        double x = time / (c*1.0),
            err = Math.pow(1.0,-9.0);
        //return search(x);
        double lo = 0, hi = x, n = 0;
        // x = 200
        // 100
        // => 100 + (200-100 / 2 = 50) = 150
        // => 150 + (200-150 / 2 = 25) = 175
        //

        while (hi > lo) {
            n = (hi+lo)/2;
            double m = n*(Math.log(n)/Math.log(2));
            System.out.format("lo = %s, hi = %s, n = %s, m = %s\n", lo, hi, n, m);
            if (x == m)
                //|| x == m+err || m-err == x)
                return n;
            if (x < m) hi = n;
            else lo = n+1;
        }

        return n; 
    }

    public static void main(String[] args) {
        System.out.format("estimate = %s\n", 
            new SortEstimate().howMany(Integer.parseInt(args[0]),
                Integer.parseInt(args[1])));
    }
}
