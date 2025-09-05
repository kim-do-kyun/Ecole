package week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class P001 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] numList = new int[n];
        for (int i = 0; i < n; i++) {
            numList[i] = Integer.parseInt(br.readLine());
        }

        int[] result = mergeSort(numList);

        for (int num : result) {
            bw.write(num + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] mergeSort(int[] arr) {
        // 배열의 크기가 1 이하면 이미 정렬된 것으로 생각
        if (arr.length <= 1) {
            return arr;
        }

        // 1. 분할 (Divide)
        int mid = arr.length / 2;

        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        // 2. 정복 (Conquer) & 결합 (Combine)
        return merge(mergeSort(left), mergeSort(right));
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }
}
