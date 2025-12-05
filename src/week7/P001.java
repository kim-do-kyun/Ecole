package week7;

import java.util.Scanner;

public class P001 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 수 입력받기
        int x = sc.nextInt();

        // DP 테이블 생성 (0으로 초기화됨)
        // 1-based 인덱싱을 위해 크기를 x + 1로 설정
        int[] d = new int[x + 1];

        // 2부터 x까지 반복 (Bottom-Up)
        for (int i = 2; i <= x; i++) {
            // 1. 1을 빼는 연산 (기본 전제)
            d[i] = d[i - 1] + 1;

            // 2. 2로 나누어 떨어질 때, 2로 나누는 연산과 비교
            if (i % 2 == 0) {
                d[i] = Math.min(d[i], d[i / 2] + 1);
            }

            // 3. 3으로 나누어 떨어질 때, 3으로 나누는 연산과 비교
            if (i % 3 == 0) {
                d[i] = Math.min(d[i], d[i / 3] + 1);
            }
        }

        System.out.println(d[x]);
        sc.close();
    }
}