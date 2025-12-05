package week7;

import java.io.*;
import java.util.*;

public class P008 {
    static int N;
    static int[][] world;
    static int[][] dp;
    static final int INF = 1000000000; // 10억 (무한대 대용)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        world = new int[N][N];
        dp = new int[N][1 << N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                world[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }
        System.out.println(dfs(0, 1));
    }

    static int dfs(int now, int visited) {
        // 모든 도시를 방문했을 경우 ((1<<N) - 1 은 모든 비트가 1인 상태)
        if (visited == (1 << N) - 1) {
            // 현재 도시에서 출발 도시(0)로 돌아갈 수 있으면 그 비용 리턴
            if (world[now][0] != 0) {
                return world[now][0];
            }
            // 돌아갈 수 없으면 무한대 리턴
            return INF;
        }
        if (dp[now][visited] != -1) {
            return dp[now][visited];
        }
        dp[now][visited] = INF; // 최솟값을 구하기 위해 초기화
        for (int next = 0; next < N; next++) {
            if (world[now][next] == 0 || (visited & (1 << next)) != 0) {
                continue;
            }
            int cost = dfs(next, visited | (1 << next));
            if(cost != INF) {
                dp[now][visited] = Math.min(dp[now][visited], cost + world[now][next]);
            }
        }
        return dp[now][visited];
    }
}