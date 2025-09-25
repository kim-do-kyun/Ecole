package week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P005 {
    static int[] numList, tmp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        numList = new int[n+1];
        tmp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            numList[i] = Integer.parseInt(br.readLine());
        }
        mergeSort(1, n);

        for(int i = 1; i <= n; i++) {
            bw.write(numList[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void mergeSort(int start, int end) {
        if (end - start < 1) return;
        int mid = start + (end - start) / 2;
        mergeSort(start, mid);
        mergeSort(mid + 1, end);
        for(int i = start; i <= end; i++) {
            tmp[i] = numList[i];
        }
        int k = start;
        int index1 = start;
        int index2 = mid + 1;
        while (index1 <= mid && index2 <= end) {
            if (tmp[index1] > tmp[index2]) {
                numList[k] = tmp[index2];
                k++;
                index2++;
            } else {
                numList[k] = tmp[index1];
                k++;
                index1++;
            }
        }
        while (index1 <= mid) {
            numList[k] = tmp[index1];
            k++;
            index1++;
        }
        while (index2 <= end) {
            numList[k] = tmp[index2];
            k++;
            index2++;
        }
    }
}
