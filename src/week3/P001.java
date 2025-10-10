package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P001 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] nList = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nList[i] = Integer.parseInt(st.nextToken());
        }
        // 이진 탐색을 위해 배열을 정렬
        Arrays.sort(nList);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken()); // 찾아야 할 숫자

            int left = 0;
            int right = n - 1;
            boolean found = false; // 찾았는지 여부를 저장하는 변수

            // 이진 탐색 시작
            while (left <= right) {
                int mid = (left + right) / 2;

                if (target > nList[mid]) {
                    left = mid + 1;
                } else if (target < nList[mid]) {
                    right = mid - 1;
                } else { // 숫자를 찾은 경우
                    found = true;
                    break;
                }
            }
            if (found) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }
        System.out.print(sb.toString());
    }
}
