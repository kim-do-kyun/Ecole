package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class P005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] meeting_times = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meeting_times[i][0] = Integer.parseInt(st.nextToken()); // 시작 시간
            meeting_times[i][1] = Integer.parseInt(st.nextToken()); // 종료 시간
        }

        // 회의 시간을 정렬 (핵심 로직)
        // 1. 종료 시간을 기준으로 오름차순 정렬
        // 2. 종료 시간이 같다면, 시작 시간을 기준으로 오름차순 정렬
        Arrays.sort(meeting_times, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });

        int count = 0;
        int prevEndTime = 0;

        for (int i = 0; i < n; i++) {
            // 이전 회의 종료 시간보다 현재 회의 시작 시간이 크거나 같다면
            if (meeting_times[i][0] >= prevEndTime) {
                prevEndTime = meeting_times[i][1];
                count++;
            }
        }
        System.out.println(count);

    }
}
