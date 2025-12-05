package week7;

import java.util.Scanner;

public class P006 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        // DP 테이블
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            // 공백 없이 들어오는 문자열(예: "01001")을 받음
            String line = sc.next();
            for (int j = 0; j < m; j++) {
                // 문자를 숫자로 변환 ('0'을 빼줌)
                arr[i][j] = line.charAt(j) - '0';
            }
        }

        long answer = 0; // 넓이가 커질 수 있으므로 long 사용 권장 (이 문제 범위에선 int도 무관)

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = arr[i][j];
                }
                else if (arr[i][j] == 0) {
                    dp[i][j] = 0;
                }
                else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
                answer = Math.max(answer, dp[i][j]);
            }
        }
        System.out.println(answer * answer);
        sc.close();
    }
}