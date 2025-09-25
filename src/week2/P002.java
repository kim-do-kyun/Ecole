package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String num = br.readLine();

        int[] numList = new int[num.length()];
        for(int i=0; i<num.length(); i++) {
            numList[i] = Integer.parseInt(num.substring(i, i+1));
        }

        for(int i=0; i<numList.length; i++) {
            int max = i;
            for (int j=i+1; j<numList.length; j++) {
                if(numList[j] > numList[max]) {
                    max = j;
                }
                if (numList[i] < numList[max]) {
                    int tmp = numList[i];
                    numList[i] = numList[max];
                    numList[max] = tmp;
                }
            }
        }
        for (int i : numList) {
            System.out.print(i);
        }
    }
}
