package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        int result = 0;
        // 가장 가치가 큰 동전부터 확인하기 위해 배열을 거꾸로 순회
        for (int i = n - 1; i >= 0; i--) {
            if (coins[i] <= k) {
                result += (k / coins[i]);
                k %= coins[i];
            }
            if (k == 0) break;
        }
        System.out.println(result);
    }
}
