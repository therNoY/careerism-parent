package pers.mihao.careerism.data_structures.linktable;


import pers.mihao.careerism.data_structures.util.ListNode;

/**
 * 92. 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * @return
 */
public class Code92反转链表2 {

    public static void main(String[] args) {

    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 设置 dummyNode 是这一类问题的一般做法
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }

    public ListNode reverseBetween20211214(ListNode head, int left, int right) {
        if (head == null) return null;
        if (head.next == null || left == right) return head;
        ListNode pre = null, curr = head, next, preStart = null, start = null, end = null, endRight = null;
        int cursor = 1;
        for (; curr != null; cursor++) {
            if (cursor == left) {
                preStart = pre;
                start = curr;
                pre = curr;
                curr = curr.next;
            } else if (cursor > left && cursor <= right) {
                next = curr.next;
                end = curr;
                curr.next = pre;
                pre = curr;
                curr = next;
                if (cursor == right) {
                    endRight = curr;
                }
            } else {
                pre = curr;
                curr = curr.next;
            }
        }
        if (preStart != null) {
            preStart.next = end;
        }
        start.next = cursor == right + 1 ? null : endRight;
        return left != 1 ? head : end;
    }

}
