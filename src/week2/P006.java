package week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P006 {
    static int[] numList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        numList = new int[n];
        for (int i = 0; i < n; i++) {
            numList[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        RadixSort(numList, 5);
        for (int i = 0; i < n; i++) {
            bw.write(numList[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void RadixSort(int[] numList, int maxSize) {
        int[] output = new int[numList.length];
        int digit = 1;
        int count = 0;
        while(count != maxSize) {
            int[] bucket = new int[10];
            for (int j : numList) {
                bucket[(j / digit) % 10]++;
            }
            for (int i = 1; i < 10; i++) {
                bucket[i] += bucket[i - 1];
            }
            for (int i = numList.length - 1; i >= 0; i--) {
                output[bucket[(numList[i] / digit % 10)] - 1] = numList[i];
                bucket[(numList[i] / digit % 10)]--;
            }
            for (int i = 0; i < numList.length; i++) {
                numList[i] = output[i];
            }
            digit = digit * 10;
            count++;
        }
    }
}
