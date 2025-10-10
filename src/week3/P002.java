package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] times = new int[n];
        int start = 0; // 이진 탐색 시작점 (가장 긴 강의의 길이)
        int end = 0;   // 이진 탐색 끝점 (모든 강의 길이의 합)

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            times[i] = Integer.parseInt(st.nextToken());
            end += times[i];
            if (start < times[i]) {
                start = times[i];
            }
        }

        while (start <= end) {
            int mid = (start + end) / 2;    // 테스트 해볼 블루레이의 크기
            int count = 0;
            int tmp = 0;

            for (int t : times) {
                if (tmp + t > mid) {
                    count++;
                    tmp = 0;
                }
                tmp += t;
            }

            if (tmp != 0) count++;

            if (count > m) start = mid + 1;
            else end = mid -1;
        }
        System.out.println(start);
    }
}
