package List;

import java.util.BitSet;

public class Easy {

    //160. 相交链表 https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int stepA = 0, stepB = 0;
        ListNode tmpA = headA, tmpB = headB;
        while (tmpA != null) {
            tmpA = tmpA.next;
            stepA++;
        }
        while (tmpB != null) {
            tmpB = tmpB.next;
            stepB++;
        }
        int diff = Math.abs(stepA - stepB);
        if (stepA > stepB) {
            while (diff > 0) {
                headA = headA.next;
                diff--;
            }
        } else {
            while (diff > 0) {
                headB = headB.next;
                diff--;
            }
        }
        while (headA != null && headB != null) {
            if (headA == headB) return headA;
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    //141. 环形链表 https://leetcode-cn.com/problems/linked-list-cycle/
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && slow != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) fast = fast.next;
            if (slow == fast && slow!=null) return true;
        }
        return false;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        Middle.ListNode start = new Middle.ListNode(1);
        start.next = new Middle.ListNode(2);
        start.next.next = new Middle.ListNode(3);
        start.next.next.next = new Middle.ListNode(4);
        System.out.println();
    }

}
