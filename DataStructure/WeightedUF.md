# Weighted Union Find
```
public class WUF {
    private int[] id;
    private int[] size;
    private int count;
    
    public WUF(int N) {
        this.id = new int[N];
        this.size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
        this.count = N;
    }
    
    public void union(int p, int q) {
        if (isConnected(p, q)) {
            return;
        }
        int pRoot = find(p);
        int qRoot = find(q);
        
        if (size[pRoot] > size[qRoot]) {
            id[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        } else {
            id[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        }
        count--;
    }
    
    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }
    
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
    
    public int count() {
        return count;
    }
}
```