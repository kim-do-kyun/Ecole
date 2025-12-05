package week7;

import java.io.*;
import java.util.*;

public class P009 {
    // dp 배열에 저장할 정보를 담는 클래스 (LIS 내 인덱스, 실제 값)
    static class Node {
        int idx;
        int val;

        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    // 이분 탐색 메서드 (Lower Bound 역할)
    static int binarySearch(ArrayList<Integer> lis, int key) {
        int start = 0;
        int end = lis.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (lis.get(mid) == key) {
                return mid;
            } else if (lis.get(mid) < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    public static void main(String[] args) throws IOException {
        // 입력을 빠르게 받기 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // LIS 배열 (현재 만들어지고 있는 증가 수열)
        ArrayList<Integer> lis = new ArrayList<>();
        lis.add(arr[0]);

        // 추적용 기록 배열 (Python 코드의 dp 리스트 대응)
        ArrayList<Node> record = new ArrayList<>();
        record.add(new Node(0, arr[0]));

        for (int i = 1; i < n; i++) {
            // 현재 값이 LIS의 마지막 값보다 크면 뒤에 추가
            if (arr[i] > lis.get(lis.size() - 1)) {
                lis.add(arr[i]);
                record.add(new Node(lis.size() - 1, arr[i]));
            }
            // 아니면 이분 탐색으로 들어갈 위치를 찾아 교체
            else {
                int idx = binarySearch(lis, arr[i]);
                lis.set(idx, arr[i]);
                record.add(new Node(idx, arr[i]));
            }
        }

        // 1. LIS 길이 출력
        System.out.println(lis.size());

        // 2. 역추적 (Backtracking)
        int lastIdx = lis.size() - 1;
        Stack<Integer> result = new Stack<>(); // 역순 출력을 위해 스택 사용

        // 뒤에서부터 거꾸로 탐색
        for (int i = record.size() - 1; i >= 0; i--) {
            Node node = record.get(i);
            if (node.idx == lastIdx) {
                result.push(node.val);
                lastIdx--;
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        while (!result.isEmpty()) {
            sb.append(result.pop()).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
