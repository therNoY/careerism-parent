package pers.mihao.careerism.data_structures.linktable;

/**
 * 206. 反转链表
 * 92. 反转链表 II
 */
public class ReverseList {

    public static void main(String[] args) {

        ListNode node = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
//        System.out.println(new ReverseList().reverseList(node));
//        System.out.println(new ReverseList().reverseList2(node));
        ListNode nod = new ReverseList().reverseBetween(node, 2, 4);
    }


    public ListNode reverseList(ListNode head) {
        ListNode curNode = head, preNode = null, tempNode;
        while (curNode != null) {
            tempNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = tempNode;
        }
        return preNode;
    }


    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            ListNode lastNode = new ListNode(-1);
            return reverseN(head, n, lastNode);
        } else {
            head.next = reverseBetween(head.next, m - 1, n - 1);
        }
        return head;
    }

    ListNode reverseN(ListNode head, int n, ListNode lastNode) {
        if (n == 1) {
            lastNode.next = head.next;
            return head;
        }
        ListNode newHead = reverseN(head.next, n - 1, lastNode);
        head.next.next = head;
        head.next = lastNode.next;
        return newHead;
    }

    /**
     * 使用递归算法
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head.next == null) return head;
        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
