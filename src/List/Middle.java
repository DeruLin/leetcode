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

    public static ListNode prev = null;

    public static ListNode reverseList2(ListNode head) {
        if (head == null) return null;
        prev = null;
        return reverseListHelper(head);
    }

    public static ListNode reverseListHelper(ListNode head) {
        ListNode curr = head;

        ListNode tmp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = tmp;

        if (curr == null) return prev;

        return reverseListHelper(curr);
    }

    //2. 两数相加 https://leetcode-cn.com/problems/add-two-numbers/
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean flag = false;
        ListNode head = new ListNode(0);
        ListNode tmp = head;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val;
            if (flag) {
                flag = false;
                sum += 1;
            }
            if (sum >= 10) {
                flag = true;
                sum -= 10;
            }
            tmp.next = new ListNode(sum);
            tmp = tmp.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int sum = l1.val;
            if (flag) {
                flag = false;
                sum += 1;
            }
            if (sum >= 10) {
                flag = true;
                sum -= 10;
            }
            tmp.next = new ListNode(sum);
            tmp = tmp.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = l2.val;
            if (flag) {
                flag = false;
                sum += 1;
            }
            if (sum >= 10) {
                flag = true;
                sum -= 10;
            }
            tmp.next = new ListNode(sum);
            tmp = tmp.next;
            l2 = l2.next;
        }
        if(flag) {
            tmp.next = new ListNode(1);
        }
        return head.next;
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
        System.out.println(reverseList2(start));
    }

}
