package pers.mihao.careerism.data_structures.linktable;

import pers.mihao.careerism.data_structures.util.ListNode;

/**
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *
 * @Author mh32736
 * @Date 2021/12/11 21:41
 */
public class Code206反转链表 {

    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode curr = head.next, next, pre = head;
        while (curr != null) {
            next = curr.next;
            curr.next = pre;
            if (pre == head) pre.next = null;
            pre = curr;
            curr = next;
        }
        return pre;
    }

}
