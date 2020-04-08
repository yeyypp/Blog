# LinkedList
- 2 [Add Two Numbers](https://leetcode.com/problems/add-two-numbers/)
```
Java

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy, a = l1, b = l2;
        int carry = 0;
        
        while (a != null || b != null) {
            int x = a == null ? 0 : a.val;
            int y = b == null ? 0 : b.val;
            int val = carry + x + y;
            carry = val / 10;
            val = val % 10;
            cur.next = new ListNode(val);
            cur = cur.next;
            
            a = a == null ? null : a.next;
            b = b == null ? null : b.next;
            
        }
        
        if (carry != 0) {
            cur.next = new ListNode(1);
        }
        
        return dummy.next;
    }
}
```
- 19 [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)
```
// must use the dummy head so that the algorithm work when the ListNode has only one element and n == 1

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy, second = dummy;
        
        // it is n + 1
        for (int i = 0; i < n + 1; i++) {
            first = first.next;
        }
        
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        ListNode tem = second.next;
        second.next = tem.next;
        tem.next = null;
        return dummy.next;
    }
}
```
- 21 [Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)
```
Java

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
```


- 23 [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)
```
Java

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return split(lists, 0, lists.length - 1);
    }
    
    private ListNode split(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        ListNode a = split(lists, left, mid);
        ListNode b = split(lists, mid + 1, right);
        return merge(a, b);
    }
    
    private ListNode merge(ListNode a, ListNode b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (a != null && b != null) {
            if (a.val < b.val) {
                cur.next = a;
                a = a.next;
            } else {
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }
        
        while (a != null) {
            cur.next = a;
            a = a.next;
            cur = cur.next;
        }
        
        while (b != null) {
            cur.next = b;
            b = b.next;
            cur = cur.next;
        }
        return head.next;
    }
}
```

- 138 [Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/)
```
Java

// first: create copy after each origin node
// second: copy random
// third: separate copy list from origin list

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        
        Node cur = head, next = null;
        
        while (cur != null) {
            next = cur.next;
            
            Node tem = new Node(cur.val, null, null);
            tem.next = next;
            cur.next = tem;
            cur = next;
        }
        
        cur = head;
        
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        
        cur = head;
        Node copyHead = head.next, copy = copyHead;
        
        while (cur != null) {
            next = cur.next.next;
            cur.next = next;
            cur = cur.next;
            
            if (cur != null) {
                copy.next = cur.next;
                copy = copy.next;
            }
        }
        
        return copyHead;
    }
}
```

- 141 [LinkedList Cycle](https://leetcode.com/problems/linked-list-cycle/)
```
Java

public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head, fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
        
    }
}
```

- 142 [LinkedList Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/)
```
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        
        if (fast == null || fast.next == null) {
            return null;
        }
        
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
}
```

- 148 [Sort List](https://leetcode.com/problems/sort-list/)
```
Java

class Solution {
    public ListNode sortList(ListNode head) {
        return split(head);
    }
    
    private ListNode split(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode mid = findMid(node);
        ListNode a = split(node);
        ListNode b = split(mid);
        return merge(a, b);
    }
    
    private ListNode findMid(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode slow = node, fast = node, pre = null;
        
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        return slow;
    }
    
    private ListNode merge(ListNode a, ListNode b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (a.val < b.val) {
            a.next = merge(a.next, b);
            return a;
        } else {
            b.next = merge(b.next, a);
            return b;
        }
    }
}
```

- 160 [Intersection of Two LinkedList](https://leetcode.com/problems/intersection-of-two-linked-lists/)
```
Java

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode temA = headA;
        while (temA != null) {
            set.add(temA);
            temA = temA.next;
        }
        ListNode temB = headB;
        while (temB != null) {
            if (set.contains(temB)) {
                return temB;
            }
            temB = temB.next;
        }
        return null;
    }
}
// the key is if there is no intersection, 
// when the second iteration end, both of the pointer would be null
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        
        return a;
    }
}
```


- 206 [Reverse LinkedList](https://leetcode.com/problems/reverse-linked-list/)
```
Java

class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null, cur = head, tem = cur.next;
        
        while (cur != null) {
            tem = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tem;
        }
        return pre;
    }
}
```

- 234 [Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/)
```
Java

class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        if (fast != null) {
            slow = reverse(slow.next);
        } else {
            slow = reverse(slow);
        }
        
        
        
        while (slow != null && head != null) {
            if (slow.val != head.val) {
                return false;
            }
            slow = slow.next;
            head = head.next;
        }
        return true;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head, tem = cur.next;
        
        while (cur != null) {
            tem = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tem;
        }
        return pre;
    }
}
```

- 237 [Delete Node in LinkedList](https://leetcode.com/problems/delete-node-in-a-linked-list/)
```
Java

class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        ListNode tem = node.next.next;
        node.next.next = null;
        node.next = tem;
    }
}
```

- 328 [Odd Even Linked List](https://leetcode.com/problems/odd-even-linked-list/)
```
Java

class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode odd = head, even = head.next, evenHead = even;
        
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
```