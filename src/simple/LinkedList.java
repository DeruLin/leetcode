package simple;

import java.util.List;

public class LinkedList {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
//        head1.next = new ListNode(2);
//        head1.next.next = new ListNode(2);
//        head1.next.next.next = new ListNode(1);
//        //head1.next.next.next.next = new ListNode(1);

        System.out.println(isPalindrome(head1));
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast;
        if (slow.next == null) return slow;
        else fast = slow.next;
        ListNode temp;
        do {
            temp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = temp;
        } while (fast != null);
        head.next = null;
        return slow;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        ListNode head = node;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                node.next = l2;
                l2 = l2.next;
            } else {
                node.next = l1;
                l1 = l1.next;
            }
            node = node.next;
        }

        if (l1 == null)
            node.next = l2;
        if (l2 == null)
            node.next = l1;

        return head.next;
    }

    public static boolean isPalindrome(ListNode head) {
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        int start;
        if (count % 2 == 0) {
            start = count / 2 + 1;
        } else {
            start = count / 2 + 2;
        }
        ListNode halfHead = head;
        for (int i = 1; i < start; i++) {
            halfHead = halfHead.next;
        }

        ListNode endNode = reverseList(halfHead);
        while (head != null && endNode != null) {
            if (head.val != endNode.val) return false;
            head = head.next;
            endNode = endNode.next;
        }
        return true;
    }


    public static void toString(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


}
