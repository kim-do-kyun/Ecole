package week7;

import java.util.Scanner;

public class P004 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        // 문제 조건이 N <= 1000 이므로 크기 1001로 고정
        int[] dp = new int[1001];

        // 초기값 설정
        dp[1] = 1;
        dp[2] = 2;

        // 3부터 n까지 반복
        for (int i = 3; i <= n; i++) {
            // 매 계산마다 10007로 나눈 나머지를 저장 (오버플로우 방지)
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
        }

        // 반복문이 끝난 뒤 결과 출력
        System.out.println(dp[n]);

        sc.close();
    }
}