package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class P001 {
    static long[] tree;
    static long[] arr;
    static int N;

    // start, end: 숫자 배열의 인덱스 범위
    // idx: 세그먼트 트리의 현재 노드 인덱스 (1부터 시작)
    public static long makeTree(int start, int end, int idx) {
        if (start == end) {
            tree[idx] = arr[start];
            return tree[idx];
        }

        int mid = (start + end) / 2;
        tree[idx] = makeTree(start, mid, idx * 2) + makeTree(mid + 1, end, idx * 2 + 1);

        return tree[idx];
    }

    // target: 수정할 값의 숫자 배열 인덱스 (0-based)
    // diff: 기존 값에 '얼만큼 더해야 하는지의 값' (변경 후 값 - 변경 전 값)
    public static void updateTree(int start, int end, int idx, int target, long diff) {
        // target이 현재 노드가 담당하는 범위를 벗어난 경우
        if (target < start || target > end) {
            return;
        }

        // 현재 노드에 diff 반영
        tree[idx] += diff;

        // 리프 노드에 도달했으면 종료
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        // 자식 노드 재귀 호출
        updateTree(start, mid, idx * 2, target, diff);
        updateTree(mid + 1, end, idx * 2 + 1, target, diff);
    }

    // left, right: 구하고자 하는 범위의 숫자 배열 인덱스 (0-based)
    public static long sumTree(int start, int end, int idx, int left, int right) {
        // [start, end]가 [left, right]와 완전히 벗어난 경우
        if (right < start || left > end) {
            return 0;
        }

        // [start, end]가 [left, right]에 완전히 포함되는 경우
        if (left <= start && right >= end) {
            return tree[idx];
        }

        int mid = (start + end) / 2;
        return sumTree(start, mid, idx * 2, left, right) + sumTree(mid + 1, end, idx * 2 + 1, left, right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 숫자의 개수
        int M = Integer.parseInt(st.nextToken()); // 변경 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간 합 횟수

        arr = new long[N];
        tree = new long[N * 4];

        // 원본 배열 값 입력
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        makeTree(0, N - 1, 1);
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken()); // c는 값 또는 범위 끝 인덱스일 수 있음

            if (a == 1) { // 1: 값 변경
                int targetIndex = b - 1;
                long newValue = c;

                // 기존 값과 새로운 값의 차이 (diff)를 계산하여 updateTree 호출
                long diff = newValue - arr[targetIndex];

                updateTree(0, N - 1, 1, targetIndex, diff);

                // 원본 배열 값 변경 (필수)
                arr[targetIndex] = newValue;

            } else { // 2: 구간 합
                int left = b - 1;
                int right = (int) c - 1; // c가 인덱스이므로 int로 변환

                long result = sumTree(0, N - 1, 1, left, right);
                sb.append(result).append("\n");
            }
        }
        System.out.print(sb);
    }
}