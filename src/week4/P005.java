package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[n + 1];
        int[] cost = new int[n + 1];
        // result 배열은 '선수 건물이 모두 완료되는 데 걸리는 시간'을 저장
        int[] result = new int[n + 1];

        // 1. 입력 파싱
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            cost[i] = Integer.parseInt(st.nextToken()); // 건물 짓는 비용(시간)

            while (st.hasMoreTokens()) {
                int preBuilding = Integer.parseInt(st.nextToken());
                if (preBuilding == -1) {
                    break;
                }
                // 간선 연결: preBuilding -> i
                graph.get(preBuilding).add(i);
                indegree[i]++; // i의 진입 차수 증가
            }
        }

        // 2. 위상 정렬 (Kahn's Algorithm)
        Queue<Integer> queue = new LinkedList<>();

        // 진입 차수가 0인 노드 큐에 추가
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            // ★ 현재 건물 완공 시간 = (선수 건물 완료 시간) + (현재 건물 짓는 시간)
            result[now] += cost[now];

            for (int b : graph.get(now)) {
                indegree[b]--;
                result[b] = Math.max(result[b], result[now]);

                if (indegree[b] == 0) {
                    queue.add(b);
                }
            }
        }

        // 3. 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.print(sb.toString());
    }
}