package com.tc;

import java.util.*;

public class Jewelry {
    private class Group {
        public int key;
        public int size;
        public Group(int key, int size) {
            this.key = key;
            this.size = size;
        }
    }
    
    public long howMany(int[] values) {
        int N = values.length;
        int max = 0;
        Arrays.sort(values);
        for (int i = 0; i < N-1; i++)
            max += values[i];
        
        List<Group> G = groups(values);

        int[] waysLo = new int[max+1],
            waysHi = new int[max+1];
        Arrays.fill(waysLo, 0);
        Arrays.fill(waysHi, 0);
        waysLo[0] = waysHi[0] = 1;

        for (Group g : G)
            addWays(waysHi, g);

        /*
        System.out.print("fu =");
        for (int j = 1; j < waysHi.length; j++)
            System.out.print(String.format(" %s", waysHi[j] == 0 ? "." : waysHi[j]));
        System.out.println("");
        System.out.println("");
        */
        long count = 0;
        for (Group g : G) {
            subWays(waysHi, g);
            long c1 = 1,
                c2 = 1;
            for (int i = 1; i <= g.size; i++) {

                
                System.out.print("c  =");
                for (int j = 1; j < waysLo.length; j++)
                    System.out.print(String.format(" %s", waysLo[j] == 0 ? "." : waysLo[j]));
                System.out.println("");
                System.out.print("hi =");
                for (int j = 1; j < waysHi.length; j++)
                    System.out.print(String.format(" %s", waysHi[j] == 0 ? "." : waysHi[j]));
                System.out.println("");
                

                int elem = g.key*i;
                if (c2 != 0) {
                    c1 = combo(g.size-i, i);
                    c2 = combo(c2, g.size-i+1, i);
                    //System.out.println(String.format("g.size-i+1 = %s, i = %s", g.size-i+1, i));
                    count += c1*c2;
                    // System.out.println(String.format("elem = %s, count(1) = %s", elem, count));
                }
                for (int j = elem; j <= max; j++) {
                    System.out.println(String.format("elem = %s, c2 = %s, j = %s, waysLo[j] = %s, waysHi[j] = %s", elem, c2, j, waysLo[j-elem], waysHi[j]));
                    count += c2 * waysLo[j-elem] * waysHi[j];
                }
                System.out.println(String.format("count(2) = %s", count));
                
            }

            for (int i = 1; i <= g.size; i++)
                for (int j = waysLo.length-1; j >= g.key; j--)
                    waysLo[j] += waysLo[j-g.key];

            //System.out.println("");
        }

        return count;
    }

    private List<Group> groups(int[] values) {
        int size = 0,
            g = 0;
        List<Group> G = new ArrayList<Group>();
        for (g = 1; g < values.length; g++)
            if (values[g] != values[g-1]) {
                G.add(new Group(values[g-1], g-size));
                size = g;
            }
        G.add(new Group(values[g-1], g-size));
        return G;
    }

    private void addWays(int[] sums, Group g) {
        for (int i = 1; i <= g.size; i++)
            addWay(sums, g.key);
    }

    private void subWays(int[] sums, Group g) {
        for (int i = 1; i <= g.size; i++)
            subtractWay(sums, g.key);
    }

    private void addWay(int[] sums, int item) {
        for (int j = sums.length-1; j >= item; j--)
            sums[j] += sums[j-item];
    }

    private void subtractWay(int[] sums, int item) {
        for (int j = item; j < sums.length; j++)
            sums[j] -= sums[j-item];
    }
   
    private long combo(long ncr, int n, int r) {
        return (ncr*n)/r;
    }

    private long combo(int n, int r) {
        if (r > n) return 0;
        if (r == n) return 1;
        long q = n;
        for (int j = 2, k = n-1; j <= r && k > r; j++, k--)
            q = (q*k)/j;
        return q;
    }
       
    public static void main(String[] args) {
        String[] s1 = args[0].split(",");
        int[] values = new int[s1.length];
        for (int i = 0; i < s1.length; i++) values[i] = Integer.parseInt(s1[i]);
        long count = new Jewelry().howMany(values);
        System.out.println(String.format("howMany = %s", count));
    }
    
}
