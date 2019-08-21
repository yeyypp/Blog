# LinkedList
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