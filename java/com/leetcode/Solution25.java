package com.leetcode;

public class Solution25 {
    ListNode prev,
        curr,
        next,
        tail,
        khead; 
    int ctr;

    public ListNode reverseKGroup(ListNode head, int k) {
        ctr = 1;
        tail = curr = head;
        next = curr.next;
        while (ctr < k) {
            move();
            ctr++;
            switchLink();
        }

        head = curr;
        kheadTo(next);

        while (next != null) {
            move();
            ctr++;
            if (ctr % k != 1) switchLink();
            else {
                if (ctr / k == 2) tailTo(prev); 
                kheadTo(curr);
            }
        }

        System.out.println(String.format("ctr = %s, curr = %s", ctr, curr.val));
        if (ctr % k != 0) {
            kheadTo(curr);
            next = prev;
            while (ctr % k != 1) {
                move();
                ctr--;
                switchLink();
                //System.out.println(String.format("ctr = %s, curr = %s, next = %s", ctr, curr.val, next.val));
            }
        }
        tailTo(curr);
        tail.next = null;

        return head;
    }
   
    private void move() {
        prev = curr;
        curr = next;
        next = curr.next;
    }

    private void switchLink() {
        curr.next = prev;
    }

    private void kheadTo(ListNode n) {
        khead = n; 
    }

    private void tailTo(ListNode n) {
        tail.next = n;
        tail = khead;
        ctr = 1;
    }

    public static void main(String[] args) {
        ListNode node = new Solution25().reverseKGroup(convert(args[0]),
            Integer.parseInt(args[1]));
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
        String[] strArr = s.split(",");
        ListNode node = new ListNode(Integer.parseInt(String.valueOf(strArr[0])));
        ListNode tmp = node;
        for (int i = 1; i < strArr.length; i++) {
            tmp.next = new ListNode(Integer.parseInt(String.valueOf(strArr[i])));
            tmp = tmp.next;
        }
        return node;
    }
}
