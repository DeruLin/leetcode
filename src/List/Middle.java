package List;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Middle {

    //24. 两两交换链表中的节点 https://leetcode-cn.com/problems/swap-nodes-in-pairs/
    public static ListNode swapPairs(ListNode head) {
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode tmp = start;
        while (tmp.next != null && tmp.next.next != null) {
            ListNode a = tmp.next;
            ListNode b = tmp.next.next;
            a.next = b.next;
            b.next = a;
            tmp.next = b;
            tmp = tmp.next.next;
        }
        return start.next;
    }

    //25. K 个一组翻转链表 https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode tmp = start;
        boolean flag = true;
        while (flag) {
            reverseK(tmp, k);
            for (int i = 0; i < k; i++) {
                if (tmp.next != null)
                    tmp = tmp.next;
                else {
                    flag = false;
                    break;
                }
            }
        }
        return start.next;
    }

    public static void reverseK(ListNode head, int k) {
        ListNode tmp = head;
        for (int i = 0; i < k; i++) {
            if (tmp.next == null)
                return;
            else tmp = tmp.next;
        }
        ListNode prev = tmp.next;
        ListNode curr = head.next;
        while (k > 0) {
            ListNode aaa = curr.next;
            curr.next = prev;
            prev = curr;
            curr = aaa;
            k--;
        }
        head.next = prev;

    }

    //206. 反转链表 https://leetcode-cn.com/problems/reverse-linked-list/
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode start = new ListNode(1);
        start.next = new ListNode(2);
        start.next.next = new ListNode(3);
        start.next.next.next = new ListNode(4);
        System.out.println(reverseKGroup(start,2));
    }

}
