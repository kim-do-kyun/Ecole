package week7;

import java.util.Scanner;

public class P002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        // 시간(T)과 금액(P)을 저장할 배열
        int[] T = new int[N];
        int[] P = new int[N];

        for (int i = 0; i < N; i++) {
            T[i] = sc.nextInt();
            P[i] = sc.nextInt();
        }

        // DP 테이블 생성 (N+1 크기, 0으로 자동 초기화)
        int[] dp = new int[N + 1];

        // 뒤에서부터 역순으로 반복 (N-1 부터 0까지)
        for (int i = N - 1; i >= 0; i--) {
            // 상담 기간이 남은 기간(N)을 초과하는 경우
            if (i + T[i] > N) {
                dp[i] = dp[i + 1]; // 상담 불가, 이전(사실상 다음 날짜) 값 가져옴
            } else {
                // 상담을 안 하는 경우 vs 상담을 하는 경우 중 최댓값
                // dp[i + T[i]]는 해당 상담이 끝난 시점의 누적 최대 이익
                dp[i] = Math.max(dp[i + 1], P[i] + dp[i + T[i]]);
            }
        }

        System.out.println(dp[0]);
        sc.close();
    }
}
