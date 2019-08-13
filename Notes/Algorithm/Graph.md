# Graph
- 997 [Find the Town Judge](https://leetcode.com/problems/find-the-town-judge/)
```
Java

/* 相当于是一个图，找出一个入度in-degree为N - 1（全村人除法官外），出度out-degree为0的点

class Solution {
    public int findJudge(int N, int[][] trust) {
        int ans = -1;
        int[] person = new int[N + 1]; // 相当于出度
        int[] trusted = new int[N + 1]; // 相当于入度
        person[0] = -1;
        trusted[0] = -1;
        for (int[] array : trust) {
            person[array[0]]++;
            trusted[array[1]]++;
        }
        for (int i = 0; i < person.length; i++) {
            if (person[i] == 0 && trusted[i] == N - 1) {
                ans = i;
            }
        }
        return ans;
    }
}
```