# BinarySearch
**a** : the array to be searched  
**fromIndex** : the index of the first element (inclusive) to besearched  
**toIndex** : the index of the last element (exclusive) to be searched  
**key** : the value to be searched for

when the return value is negative = - (low + 1), it means the key is not in the array
and the correct position would be  position = - (return value + 1)
```
Java

public static int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex - 1;
        
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];
            
            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -(low + 1);
    }
```

- 4 [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)
```
Java

// Solution 1 : Using PriorityQueue
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int x = nums1.length;
        int y = nums2.length;
        int size = (x + y) / 2 + 1;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int i : nums1) {
            if (minHeap.size() < size) {
                minHeap.offer(i);
            } else if (i > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(i);
            }
        }
        
        for (int i : nums2) {
            if (minHeap.size() < size) {
                minHeap.offer(i);
            } else if (i > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(i);
            }
        }
        
        if ((x + y) % 2 == 1) {
            return minHeap.poll();
        }
        return (minHeap.poll() + minHeap.poll()) / 2.0;
    }
}

// Solution 2 Using BinarySearch
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int x = nums1.length;
        int y = nums2.length;
        
        int low = 0, high = x;
        
        while (low <= high) {
        // the partition variable means the split point, so partitionX + partitionY
        = (x + y + 1) / 2
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2- partitionX;
            
            int maxLeftX = partitionX == 0 ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = partitionX == x ? Integer.MAX_VALUE : nums1[partitionX];
            int maxLeftY = partitionY == 0 ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = partitionY == y ? Integer.MAX_VALUE : nums2[partitionY];
            
            // if the x + y is odd, then answer would be the max of maxLeftX and maxLeftY
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((x + y) % 2 == 1) {
                    return (double) Math.max(maxLeftX, maxLeftY);
                }
                // if x + y is even, answer would be (max left + min right) / 2
                return (double) (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
            }
            
            if (maxLeftX > minRightY) {
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }
        }
        return -1.0;
    }
}
```

- 240 [Search 2D matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/)
```
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int i = 0, j = matrix[0].length - 1;
        while (valid(matrix, i, j)) {
            if (target > matrix[i][j]) {
                i++;
            } else if (target < matrix[i][j]) {
                j--;
            } else {
                return true;
            }
        }
        return false;
    }
    
    private boolean valid(int[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
            return false;
        }
        return true;
    }
}
```