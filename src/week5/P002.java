package week5;

import java.io.*;
import java.util.*;

public class P002 {
    static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] graph = new int[n + 1][n + 1];

        // 그래프 초기화
        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        // 간선 정보 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a][b] = Math.min(graph[a][b], c);
        }

        // 플로이드-워셜 알고리즘
        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                if (graph[a][b] == INF) {
                    sb.append("0 ");
                } else {
                    sb.append(graph[a][b] + " ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}