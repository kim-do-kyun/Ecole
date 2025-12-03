package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class P004 {
    // 모듈러 연산 상수
    static final int MOD = 10007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N, K 입력
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // K가 유효하지 않은 범위일 경우 처리
        if (K < 0 || K > N) {
            System.out.println(0);
            return;
        }

        // DP 테이블 초기화. DP[i][j]는 iCj (i choose j)의 결과
        int[][] DP = new int[N + 1][N + 1];

        // 파스칼의 삼각형을 이용한 DP 테이블 채우기
        for (int i = 0; i <= N; i++) {
            DP[i][0] = 1; // nC0는 항상 1

            // iC1부터 iC(i-1)까지 계산
            for (int j = 1; j < i; j++) {
                // 파스칼의 항등식: iCj = (i-1)C(j-1) + (i-1)Cj
                // 모든 중간 결과에 모듈러 연산 적용
                DP[i][j] = (DP[i - 1][j - 1] + DP[i - 1][j]) % MOD;
            }

            DP[i][i] = 1; // nCn은 항상 1
        }

        // 최종 결과 출력
        System.out.println(DP[N][K]);
    }
}
