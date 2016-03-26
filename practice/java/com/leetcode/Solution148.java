package com.leetcode;

public class Solution148 {
    public ListNode sortList(ListNode head) {
        if (head == null) return head;
        return sort(head);
    }

    private ListNode sort(ListNode head) {
        if (head.next == null) return head;
        ListNode less, lhead, ltail;
        lhead = less = head;
        head = head.next;
        while (head != null && head.val == less.val) {
            less.next = head;
            less = less.next;
            head = head.next;
        }
        less.next = null;
        if (head == null) return lhead;

        ListNode more, mhead;
        if (lhead.val < head.val) {
            more = mhead = head;
            ltail = less; 
        } else {
            more = less;
            mhead = lhead;
            less = lhead = ltail = head;
        }
        head = head.next;

        int mval = more.val;
        while (head != null) {
            if (head.val < mval) {
                if (ltail.val <= head.val)
                    ltail = head;
                less.next = head;
                less = less.next;
            } else {
                more.next = head;
                more = more.next;
            }
            head = head.next;
        }

        less.next = more.next = null;
        lhead = sort(lhead);
        mhead = sort(mhead);
        ltail.next = mhead;
        return lhead;
    }

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
