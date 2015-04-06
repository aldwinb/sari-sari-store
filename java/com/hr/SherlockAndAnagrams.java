package com.hr;

import java.util.*;
import java.util.regex.*;

public class SherlockAndAnagrams  {
    private class Node {
        char c;
        int val;
        Node left, mid, right;
        public Node(char c) {
            this.c = c;
            val = 0;
        }
    }

    private class TST {
        private Node root;

        public int searchOrAdd(char[] key) {
            int val = get(root, key, 0);
            if (val != 0)
                return val;
            root = add(root, key, 0);
                return 1;
        }

        private Node add(Node x, char[] key, int d) {
            char c = key[d];
            if (x == null) x = new Node(c);
            if (x.c < c) x.left = add(x.left, key, d);
            else if (x.c > c) x.right = add(x.right, key, d);
            else if (d < key.length-1) x.mid = add(x.mid, key, d+1);
            else x.val = 1;
            return x;
        }

        private int get(Node x, char[] key, int d) {
            char c = key[d];
            if (x == null) return 0;
            if (x.c < c) return get(x.left, key, d);
            if (x.c > c) return get(x.right, key, d);
            if (d < key.length-1) return get(x.mid, key, d+1);
            return ++x.val;
        }
    }

    public int countAnagramPairs(String S) {
        int n = S.length(),
            count = 0;
        if (n == 1) return 0;
        TST dict = null;
        for (int i = 1; i < n; i++) {
            dict = new TST();
            for (int j = 0; j <=i; j++) {
                char[] key = new char[n-i+1];
                for (int k = 0; k < n-i; k++)
                    key[k] = S.charAt(j+k);
                Arrays.sort(key);
                /*
                for (int p = 0; p < key.length; p++)
                    System.out.print(key[p]);
                System.out.println("");
                */
                int val = dict.searchOrAdd(key); 
                if (val%2==0) {
                    count++;
                    //System.out.format("count = %s\n", count);
                }
            }   
        }
        return count;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        SherlockAndAnagrams soln = new SherlockAndAnagrams();
        while (in.hasNextLine()) {
            String[] pairs = in.nextLine().split("\\t");
            int count = soln.countAnagramPairs(pairs[0]);
            if (count != Integer.parseInt(pairs[1])) {
                System.out.println(pairs[0]);
                System.out.format("expected\t%s\tactual\t%s\n", 
                    pairs[1], 
                    count);
            }
        }
    }
}
