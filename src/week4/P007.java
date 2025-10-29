package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P007 {
    static class Node implements Comparable<Node> {
        int index;
        long cost;

        public Node(int index, long cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            // long 타입 비교는 Long.compare 사용 권장
            return Long.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // graph[i] = (비용, 도착노드) -> (Node(도착노드, 비용))
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();

        // distance[i] = i번 노드까지의 K개의 최단 경로를 저장하는 힙
        // (음수 비용을 저장하는 최소 힙 -> 양수 비용 기준 최대 힙)
        PriorityQueue<Long>[] distance = new PriorityQueue[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            distance[i] = new PriorityQueue<Long>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c)); // (도착노드, 비용)
        }

        // 다익스트라 알고리즘 큐 (전체 경로 탐색용)
        PriorityQueue<Node> q = new PriorityQueue<>();

        // 1. 시작 노드 설정
        q.add(new Node(1, 0L));
        distance[1].add(0L); // 음수 0은 0이므로 0L

        while (!q.isEmpty()) {
            Node node = q.poll();
            long dist = node.cost;
            int now = node.index;

            // 2. 현재 노드(now)와 연결된 인접 노드(neighbor) 확인
            for (Node neighbor : graph.get(now)) {
                // neighbor.index = 도착노드(b), neighbor.cost = 비용(c)
                long cost = dist + neighbor.cost;
                int nextNode = neighbor.index;

                // 3. K번째 경로 갱신
                // 3-1. 아직 K개의 경로가 채워지지 않았다면
                if (distance[nextNode].size() < k) {
                    distance[nextNode].add(-cost); // (음수로 저장)
                    q.add(new Node(nextNode, cost));
                }
                // 3-2. K개가 찼고, 새 경로(cost)가 K번째 경로(-distance[nextNode].peek())보다 작다면
                else if (cost < -distance[nextNode].peek()) {
                    distance[nextNode].poll(); // K번째 경로(가장 큰 값) 삭제
                    distance[nextNode].add(-cost); // 새 경로 추가 (음수로)
                    q.add(new Node(nextNode, cost));
                }
            }
        }

        // 4. 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (distance[i].size() == k) {
                // K번째 경로는 최소 힙의 루트(음수)를 뒤집은 값
                sb.append(-distance[i].peek()).append("\n");
            } else {
                sb.append(-1).append("\n");
            }
        }
        System.out.print(sb.toString());
    }
}