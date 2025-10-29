package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P002 {
    static ArrayList<ArrayList<Integer>> graph;
    static int[] visited; // -1: 미방문, 1: 1번 그룹, 2: 2번 그룹
    static boolean isBipartite;

    static void dfs(int node) {
        if (!isBipartite) {
            return;
        }
        for (int neighbor : graph.get(node)) {
            if (visited[neighbor] == -1) {
                visited[neighbor] = (visited[node] == 1) ? 2 : 1;
                dfs(neighbor);
            }
            else if (visited[node] == visited[neighbor]) {
                isBipartite = false;
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()); // 정점(Vertex) 수
            int e = Integer.parseInt(st.nextToken()); // 간선(Edge) 수

            // 1. 그래프 및 방문 배열 초기화
            graph = new ArrayList<>();
            for (int j = 0; j <= v; j++) {
                graph.add(new ArrayList<>());
            }

            // -1 (미방문) 상태로 초기화
            visited = new int[v + 1];
            Arrays.fill(visited, -1);

            // 2. 간선 정보 입력
            for (int j = 0; j < e; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                // 양방향 그래프
                graph.get(start).add(end);
                graph.get(end).add(start);
            }

            // 3. 이분 그래프 판별
            isBipartite = true; // 일단 true로 가정

            for (int j = 1; j <= v; j++) {
                // 아직 방문하지 않은 정점(새 컴포넌트의 시작점)이라면
                if (visited[j] == -1) {
                    visited[j] = 1; // 1번 그룹(색)으로 칠하고 시작
                    dfs(j);
                }
                // DFS 도중 이분 그래프가 아님이 판명되면 즉시 중단
                if (!isBipartite) {
                    break;
                }
            }

            // 4. 결과 출력
            sb.append(isBipartite ? "YES" : "NO").append("\n");
        }
        System.out.print(sb.toString());
    }
}