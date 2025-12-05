package week7;

import java.util.Scanner;

public class P003 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        // 입력값이 0인 경우에 대한 예외 처리 (문제 조건에 따라 필요할 수 있음)
        if (n == 0) {
            System.out.println(0);
            return;
        }

        // int가 아닌 long 배열 선언
        long[] dp = new long[n + 1];

        dp[1] = 1;
        // dp[0]은 자동으로 0이므로 생략 가능

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        System.out.println(dp[n]);
        sc.close();
    }
}