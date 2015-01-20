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
            addWays(waysLo, g);
            subWays(waysHi, g);
            
            System.out.print("lo =");
            for (int j = 1; j < waysLo.length; j++)
                System.out.print(String.format(" %s", waysLo[j] == 0 ? "." : waysLo[j]));
            System.out.println("");
            System.out.print("hi =");
            for (int j = 1; j < waysHi.length; j++)
                System.out.print(String.format(" %s", waysHi[j] == 0 ? "." : waysHi[j]));
            System.out.println("");
            
            for (int i = 1; i <= g.size; i++) {
                int elem = g.key*i;
                long c = combo(g.size, elem);
                count += combo(g.size-i, elem) * c;
                System.out.println(String.format("elem = %s, count(1) = %s", elem, count));
                for (int j = elem; j <= max; j++)
                    count += waysLo[j] * waysHi[j];
                System.out.println(String.format("elem = %s, count(2) = %s", elem, count));
            }
            System.out.println("");
        }

        return count;
    }

    private List<Group> groups(int[] values) {
        int size = 0,
            g = 0;
        List<Group> G = new ArrayList<Group>();
        for (g = 1; g < values.length; g++) {
            if (values[g] != values[g-1]) {
                G.add(new Group(values[g-1], g-size));
                size = g;
            }
        }
        G.add(new Group(values[g-1], g-size));
        return G;
    }

    private void addWays(int[] sums, Group g) {
        for (int i = 1; i <= g.size; i++)
            addWay(sums, g.key);
            // System.out.println(String.format("key = %s, size = %s", g.key, g.size));
            // for (int i = 1; i <= g.size; i++)
            //    addWay(sums, g.key*i); 
    }

    private void subWays(int[] sums, Group g) {
        for (int i = 1; i <= g.size; i++)
            subtractWay(sums, g.key);
    }

    private void addWay(int[] sums, int item) {
        for (int j = sums.length-1; j >= item; j--)
            sums[j] += sums[j-item];
        // System.out.print("fu =");
        // for (int j = 1; j < sums.length; j++)
        //     System.out.print(String.format(" %s", sums[j] == 0 ? "." : sums[j]));
        // System.out.println("");
        // System.out.println("");
    }

    private void subtractWay(int[] sums, int item) {
        for (int j = item; j < sums.length; j++)
            sums[j] -= sums[j-item];
    }
   
    private long combo(int n, int r) {
        long num = 1,
            den = 1;
        if (r > n) return 0;
        for (int j = 1, k = n; j <= r && k > r; j++, k--) {
            num *= k; den *= j;
        }
        return num / den;
    }
       
    public static void main(String[] args) {
        String[] s1 = args[0].split(",");
        int[] values = new int[s1.length];
        for (int i = 0; i < s1.length; i++) values[i] = Integer.parseInt(s1[i]);
        long count = new Jewelry().howMany(values);
        System.out.println(String.format("howMany = %s", count));
    }
    
}
