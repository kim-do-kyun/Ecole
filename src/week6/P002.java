package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P002 {
    static int N;                                   // 노드의 개수
    static int[] parent;                            // 각 노드의 부모 노드 정보
    static int[] depth;                             // 각 노드까지의 깊이
    static ArrayList<ArrayList<Integer>> graph;     // 인접 리스트로 그래프 표현

    public static void dfs(int curr, int dep, int pcurr) {
        depth[curr] = dep;
        parent[curr] = pcurr;

        for (int next : graph.get(curr)) {
            // 부모 노드가 아닌 경우에만 재귀 호출 (무방향 그래프 탐색 시 중요)
            if (next != pcurr) {
                dfs(next, dep + 1, curr);
            }
        }
    }
    public static int lca(int a, int b) {
        // 1. 깊이 맞추기 (항상 b가 더 깊거나 같은 깊이가 되도록 스왑)
        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // 깊이가 같아질 때까지 깊이가 더 깊은 노드(b)를 부모 노드로 이동
        while (depth[a] != depth[b]) {
            b = parent[b];
        }

        // 2. 노드 맞추기 (두 노드가 같아질 때까지 동시에 부모 노드로 이동)
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        // 배열 및 리스트 초기화 (노드 번호 1부터 N까지 사용)
        parent = new int[N + 1];
        depth = new int[N + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 트리의 연결 정보 입력 (N-1개의 간선)
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u); // 양방향 연결
        }
        // DFS를 통해 깊이와 부모 노드 설정
        // 루트 노드를 1로 가정, 깊이 0부터 시작, 부모 노드는 0
        dfs(1, 0, 0);

        // LCA 질의 개수 입력
        int M = Integer.parseInt(br.readLine());

        // M번의 LCA 질의 처리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(lca(u, v)).append("\n");
        }
        System.out.print(sb);
    }
}
