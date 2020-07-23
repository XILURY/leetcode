import java.util.List;
import java.util.Stack;

/**
 * 倒转链表 head节点有数据 非空
 */
public class ReverseList {
    public static void main(String[] args) {
        ReverseList reverseList = new ReverseList();
        ListNode head = new ListNode(2);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(6);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        System.out.println("原链表为：");
        reverseList.show(head);
        System.out.println("翻转后的链表为：");
//        reverseList.reverseList(head);
        reverseList.getReverse(head);
        reverseList.show(head);

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

//     双指针法
    public ListNode getReverse(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp;
        while(cur.next != null){
            temp = cur.next; // 保存当前节点的下一节点索引
            cur.next = pre; // 方向交换

            // 向后移动
            pre = cur;
            cur =temp;
        }
        return pre;
    }

    // 常规方法 用栈实现
    public ListNode reverseList(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode temp = head;
        if (head == null) {
            return null;
        }
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
        }
        if (!stack.isEmpty()) {
            head.val = stack.pop();
        }
        temp = head.next;
        while (!stack.isEmpty()) {
            temp.val = stack.pop();
            temp = temp.next;
        }
        return head;
    }

    // 打印
    public void show(ListNode head) {
        while(head != null) {
            System.out.print(head.val+" ");
            head = head.next;
        }
        System.out.println();
    }
}

