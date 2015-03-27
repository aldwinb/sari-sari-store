package com.leetcode;

public class Solution148 {
    public ListNode sortList(ListNode head) {
        if (head == null) return head;
        return sort(head);
    }

    // 2->4->1->5->3
    // 2->1->3, 4->5
    // 2->1, 3
    // 1->2, 3
    // 1->2->3, 4->5
    //
    private ListNode sort(ListNode head) {
        if (head.next == null) return head;
        ListNode less, lhead, ltail;
        lhead = less = ltail = head.val;
        head = head.next;
        while (head != null && head.val == less.val) {
            less.next = head;
            less = less.next;
            head = head.next;
        }
        lhead = less = ltail = head.val <= head.next.val ? head : head.next;
        mhead = more = head.val > head.next.val ? head : head.next;
        head = head.next.next;
        less.next = more.next = null;
        int mval = more.val;
        while (head != null) {
            if (head.val < mval) {
                if (ltail.val < head.val)
                    ltail = head;
                less.next = head;
                less = less.next;
            } else {
                System.out.format("more = %s\n", head.val);
                more.next = head;
                more = more.next;
            }
            head = head.next;
        }

        System.out.format("ltail = %s, lhead = %s, mhead = %s\n", ltail.val, lhead.val, mhead.val);
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
