package week6;

import java.io.*;
import java.util.StringTokenizer;

public class P006 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 1. T 입력
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            // 2. N, M 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // 3. dp 테이블 정의 (기본값 0으로 자동 초기화됨)
            int[][] D = new int[N + 1][M + 1];

            // 4. dp 테이블 채우기
            // 초기값 설정 (서쪽 사이트가 1개일 때, 0개일 때)
            for (int j = 0; j <= M; j++) {
                D[0][j] = 1; // 파이썬 코드 로직 유지
                if (j > 0) D[1][j] = j;
            }

            // 점화식 적용
            for (int i = 2; i <= N; i++) {
                for (int j = i; j <= M; j++) {
                    D[i][j] = D[i - 1][j - 1] + D[i][j - 1];
                }
            }

            // 5. 결과 저장
            sb.append(D[N][M]).append('\n');
        }

        // 전체 출력
        System.out.print(sb);
    }
}
