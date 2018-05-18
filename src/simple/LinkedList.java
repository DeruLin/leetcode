package simple;

public class LinkedList {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(5);
        head1.next.next = new ListNode(6);

        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);

        toString(mergeTwoLists(head1, head2));
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
