package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P006 {

    static class Node implements Comparable<Node> {
        int index; // 노드 번호
        int cost;  // 해당 노드까지의 거리 (가중치)

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        // 거리를 기준으로 오름차순 정렬
        @Override
        public int compareTo(Node other) {
            // int 비교는 단순 뺄셈으로 가능
            return this.cost - other.cost;
        }
    }

    static final int INF = (int) 1e9; // 무한대 값
    static ArrayList<ArrayList<Node>> graph;
    static int[] distance;
    static int v, e, snode; // v: 정점 수, e: 간선 수, snode: 시작 노드

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 1. 시작 노드 설정
        pq.add(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int dist = node.cost; // 현재 노드까지의 거리
            int now = node.index; // 현재 노드 번호

            // 2. 이미 처리된 노드(더 짧은 경로를 찾은 경우)는 무시
            if (distance[now] < dist) {
                continue;
            }

            // 3. 현재 노드와 연결된 인접 노드 확인
            for (Node neighbor : graph.get(now)) {
                // neighbor.index = 인접 노드 번호
                // neighbor.cost = now에서 neighbor까지의 가중치
                int cost = dist + neighbor.cost; // 현재 노드를 거쳐 가는 비용

                // 4. 현재 노드를 거쳐 가는 것이 더 짧은 경우
                if (cost < distance[neighbor.index]) {
                    distance[neighbor.index] = cost;
                    // 우선순위 큐에 (갱신된 거리, 인접 노드) 추가
                    pq.add(new Node(neighbor.index, cost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        snode = Integer.parseInt(br.readLine());

        // 1. 그래프 및 최단 거리 테이블 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }
        distance = new int[v + 1];
        Arrays.fill(distance, INF);

        // 2. 간선 정보 입력
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());      // 출발
            int v_node = Integer.parseInt(st.nextToken()); // 도착
            int w = Integer.parseInt(st.nextToken());      // 가중치
            // u번 노드에서 v_node번 노드로 가는 가중치 w
            graph.get(u).add(new Node(v_node, w));
        }

        // 3. 다익스트라 알고리즘 수행
        dijkstra(snode);

        // 4. 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= v; i++) {
            if (distance[i] == INF) {
                sb.append("INF").append("\n");
            } else {
                sb.append(distance[i]).append("\n");
            }
        }
        System.out.print(sb.toString());
    }
}