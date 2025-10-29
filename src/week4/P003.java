package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P003 {
    static int[] parent;

    static int getParent(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = getParent(parent[x]);
    }

    static void unionParent(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    static boolean findParent(int a, int b) {
        // 두 원소의 루트 노드가 같은지 비교
        return getParent(a) == getParent(b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 1. parent 배열 초기화 (각 노드의 부모를 자기 자신으로 설정)
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        // 2. m개의 연산(Operation) 수행
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op == 0) {
                // 0번 연산: 합집합 (Union)
                unionParent(a, b);
            } else {
                // 1번 연산: 같은 집합인지 확인 (Find)
                sb.append(findParent(a, b) ? "YES" : "NO").append("\n");
            }
        }

        // 3. 결과 출력
        System.out.print(sb.toString());
    }
}
