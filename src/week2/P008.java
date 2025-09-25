package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P008 {
    static int n, m, v;
    static int[][] graph;
    static boolean[] visitedDfs;
    static boolean[] visitedBfs;
    static StringBuilder dfsResult = new StringBuilder();
    static StringBuilder bfsResult = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        graph = new int[n+1][n+1];
        visitedDfs = new boolean[n+1];
        visitedBfs = new boolean[n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = graph[b][a] = 1;
        }

        dfs(v);
        bfs(v);

        System.out.println(dfsResult);
        System.out.println(bfsResult);
    }

    // DFS (깊이 우선 탐색) - 재귀
    private static void dfs(int v) {
        visitedDfs[v] = true;
        dfsResult.append(v).append(" ");

        for (int i = 1; i <= n; i++) {
            if (!visitedDfs[i] && graph[v][i] == 1) {
                dfs(i);
            }
        }
    }

    // BFS (너비 우선 탐색) - 큐
    private static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visitedBfs[v] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            bfsResult.append(current).append(" ");

            for (int i = 1; i <= n; i++) {
                if (!visitedBfs[i] && graph[current][i] == 1) {
                    queue.add(i);
                    visitedBfs[i] = true;
                }
            }
        }
    }
}
