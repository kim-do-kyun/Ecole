package week6;

import java.io.*;
import java.util.*;

public class P007 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int type = Integer.parseInt(st.nextToken());

        // 1부터 N까지 숫자를 관리할 리스트 (Python의 num)
        ArrayList<Integer> num = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            num.add(i);
        }

        // 팩토리얼 값을 미리 계산하거나 함수로 사용 (20!은 long 범위)
        // 여기서는 간단하게 함수 호출 방식 사용

        if (type == 1) {
            long K = Long.parseLong(st.nextToken());
            K -= 1; // 0-index 보정

            StringBuilder sb = new StringBuilder();

            // N부터 1까지 감소하며 처리
            for (int i = N; i > 0; i--) {
                long f = factorial(i - 1);

                // 몇 번째 구간인지 확인
                int idx = (int) (K / f);

                // 해당 숫자 가져오기 및 리스트에서 제거
                sb.append(num.get(idx)).append(" ");
                num.remove(idx);

                K %= f;
            }
            System.out.println(sb.toString().trim());

        } else if (type == 2) {
            int[] inputData = new int[N];
            for (int i = 0; i < N; i++) {
                inputData[i] = Integer.parseInt(st.nextToken());
            }

            long result = 0;

            for (int i = 0; i < N; i++) {
                long f = factorial(N - 1 - i);

                // 현재 숫자가 남은 숫자들 중 몇 번째인지(index) 찾기
                int idx = num.indexOf(inputData[i]);

                result += idx * f;
                num.remove(idx); // 사용한 숫자 제거
            }

            // 1-index 복구 후 출력
            System.out.println(result + 1);
        }
    }

    // 팩토리얼 함수 (long 반환 필수)
    public static long factorial(int n) {
        long val = 1;
        for (int i = 2; i <= n; i++) {
            val *= i;
        }
        return val;
    }
}
