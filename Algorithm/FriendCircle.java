public class Main {
    private void dfs(int[][] M, boolean[] visited, int person) {
        for (int other = 0; other < M.length; other++) {
            if (M[person][other] == 1 && !visited[other]) {
                visited[other] = true;
                dfs(M, visited, other);
            }
        }
    }

    public int findNum(int[][] M) {
        int count = 0;
        boolean[] visited = new boolean[M.length];
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }


}