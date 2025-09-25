package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] pList = new int[n];
        int[] sList = new int[n];
        for(int i = 0; i < n; i++) {
            pList[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i < n; i++) {
            int insertPoint = i;
            int insertValue = pList[i];
            for (int j=i-1; j>=0; j--) {
                if (pList[j] < pList[i]) {
                    insertPoint = j + 1;
                    break;
                }
                if (j == 0) {
                    insertPoint = 0;
                }
            }
            for(int j=i; j>insertPoint; j--) {
                pList[j] = pList[j-1];
            }
            pList[insertPoint] = insertValue;
        }
        sList[0] = pList[0];
        for (int i = 1; i < n; i++) {
            sList[i] = sList[i - 1] + pList[i];
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += sList[i];
        }
        System.out.println(sum);
    }
}
