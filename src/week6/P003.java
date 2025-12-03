package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Stack;

public class P003 {

    // 전역 변수 선언
    static int N;                           // 노드의 개수
    static int LENGTH;                      // 희소 배열의 크기 (log2(N) + 1)
    static int[][] parent;                  // parent[노드][i]: 노드의 2^i번째 부모
    static int[] depth;                     // depth[노드]: 루트로부터의 깊이
    static ArrayList<ArrayList<Integer>> graph; // 인접 리스트로 그래프 표현

    /**
     * @brief 스택 기반 DFS를 통해 각 노드의 깊이와 1번째 부모(2^0)를 설정합니다.
     */
    public static void calculateBaseInfo() {
        // (현재 노드, 깊이, 부모 노드)
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{1, 0, 0});

        boolean[] visited = new boolean[N + 1];

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int curr = current[0];
            int dep = current[1];
            int p = current[2];

            if (visited[curr]) continue;
            visited[curr] = true;

            depth[curr] = dep;
            parent[curr][0] = p; // 1번째 부모 저장

            for (int next : graph.get(curr)) {
                // 부모 노드가 아닌 경우에만 스택에 추가
                if (next != p) {
                    stack.push(new int[]{next, dep + 1, curr});
                }
            }
        }
    }

    /**
     * @brief 희소 배열(Sparse Array)을 동적 계획법으로 채웁니다.
     */
    public static void fillSparseArray() {
        for (int i = 1; i < LENGTH; i++) {
            for (int j = 1; j <= N; j++) {
                // parent[j][i] = parent[ parent[j][i-1] ][i-1]
                int intermediateParent = parent[j][i - 1];
                parent[j][i] = parent[intermediateParent][i - 1];
            }
        }
    }

    /**
     * @brief 이진 리프팅(Binary Lifting)을 사용하여 두 노드 a와 b의 최소 공통 조상(LCA)을 찾습니다.
     */
    public static int lca(int a, int b) {
        // 1. b의 깊이가 더 깊거나 같도록 설정
        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // 2. 깊이 맞추기 (O(log N))
        int diff = depth[b] - depth[a];
        for (int i = 0; i < LENGTH; i++) {
            // diff의 i번째 비트가 1이면 2^i만큼 점프
            if ((diff & (1 << i)) > 0) {
                b = parent[b][i];
            }
        }

        // 깊이가 같아진 후 두 노드가 같다면 바로 공통 조상
        if (a == b) {
            return a;
        }

        // 3. 공통 조상의 바로 아래까지 동시에 올라가기 (O(log N))
        for (int i = LENGTH - 1; i >= 0; i--) {
            // 두 노드의 2^i 번째 부모가 다르면
            if (parent[a][i] != parent[b][i]) {
                // 동시에 점프
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        // LCA 바로 아래에 위치한 두 노드의 부모가 LCA
        return parent[a][0];
    }

    public static void main(String[] args) throws IOException {
        // 빠른 입출력을 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // N 입력
        N = Integer.parseInt(br.readLine());

        // LENGTH 계산 (N이 1 이상일 경우)
        if (N > 0) {
            LENGTH = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        } else {
            LENGTH = 1;
        }

        // 배열 및 리스트 초기화 (1-based index)
        parent = new int[N + 1][LENGTH];
        depth = new int[N + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보 입력
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u); // 양방향 연결
        }

        // 전처리: 깊이 및 희소 배열 계산
        calculateBaseInfo();
        fillSparseArray();

        // LCA 질의 개수 M 입력
        int M = Integer.parseInt(br.readLine());

        // M번의 LCA 질의 처리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(lca(u, v)).append("\n");
        }

        // 결과 한 번에 출력
        System.out.print(sb);
    }
}
