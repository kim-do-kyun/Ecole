package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        int count = 0;

        for (int k = 0; k < n; k++) {
            int target = numbers[k];
            int start = 0;
            int end = n - 1;

            while (start < end) {
                // Case 1: 포인터가 자기 자신을 가리키는 경우, 건너뛴다.
                if (start == k) {
                    start++;
                    continue;
                }
                if (end == k) {
                    end--;
                    continue;
                }
                int currentSum = numbers[start] + numbers[end];
                // Case 2: 두 수의 합이 목표값과 같은 경우
                if (currentSum == target) {
                    count++;
                    break;
                }
                // Case 3: 합이 목표값보다 작은 경우
                else if (currentSum < target) {
                    start++;
                }
                // Case 4: 합이 목표값보다 큰 경우
                else {
                    end--;
                }
            }
        }
        System.out.println(count);
        br.close();
    }
}
