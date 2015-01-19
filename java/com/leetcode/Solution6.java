package com.leetcode;
   
import java.util.*;

public class Solution6 {
    private class Palindrome implements Comparable<Palindrome> {
        public int start;
        public int end;

        public Palindrome(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Palindrome p) {
            if (end-start > p.end-p.start) return -1;
            if (end-start == p.end-p.start) return 0;
            return 1;
        }
    }

	public String longestPalindrome(String s) {
		if (s.length() < 3) return s;

        int l = (s.length()/2)-1,
            r = l+1;
        Queue<Palindrome> q = new PriorityQueue<Palindrome>();
        Palindrome top = new Palindrome(0,0);

        while (l >= 1 || r <= s.length()-1) {
            q.add(search(s, l-1, l));
            q.add(search(s, l-2, l));
            q.add(search(s, r-1, r));
            q.add(search(s, r-2, r));
            top = q.peek();
            if (top.end-top.start == s.length()) break;

            l--; r++;
        }

        top = q.poll();
        return s.substring(top.start, top.end);
    }
 
    private Palindrome search(String s, int i, int j) {
        if (i < 0 || j >= s.length()) return new Palindrome(0,0);
        for (;i >= 0 && j <= s.length()-1 && s.charAt(i) == s.charAt(j); i--, j++) {}
        return new Palindrome(i+1,j);
    }
    
    public static void main(String[] args) {
        System.out.println(new Solution6().longestPalindrome(args[0]));
    }
}
