package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 노드(학생) 수
        int m = Integer.parseInt(st.nextToken()); // 간선(키 비교) 수

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inDegree = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // a가 b보다 앞에 서야 함 (a -> b)
            graph.get(a).add(b);
            // b의 진입 차수 증가
            inDegree[b]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int s = queue.poll();
            sb.append(s).append(" ");

            for (int adj_s : graph.get(s)) {
                inDegree[adj_s]--;

                if (inDegree[adj_s] == 0) {
                    queue.add(adj_s);
                }
            }
        }
        System.out.println(sb.toString().trim());
    }
}