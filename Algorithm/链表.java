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
     * 138 深拷贝带有随机指针的链表
     */
    class Solution {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }
            //先不管random指针，将每个节点后边依次添加当前节点的复制，入 1 - 2 - 3 变成 1 - 1' - 2 - 2' - 3 - 3'
            Node tem = head;
            while (tem != null) {
                Node next = tem.next;
                tem.next = new Node(tem.val, next, null);
                tem = next;
            }

            //然后将复制的链表的random指针添加上
            tem = head;
            while (tem != null) {
                if (tem.random != null) {
                    tem.next.random = tem.random.next;
                }
                tem = tem.next.next;
            }

            //最后将复制的链表从原链表中拿走
            tem = head;
            Node copyHead = tem.next;
            Node curCopy = copyHead;
            while (curCopy != null) {
                curCopy.next = curCopy.next.next;
                curCopy = curCopy.next;
                tem.next = tem.next.next;
                tem = tem.next;
            }
            //最后还需要把最后一位原链表的next指向null
            tem.next = tem.next.next;

            return copyHead;
        }
    }



    /**
     * 141 环形链表
     * 快慢
     * 或者用set
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
     * 142 链表是否有环，并找到环的入口
     * 通过双指针，第一遍找到相遇的listnode
     * 然后fast从head开始遍历，当再相遇时，即为入口
     */

    public class Solution {
        public ListNode detectCycle(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null) {
                slow = slow.next;
                if (fast.next == null) {
                    return null;
                }
                fast = fast.next.next;
                if (slow == fast) {
                    break;
                }
            }
            if (fast == null) {
                return null;
            }
            fast = head;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
            return fast;
        }
    }

    /**
     * 148 排序链表
     * 类似归并的思想
     * 注意刚开始判断head以及head.next是否为null
     * 一个merge方法
     * 一个split方法
     * split方法 return merge(split(l1), split(slow));
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
     * 160 相交链表
     * 可以用set存储A，再依次遍历B，看有没有相同
     * time O(m + n) space m
     * 或者用两个指针，依次遍历，当到达结尾时，使指针指向另一个的头节点，继续遍历
     * 注意在遍历中判断是否有相等，无论两个链表长度是否相等，两个指针最终走的距离都是一样的，相当于都走了两个链表
     */

    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode a = headA;
            ListNode b = headB;
            while (a != b) {
                a = a == null ? headB : a.next;
                b = b == null ? headA : b.next;
            }
            return a;
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
     * 206 反转链表
     * 两个指针
     * ListNode pre = null;
     * ListNode tem = null;
     */

    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode tem = null;
            ListNode pre = null;
            while (head != null) {
                tem = head.next;
                head.next = pre;
                pre = head;
                head = tem;
            }
            return pre;
        }
    }

    /**
     * 234 回文链表
     * 先求中点，然后翻转比较
     * 注意原链表大小，奇数时slow还需要再往后移动一次
     *
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
     * 328 奇偶链表
     * https://leetcode.com/problems/odd-even-linked-list/solution/
     * 三个指针 头节点 odd = head 第二个元素 even = head.next 指向第二个元素的指针 cur = even，思路是分别得到奇数链表偶数链表
     * 再连接。在这里让 odd.next = cur.next odd = odd.next这样得到奇数链表 cur.next = odd.next cur = cur.next
     * 此时的odd就为第三个元素，所以它的下一个元素是偶数元素，得到偶数链表最后组合odd.next = even。
     * 注意判断时，需要判断cur != null && cur.next != null;
     */



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