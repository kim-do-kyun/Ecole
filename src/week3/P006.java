package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P006 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        // 1 '-'를 기준으로 수식을 분해
        String[] parts = str.split("-");
        int result = 0;

        // 2. 첫 번째 부분(마이너스 앞부분)의 합을 계산
        String[] firstPartNums = parts[0].split("\\+");
        for (String numStr : firstPartNums) {
            result += Integer.parseInt(numStr);
        }

        // 3. 두 번째 부분부터는 모두 감소
        for (int i = 1; i < parts.length; i++) {
            String[] subPartNums = parts[i].split("\\+");
            int subSum = 0;
            for (String numStr : subPartNums) {
                subSum += Integer.parseInt(numStr);
            }
            result -= subSum;
        }
        System.out.println(result);
    }
}
