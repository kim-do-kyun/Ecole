package week7;

import java.io.*;
import java.util.*;

public class P011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long x1 = Long.parseLong(st.nextToken());
        long y1 = Long.parseLong(st.nextToken());
        long x2 = Long.parseLong(st.nextToken());
        long y2 = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        // 선분 2 (x3, y3, x4, y4)
        long x3 = Long.parseLong(st.nextToken());
        long y3 = Long.parseLong(st.nextToken());
        long x4 = Long.parseLong(st.nextToken());
        long y4 = Long.parseLong(st.nextToken());

        System.out.println(solution(x1, y1, x2, y2, x3, y3, x4, y4));
    }
    static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long result = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        if (result > 0) return 1;   // 반시계
        if (result < 0) return -1;  // 시계
        return 0;                   // 일직선
    }
    static int solution(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        int ccw123 = ccw(x1, y1, x2, y2, x3, y3);
        int ccw124 = ccw(x1, y1, x2, y2, x4, y4);
        int ccw341 = ccw(x3, y3, x4, y4, x1, y1);
        int ccw342 = ccw(x3, y3, x4, y4, x2, y2);
        boolean isStraight = (ccw123 * ccw124 == 0) && (ccw341 * ccw342 == 0);

        if (isStraight) {
            // 포개짐 여부 확인 (Bounding Box Check)
            // 각 선분의 양 끝점을 정렬하여 min, max를 구함
            if (Math.min(x1, x2) <= Math.max(x3, x4) &&
                    Math.min(x3, x4) <= Math.max(x1, x2) &&
                    Math.min(y1, y2) <= Math.max(y3, y4) &&
                    Math.min(y3, y4) <= Math.max(y1, y2)) {
                return 1;
            }
        }
        else {
            // 등호(<=)가 들어가는 이유는 끝점에서 만나는 경우도 교차로 치기 때문
            if (ccw123 * ccw124 <= 0 && ccw341 * ccw342 <= 0) {
                return 1;
            }
        }
        return 0;
    }
}