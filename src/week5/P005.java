package week5;

import java.io.*;
import java.util.*;

public class P005 {
    static int n;
    static int[] arr;
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        k = Integer.parseInt(br.readLine());

        // k번 노드 삭제 수행
        dfs(k);

        int count = 0;
        for (int i = 0; i < n; i++) {
            // 삭제되지 않은 노드 중에서
            if (arr[i] != -2) {
                // i번 노드를 부모로 가지는 노드가 있는지 확인
                boolean isLeaf = true;
                for (int j = 0; j < n; j++) {
                    if (arr[j] == i) { // i를 부모로 가지는 노드 j가 존재하면 리프 노드 아님
                        isLeaf = false;
                        break;
                    }
                }
                // 자식이 없다면 리프 노드
                if (isLeaf) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    // 삭제할 노드와 그 자손들을 -2로 표시하는 DFS 함수
    static void dfs(int num) {
        arr[num] = -2; // 현재 노드 삭제 표시
        for (int i = 0; i < n; i++) {
            // 현재 노드(num)를 부모로 가지는 노드(i)를 찾아 재귀 호출
            if (arr[i] == num) {
                dfs(i);
            }
        }
    }
}
