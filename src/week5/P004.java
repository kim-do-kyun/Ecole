package week5;

import java.io.*;
import java.util.*;

public class P004 {
    static ArrayList<Integer>[] graph;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        // 입력 속도를 높이기 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        // 그래프 초기화
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new int[n + 1];

        // 루트 노드 방문 처리 후 DFS 시작
        // 1번 노드의 부모는 없거나 자기 자신으로 표시하여 중복 방문 방지
        visited[1] = 1;
        dfs(1);

        // 결과 출력 (StringBuilder 사용으로 속도 향상)
        StringBuilder sb = new StringBuilder();
        for (int x = 2; x <= n; x++) {
            sb.append(visited[x]).append("\n");
        }
        System.out.print(sb);
    }

    // DFS 함수
    static void dfs(int s) {
        for (int next : graph[s]) {
            if (visited[next] == 0) {
                visited[next] = s; // 방문하지 않은 노드의 부모를 현재 노드(s)로 설정
                dfs(next);
            }
        }
    }
}
