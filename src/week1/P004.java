package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P004 {
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int count = 0;
        int currentSum = 0;
        int start = 1;

        // end 포인터가 1부터 n까지 순차적으로 이동
        for (int end = 1; end <= n; end++) {
            // 1. 윈도우 확장: 현재 end 값을 합계에 더함
            currentSum += end;

            // 2. 윈도우 축소: 합계가 n보다 크면, n보다 작거나 같아질 때까지 start를 이동시키며 값을 뺌
            while (currentSum > n) {
                currentSum -= start;
                start++;
            }

            // 3. 결과 확인: 합계가 정확히 n과 일치하면 경우의 수를 1 증가시킴
            if (currentSum == n) {
                count++;
            }
        }

        // 결과 출력
        System.out.println(count);

        br.close();
    }
}
