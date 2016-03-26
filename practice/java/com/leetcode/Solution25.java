package com.leetcode;

public class Solution25 {
    ListNode prev,
        curr,
        next,
        tail,
        khead; 
    int ctr;

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1 || head == null) return head;

        ctr = 1;
        khead = tail = curr = head;
        next = curr.next;
        while (ctr < k && next != null) {
            move();
            ctr++;
            switchLink();
        }

        if (ctr == k) head = curr;
        if (next != null) {
            move();
            ctr++;
            kheadTo(curr);
        }
        
        while (next != null) {
            move();
            ctr++;
            if (ctr % k != 1) switchLink();
            else {
                tailNextTo(prev); 
                kheadTo(curr);
            }
        }

        if (ctr % k != 0) {
            kheadTo(curr);
            next = prev;
            while (ctr % k != 1) {
                move();
                ctr--;
                switchLink();
            }
        }
        tailNextTo(curr);
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

    private void tailNextTo(ListNode n) {
        if (tail != n) tail.next = n;
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
        if (s.length() == 0) return null;
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
