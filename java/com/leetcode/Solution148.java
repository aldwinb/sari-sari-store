package com.leetcode;

public class Solution148 {
    public ListNode sortList(ListNode head) {
        if (head == null) return head;
        return sort(head);    
    }

    private void sort(ListNode head) {
        if (head.next == null) return head;
        
        ListNode sntnl = head, 
            n1 = head, 
            n2 = head.next;
        head = n2.next;
        int i = 0;
        while (head != null) {
            if (i++ % 2 == 0) {
                n1.next = head;
                n1 = n1.next;
            }
            else {
                n2.next = head;
                n2 = n2.next;
            }
            head = head.next;
        }

        sort(n1);
        sort(n2);
        merge(n1,n2);
        head = n1;
    }

    private void merge(ListNode n1, ListNode n2) {
        if (n1 == null) {
            n1 = n2;
            return;
        }
        if (n2 == null) return;
        
        Node sntnl
        while (n1 != null && n2 != null)
            if (
    }

    /*
    private ListNode sort(ListNode head) {
        if (head.next == null) return head;
        ListNode sorted = sort(head.next);
        //System.out.format("head = %s, sorted = %s\n", head.val, sorted.val);
        if (head.val <= sorted.val) {
            head.next = sorted;
            return head;
        }
        head.next = null;
        ListNode sntnl = sorted;
        while (sorted.next != null && sorted.next.val < head.val)
            sorted = sorted.next;
        
        ListNode temp = sorted.next; 
        sorted.next = head;
        head.next = temp;
        return sntnl;
    }
    */

    public static void main(String[] args) {
        String[] ints = args[0].split(",");
        ListNode head = new ListNode(Integer.parseInt(ints[0])),
            curr = head;
        for (int i = 1; i < ints.length; i++) {
            curr.next = new ListNode(Integer.parseInt(ints[i]));
            curr = curr.next;
        }
        head = new Solution148().sortList(head);
        while (head != null) {
            System.out.format("%s,", head.val);
            head = head.next;
        }
        System.out.println("");
    }
}
