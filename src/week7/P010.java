package week7;

import java.util.Scanner;

public class P010 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // P1 입력
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();

        // P2 입력
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();

        // P3 입력
        int x3 = sc.nextInt();
        int y3 = sc.nextInt();

        // CCW 공식 계산 (외적값)
        // (x1y2 + x2y3 + x3y1) - (x2y1 + x3y2 + x1y3)
        int res = (x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3);

        // 결과 판별
        if (res > 0) {
            System.out.println(1); // 반시계
        } else if (res < 0) {
            System.out.println(-1); // 시계
        } else {
            System.out.println(0); // 일직선
        }

        sc.close();
    }
}