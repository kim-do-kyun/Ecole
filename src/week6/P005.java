package week6;

import java.io.*;
import java.util.StringTokenizer;

public class P005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // K가 0이거나 N인 경우 이항 계수는 항상 1
        if (K == 0 || K == N) {
            System.out.println(1);
            return;
        }

        int[][] pascal = new int[N + 1][];

        if (N > 1) {
            pascal[1] = new int[]{1, 1};
        }

        // DP 수행
        for (int depth = 2; depth < N; depth++) {
            // 해당 depth(행)의 크기는 depth + 1이어야 함 (예: 2번째 행은 1, 2, 1로 3개)
            pascal[depth] = new int[depth + 1];

            pascal[depth][0] = 1; // 첫 번째 원소

            // 중간 원소들 계산
            for (int idx = 1; idx < depth; idx++) {
                pascal[depth][idx] = (pascal[depth - 1][idx - 1] + pascal[depth - 1][idx]) % 10007;
            }

            pascal[depth][depth] = 1; // 마지막 원소
        }

        int result = (pascal[N - 1][K - 1] + pascal[N - 1][K]) % 10007;
        System.out.println(result);
    }
}
