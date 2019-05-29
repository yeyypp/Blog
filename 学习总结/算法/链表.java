public class Main {
    /**
     * 19 删除链表倒数第N个节点
     */

    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null) {
                return null;
            }
            ListNode fast = head;
            ListNode slow = head;
            int i = 0;
            while (i < n) {
                fast = fast.next;
                i++;
            }
            while (fast != null && fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }
            //如果fast==null，意味着是删除链表第一个节点
            if (fast == null) {
                return head.next;
            }
            slow.next = slow.next.next;
            return head;

        }
    }

    /**
     * 21 合并两个有序链表
     */

    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l2.next, l1);
                return l2;
            }
        }
    }

    class Solution {
        public ListNode mergeTwoListts(ListNode l1, ListNode l2) {

        }
    }

    /**
     * 109 有序链表转bst
     * 转换list，再求
     * 求中点
     */

    class Solution {
        public TreeNode sortedListToBST(ListNode head) {
            List<Integer> list = new ArrayList<>();
            while (head != null) {
                list.add(head.val);
                head = head.next;
            }
            return help(list, 0, list.size() - 1);
        }

        private TreeNode help(List<Integer> list, int low, int high) {
            if (low > high) {
                return null;
            }
            int mid = (low + high) / 2;
            TreeNode node = new TreeNode(list.get(mid));
            node.left = help(list, low, mid - 1);
            node.right = help(list, mid + 1, high);
            return node;
        }
    }

    /**
     * 141 环形链表
     * 快慢
     */

    public class Solution {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) {
                return false;
            }

            ListNode slow = head;
            ListNode fast = head.next;

            while (fast != null && fast.next != null) {
                if (slow == fast) {
                    return true;
                }
                slow = slow.next;
                fast = fast.next.next;
            }
            return false;
        }
    }

    /**
     * 148 排序链表
     * 类似归并的思想
     * 注意刚开始判断head以及head.next是否为null
     */
    class Solution {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode fast = head;
            ListNode slow = head;
            ListNode pre = null;
            while (fast != null && fast.next != null) {
                pre = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            pre.next = null;
            ListNode l1 = sortList(head);
            ListNode l2 = sortList(slow);
            return merge(l1, l2);

        }

        private ListNode merge(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            if (l1.val < l2.val) {
                l1.next = merge(l1.next, l2);
                return l1;
            } else {
                l2.next = merge(l2.next, l1);
                return l2;
            }
        }
    }

    /**
     * 203 移除元素
     */

    class Solution {
        public ListNode removeElements(ListNode head, int val) {
            ListNode root = new ListNode(0);
            root.next = head;
            ListNode cur = head;
            ListNode pre = root;
            while (cur != null) {
                if (cur.val == val) {
                    pre.next = cur.next;
                    cur.next = null;
                    cur = pre.next;
                } else {
                    pre = pre.next;
                    cur = cur.next;
                }
            }
            return root.next;
        }
    }

    /**
     * 234 回文链表
     * 先求中点，然后翻转比较
     */

    class Solution {
        public boolean isPalindrome(ListNode head) {
            ListNode slow = head;
            ListNode fast  =head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode node = null;
            if (fast == null) {
                node = reverse(slow);
            } else {
                node = reverse(slow.next);
            }

            while (node != null) {
                if (node.val != head.val) {
                    return false;
                }
                node = node.next;
                head = head.next;
            }
            return true;
        }

        private ListNode reverse(ListNode node) {
            if (node == null) {
                return null;
            }
            ListNode pre = null;
            ListNode tem = null;
            while (node != null) {
                tem = node.next;
                node.next = pre;
                pre = node;
                node = tem;
            }
            return pre;
        }


    }

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

    /**
     * 876 链表中间节点
     * 快慢
     * 在其他需要求中间节点的问题中，当快指针等于null，说明有偶数个，如 1，2，3，4
     * 当快指针不等于null，说明有基数个，如1，2，3，4，5
     */

    class Solution {
        public ListNode middleNode(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }
}