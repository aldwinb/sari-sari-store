package com.tc;

public class SortEstimate {
    public double howMany(int c, int time) {
        double lo = 1, 
            hi = getHi(c,time), 
            n = 0;

        for (int i = 0; i < 50 && hi > lo; i++) {
            n = (hi+lo)/2;
            if (n == lo) return lo;
            if (n == hi) return hi;
            double m = getTime(c,n);
            if (time < m) hi = n;
            else if (time > m) lo = n;
            else return n;
        }

        return n; 
    }

    private double getHi(int c, int time) {
        double lo = 1;
        while (getTime(c, lo) < time)
            lo *= 2;
        return lo;
    }

    private double getTime(int c, double x) {
        return c*x*(Math.log(x)/Math.log(2));
    }

    public static void main(String[] args) {
        System.out.format("estimate = %s\n", 
            new SortEstimate().howMany(Integer.parseInt(args[0]),
                Integer.parseInt(args[1])));
    }
}
