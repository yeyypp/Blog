# Heap
- 295 [Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/)
```
Java

class MedianFinder {
    
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    private double median;

    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
        minHeap = new PriorityQueue<Integer>();
        median = 0.0;
    }
    
    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    public double findMedian() {
        return maxHeap.size() > minHeap.size() ? maxHeap.peek() : (double) (maxHeap.peek() + minHeap.peek()) / 2;
    }
}
```
- 347 [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)
```
Java
class Solution {
    private Map<Integer, Integer> map = new HashMap<>();
    
    public List<Integer> topKFrequent(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        for (Integer key : map.keySet()) {
            minHeap.offer(key);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        List<Integer> ans = new ArrayList<>(k);
        while (!minHeap.isEmpty()) {
            ans.add(0, minHeap.poll());
        }
        return ans;
    }
}
```

- 378 [Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/)
```
Java

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, (o1, o2) -> o2 - o1);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                maxHeap.offer(matrix[i][j]);
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }
        return maxHeap.poll();
    }
}
```
