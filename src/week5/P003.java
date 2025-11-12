package week5;

import java.io.*;
import java.util.*;

public class P003 {
    static int[] parent;

    // 간선 정보를 저장할 클래스 (정렬을 위해 Comparable 구현)
    static class Edge implements Comparable<Edge> {
        int u, v, c;

        Edge(int u, int v, int c) {
            this.u = u;
            this.v = v;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return this.c - o.c; // 비용 기준 오름차순 정렬
        }
    }

    // Union-Find 알고리즘
    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        // 경로 압축 (Path Compression)
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            if (x <= y) {
                parent[y] = x;
            } else {
                parent[x] = y;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }

        parent = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        long res = 0; // 결과값 (비용 합)

        // 간선을 최소 비용 순으로 오름차순 정렬
        Collections.sort(edges);

        // 크루스칼 알고리즘
        for (Edge edge : edges) {
            if (find(edge.u) != find(edge.v)) { // 부모 노드가 다름
                union(edge.u, edge.v); // 최소 신장 트리에 포함시킴
                res += edge.c;
            }
        }

        System.out.println(res);
    }
}
