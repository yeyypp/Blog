# Graph

Graph
```
Java

/**
 * @author ShuaiYe
 * @date 2019/8/21 16:16
 */
public class Graph {
    private class Node {
        private long id;
        private List<Long> adjacent;
        private Map<Long, Double> distanceTo;

        private Node(long id) {
            this.id = id;
            adjacent = new LinkedList<>();
            distanceTo = new HashMap<>();
        }

        private void addNeighbor(long adjID) {
            adjacent.add(adjID);
            distanceTo.put(adjID, distance(this.id, adjID));
        }
    }

    private Map<Long, Node> nodeMap = new HashMap<>();
    
    public double distance(long aid, long bid) {
        return 0.0;
    }

    public void addNode(long id) {
        nodeMap.put(id, new Node(id));
    }
    
    public void addEdge(long aid, long bid) {
        nodeMap.get(aid).addNeighbor(bid);
        nodeMap.get(bid).addNeighbor(aid);
    }
    
    Iterable<Long> getCurAdjacent(long id) {
        return nodeMap.get(id).adjacent;
    }
}
```

Dijkstra 
```
Java

public static List<Long> Dijkstra(Graph g, long startID, long destID) {
        Map<Long, Double> disToS = new HashMap<>();
        for (long id : g.vertices()) {
            disToS.put(id, Double.MAX_VALUE);
        }
        disToS.put(startID, 0.0);
        
        Map<Long, Long> path = new HashMap<>();

        PriorityQueue<Long> minHeap = new PriorityQueue<>(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                double o1Dis = disToS.get(o1);
                double o2Dis = disToS.get(o2);
                return o1Dis < o2Dis ? -1 : 1;
            }
        });
        
        Set<Long> visited = new HashSet<>();
        minHeap.offer(startID);
        while (!minHeap.isEmpty()) {
            Long curID = minHeap.poll();
            if (curID == destID) {
                break;
            }
            
            if (!visited.contains(curID)) {
                visited.add(curID);
                for (long adjID : g.getCurAdjacent(curID)) {
                    double oldDis = disToS.get(adjID);
                    double newDis = disToS.get(curID) + g.distance(curID, adjID);
                    if (newDis < oldDis) {
                        disToS.put(adjID, newDis);
                        minHeap.offer(adjID);
                        path.put(adjID, curID);
                    }
                }
            }
        }
        List<Long> ans = new LinkedList<>();
        Long cur = destID;
        while (cur != startID) {
            ans.add(0, cur);
            cur = path.get(cur);
        }
        ans.add(0, startID);
        return ans;
    }
```

A*
```
Java

public static List<Long> Astar(Graph g, long startID, long destID) {
        Map<Long, Double> disToS = new HashMap<>();
        Map<Long, Double> disToD = new HashMap<>();
        for (long id : g.vertices()) {
            disToS.put(id, Double.MAX_VALUE);
            disToD.put(id, g.distance(id, destID));
        }
        disToS.put(startID, 0.0);
        disToD.put(destID, 0.0);

        Map<Long, Long> path = new HashMap<>();
        path.put(startID, startID);

        PriorityQueue<Long> minHeap = new PriorityQueue<>(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                double o1Dis = disToS.get(o1) + disToD.get(o1);
                double o2Dis = disToS.get(o2) + disToD.get(o2);
                return o1Dis < o2Dis ? -1 : 1;
            }
        });

        Set<Long> visited = new HashSet<>();

        minHeap.offer(startID);
        
        while (!minHeap.isEmpty()) {
            long curID  = minHeap.poll();
            
            if (curID == destID) {
                break;
            }
            
            if (!visited.contains(curID)) {
                visited.add(curID);
                for (long adjID : g.getCurAdjacent(curID)) {
                    double oldDis = disToS.get(adjID);
                    double newDis = disToS.get(curID) + g.distance(curID, adjID);
                    if (newDis < oldDis) {
                        disToS.put(adjID, newDis);
                        path.put(adjID, curID);
                        minHeap.offer(adjID);
                    }
                }
            }
        }
        
        List<Long> ans = new LinkedList<>();
        long cur = destID;
        while (cur != startID) {
            ans.add(0, cur);
            cur = path.get(cur);
        }
        ans.add(0, startID);
        return ans;
    }
}
```

Detect Cycle By Kahn Algorithm
- 207 [Course Schedule](https://leetcode.com/problems/course-schedule/)
