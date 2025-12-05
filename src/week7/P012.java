package week7;

import java.io.*;
import java.util.*;

public class P012 {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long[][] positions = new long[N][4];

        // 좌표 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            positions[i][0] = Long.parseLong(st.nextToken());
            positions[i][1] = Long.parseLong(st.nextToken());
            positions[i][2] = Long.parseLong(st.nextToken());
            positions[i][3] = Long.parseLong(st.nextToken());
        }

        // 유니온 파인드 초기화
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }

        // 모든 쌍에 대해 교차 여부 확인 및 유니온
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isTwoLinesIntersecting(positions[i], positions[j])) {
                    union(i, j);
                }
            }
        }

        // 그룹 개수 및 가장 큰 그룹 크기 계산
        int groupCount = 0;
        int[] groupLineCounts = new int[N];

        for (int i = 0; i < N; i++) {
            int root = findParent(i);

            // 자신이 루트인 경우 그룹 개수 +1
            if (i == parents[i]) {
                groupCount++;
            }

            // 해당 그룹(루트)의 크기 증가
            groupLineCounts[root]++;
        }

        // 가장 큰 그룹의 크기 찾기
        int maxCount = 0;
        for (int count : groupLineCounts) {
            maxCount = Math.max(maxCount, count);
        }

        System.out.println(groupCount);
        System.out.println(maxCount);
    }

    // CCW: 1(반시계), -1(시계), 0(일직선)
    static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long result = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        if (result > 0) return 1;
        if (result < 0) return -1;
        return 0;
    }

    static boolean isTwoLinesIntersecting(long[] line1, long[] line2) {
        long x1 = line1[0], y1 = line1[1], x2 = line1[2], y2 = line1[3];
        long x3 = line2[0], y3 = line2[1], x4 = line2[2], y4 = line2[3];

        int ccw123 = ccw(x1, y1, x2, y2, x3, y3);
        int ccw124 = ccw(x1, y1, x2, y2, x4, y4);
        int ccw341 = ccw(x3, y3, x4, y4, x1, y1);
        int ccw342 = ccw(x3, y3, x4, y4, x2, y2);

        // 평행 또는 일직선 상에 있는 경우
        if (ccw123 * ccw124 == 0 && ccw341 * ccw342 == 0) {
            long mx1 = Math.min(x1, x2), mx2 = Math.max(x1, x2);
            long my1 = Math.min(y1, y2), my2 = Math.max(y1, y2);
            long mx3 = Math.min(x3, x4), mx4 = Math.max(x3, x4);
            long my3 = Math.min(y3, y4), my4 = Math.max(y3, y4);

            if (mx1 <= mx4 && mx3 <= mx2 && my1 <= my4 && my3 <= my2) {
                return true;
            }
        }
        // 교차하는 경우
        else {
            if (ccw123 * ccw124 <= 0 && ccw341 * ccw342 <= 0) {
                return true;
            }
        }
        return false;
    }

    static int findParent(int x) {
        if (parents[x] == x) return x;
        return parents[x] = findParent(parents[x]);
    }

    static void union(int x, int y) {
        int px = findParent(x);
        int py = findParent(y);

        if (px < py) parents[py] = px;
        else parents[px] = py;
    }
}