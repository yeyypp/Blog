public class Main {
    /**
     *
     */
    int[] parent;
    int[] size;

    parent = new int[N];
    size = new int[N];

    for (int i = 0; i < N; i++) {
        parent[i] = i;
        size[i] = 1;
    }

    public void connect(int q, int p) {
        int i = find(q);
        int j = find(p);
        if (i == j) {
            return;
        }
        if (size[i] < size[j]) {
            parent[i] = j;
            size[j] += size[i];
        } else {
            size[j] = i;
            size[i] += size[j];
        }
    }
    //regular
    private int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
    // compression
    private int find(int p) {
        if (p == parent[p]) {
            return p;
        } else {
            parent[p] = find(parent[p]);
            return parent[p];
        }

    }
}