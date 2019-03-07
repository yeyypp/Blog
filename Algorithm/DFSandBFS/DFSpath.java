import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DFSpath {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;


    public DFSpath(Graph G, int s) {
        this.s = s;
        Arrays.fill(marked, false);
        dfs(G, s);

    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }


}
