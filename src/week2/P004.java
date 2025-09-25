package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P004 {
    static int[] numList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        numList = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(0, n - 1, k - 1);
        System.out.println(numList[k - 1]);
    }

    private static void quickSort(int start, int end, int k) {
        if (start < end) {
            int pivot = partition(start, end);
            if (pivot < k) {
                quickSort(pivot + 1, end, k);
            } else if (k < pivot) {
                quickSort(start, pivot - 1, k);
            } else {
                return; // pivot == k 이면 탐색 종료
            }
        }
    }

    private static int partition(int start, int end) {
        // 데이터가 2개인 경우 특별 처리
        if (start + 1 == end) {
            if (numList[start] > numList[end]) {
                swap(start, end);
            }
            return end;
        }
        // 중간 값을 피벗으로 선택하고 맨 앞으로 이동
        int mid = (start + end) / 2;
        swap(start, mid);

        int pivotIndex = start; // 피벗의 '인덱스'
        int i = start + 1;
        int j = end;

        while (i <= j) {
            // 피벗보다 큰 수가 나올 때까지 i 증가
            // Python 코드의 `i < len(numList) - 1` 와 `j > 0` 경계 조건도 그대로 유지
            while (i < numList.length - 1 && numList[pivotIndex] > numList[i]) {
                i++;
            }
            // 피벗보다 작은 수가 나올 때까지 j 감소
            while (j > 0 && numList[pivotIndex] < numList[j]) {
                j--;
            }

            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        // 피벗 값을 최종 위치(j)로 이동
        int tmp = numList[pivotIndex];
        numList[start] = numList[j];
        numList[j] = tmp;

        return j;
    }

    private static void swap(int a, int b) {
        int tmp = numList[a];
        numList[a] = numList[b];
        numList[b] = tmp;
    }
}