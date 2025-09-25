package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P007 {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start].add(end);
            graph[end].add(start);
        }
        int componentCount = 0;
        for(int i = 1; i <= n; i++) {
            if (!visited[i]) {
                componentCount++;
                dfs(i);
            }
        }
        System.out.println(componentCount);
    }

    private static void dfs(int v) {
        visited[v] = true;
        for (int neighbor : graph[v]) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
}
