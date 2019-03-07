import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFSpath {
    private boolean[] marked;
    private int[] edgeTo;
    private Graph G;
    private int s;

    public BFSpath(Graph G, int s) {
        this.G = G;
        this.s = s;
        Arrays.fill(marked, false);
        bfs(G, s);
    }

    private void bfs(Graph G, int v) {
        marked[v] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        while (!queue.isEmpty()) {
            int a = queue.poll();
            for (int w : G.adj(a)) {
                if (!marked[w]) {
                    edgeTo[w] = a;
                    marked[w] = true;
                    queue.offer(w);
                }
            }

        }
    }


}
