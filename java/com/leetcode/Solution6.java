package com.leetcode;

public class Solution6 {
	public String longestPalindrome(String s) {
		int i = 0,
			j = -1,
			start = 0,
			end = 0;
		boolean pal = false;
		
		if (s.length() == 1) return s;
		if (s.charAt(0) == s.charAt(1)) {
			j = 0; pal = true;
		}
		
		// 11222
		for (i = 2; i < s.length(); i++) {             
			if (pal) {
				if (j > 0 && s.charAt(i) == s.charAt( j-1)) j--;
				else  if (s.charAt(i) != s.charAt(j)) {
					if (i-j > end-start) { start = j; end = i; }
					j = -1;
					pal = false;
				}
			} else {
				if (s.charAt(i) == s.charAt(i-1)) {
					j = i-1; pal = true;
				} else if (s.charAt(i) == s.charAt(i-2)) {
					j = i-2; pal = true;  
				}
			}
		}
		
		//System.out.println(String.format("i = %s, j = %s, start = %s, end = %s", i, j, start, end));
		return j > -1 && i-j > end-start ? s.substring(j, i) : s.substring(start, end);
   	 }
    
   	 public static void main(String[] args) {
   	 	 System.out.println(new Solution6().longestPalindrome(args[0]));
   	 }
}
