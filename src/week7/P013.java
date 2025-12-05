package week7;

import java.io.*;
import java.util.*;

public class P013 {
    public static void main(String[] args) throws IOException {
        // 속도를 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] x = new long[N + 1];
        long[] y = new long[N + 1];

        for (int i = 0; i < N; i++) {
            // 공백 기준으로 파싱
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Long.parseLong(st.nextToken());
            y[i] = Long.parseLong(st.nextToken());
        }

        // 마지막에 첫 번째 좌표 다시 추가 (신발끈 공식)
        x[N] = x[0];
        y[N] = y[0];

        long sum = 0;

        for (int i = 0; i < N; i++) {
            // (x1*y2 - x2*y1) 누적
            // long 범위 내에서 계산되므로 안전
            sum += x[i] * y[i + 1] - x[i + 1] * y[i];
        }

        // 절댓값 취하기
        // 여기서 2.0으로 나누지 말고, String.format을 이용해 출력 포맷을 지정
        // 넓이 = 0.5 * |sum|
        double area = Math.abs(sum) / 2.0;

        // "%.1f" : 소수점 첫째 자리까지 강제 출력 (지수 표현 방지)
        System.out.printf("%.1f", area);
    }
}
