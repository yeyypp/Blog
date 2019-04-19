public class Main {
    /**
     * 237 Delete Node in a LinkedList
     * 给的是要被删除的node
     */

    class Solution {
        public void deleteNode(ListNode node) {
            ListNode tem = node.next;
            node.val = tem.val;
            node.next = tem.next;
            tem.next = null;
        }
    }
}