package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P001 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 도시의 개수 (노드)
        int m = Integer.parseInt(st.nextToken()); // 도로의 개수 (간선)
        int k = Integer.parseInt(st.nextToken()); // 거리 정보
        int s = Integer.parseInt(st.nextToken()); // 출발 도시

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        dist[s] = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : graph.get(now)) {
                // 아직 방문하지 않은 노드라면
                if (dist[next] == -1) {
                    dist[next] = dist[now] + 1; // 거리 갱신
                    queue.add(next);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == k) {
                sb.append(i).append("\n");
                found = true;
            }
        }

        if (!found) System.out.println(-1);
        else System.out.print(sb.toString());
    }
}
