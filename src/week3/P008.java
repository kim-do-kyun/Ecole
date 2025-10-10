package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P008 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        long result = n;

        // 2부터 n의 제곱근까지 반복
        for (long i = 2; i * i <= n; i++) {
            // i가 n의 소인수인지 확인
            if (n % i == 0) {
                // 오일러 피 함수 공식 적용
                // result = result * (1 - 1/i)
                result = result - result / i;

                // 해당 소인수를 모두 제거
                while (n % i == 0) {
                    n /= i;
                }
            }
        }

        // 루프가 끝난 후에도 n이 1보다 크다면,
        // 남은 n은 n의 소인수 중 하나임 (제곱근보다 큰 소인수)
        if (n > 1) {
            result = result - result / n;
        }

        System.out.println(result);
    }
}
