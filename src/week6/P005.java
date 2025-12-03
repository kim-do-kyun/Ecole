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

        // 파스칼 삼각형을 저장할 2차원 배열 (가변 배열)
        // N번째 줄까지 계산하기 위해 크기 확보
        int[][] pascal = new int[N + 1][];

        // 초기값 설정 (n=1 일 때: [1, 1])
        // 파이썬 코드의 로직을 그대로 따름
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

        // 결과 출력 (파이썬 코드의 마지막 계산식 반영)
        // N-1행의 값을 이용해 N행 K열의 값을 도출
        int result = (pascal[N - 1][K - 1] + pascal[N - 1][K]) % 10007;
        System.out.println(result);
    }
}
