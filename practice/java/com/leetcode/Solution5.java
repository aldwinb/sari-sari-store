package com.leetcode;

import java.util.*;

public class Solution5 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode curr = null,
            prev = null,
            head = null;
        int co = 0;
        
        if (l2 == null) return l1;
        if (l1 == null) return l2;
        while (!(l1 == null && l2 == null && co == 0)) {
            int n1 = l1 != null ? l1.val : 0,
                n2 = l2 != null ? l2.val : 0;
            int val = co+n1+n2;
            prev = curr;
            curr = new ListNode(val % 10);
            if (head == null) head = curr;
            if (prev != null) prev.next = curr;
            co = val / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        
        return head;
    }
    
    public static void main(String[] args) {
        ListNode node = new Solution5().addTwoNumbers(convert(args[0]),
            convert(args[1]));
        if (node == null) return;
        System.out.print(node.val);
        node = node.next;
        while (node != null) {
            System.out.print(String.format("->%s", node.val));
            node = node.next;
        }
        System.out.println();
    }
    
    private static ListNode convert(String s) {
        if (s.length() == 0) return null;
        Integer n = Integer.parseInt(s);
        char[] charArr = n.toString().toCharArray();
        ListNode node = new ListNode(Integer.parseInt(String.valueOf(charArr[charArr.length-1])));
        ListNode tmp = node;
        for (int i = charArr.length-2; i >= 0; i--) {
            tmp.next = new ListNode(Integer.parseInt(String.valueOf(charArr[i])));
            tmp = tmp.next;
        }
        return node;
    }
}