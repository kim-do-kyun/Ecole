package week7;

import java.util.Scanner;

public class P007 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        int[][] dp = new int[N][N];

        for (int term = 1; term < N; term++) {

            // start: 시작 행렬 인덱스 - 파이썬의 range(N)
            for (int start = 0; start < N; start++) {

                // 범위를 벗어나면 해당 term 루프는 더 이상 볼 필요 없음 (break)
                if (start + term >= N) {
                    break;
                }

                int end = start + term; // 끝 행렬 인덱스

                // 최솟값을 구하기 위해 큰 값으로 초기화
                dp[start][end] = Integer.MAX_VALUE;

                // t: 분할 지점 - 파이썬의 range(start, start + term)
                // start ~ t (왼쪽) / t+1 ~ end (오른쪽) 으로 나눔
                for (int t = start; t < end; t++) {
                    int cost = dp[start][t] + dp[t + 1][end]
                            + arr[start][0] * arr[t][1] * arr[end][1];

                    dp[start][end] = Math.min(dp[start][end], cost);
                }
            }
        }
        System.out.println(dp[0][N - 1]);
        sc.close();
    }
}