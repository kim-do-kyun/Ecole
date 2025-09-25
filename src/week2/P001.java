package week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P001 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] numList = new int[n];
        for(int i = 0; i < n; i++) {
            numList[i] = Integer.parseInt(br.readLine());
        }
        for(int i = 0; i < n-1; i++) {
            for(int j = 0; j < n-1-i; j++) {
                if (numList[j] > numList[j+1]) {
                    int temp = numList[j];
                    numList[j] = numList[j+1];
                    numList[j+1] = temp;
                }
            }
        }

        for (int i : numList) {
            bw.write(i + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
