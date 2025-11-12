package week5;

import java.io.*;
import java.util.*;

public class P001 {
    static final long INF = Long.MAX_VALUE;

    // 이너 클래스로 Edge 정의 (static 필수)
    static class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>();
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u, v, w));
        }

        dist[1] = 0;

        // 벨만-포드 알고리즘
        for (int i = 1; i <= N; i++) {
            for (Edge edge : edges) {
                if (dist[edge.u] != INF && dist[edge.u] + edge.w < dist[edge.v]) {
                    dist[edge.v] = dist[edge.u] + edge.w;
                    // N번째 라운드에서도 값이 갱신된다면 음수 사이클 존재
                    if (i == N) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            if (dist[i] == INF) {
                sb.append("-1\n");
            } else {
                sb.append(dist[i]).append("\n");
            }
        }
        System.out.print(sb);
    }
}