package week7;

import java.util.Scanner;

public class P005 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str1 = " " + sc.next();
        String str2 = " " + sc.next();

        int len1 = str1.length();
        int len2 = str2.length();

        String[][] dp = new String[len2][len1];

        for (int i = 0; i < len2; i++) {
            for (int j = 0; j < len1; j++) {
                dp[i][j] = "";
            }
        }
        for (int i = 1; i < len2; i++) {
            for (int j = 1; j < len1; j++) {
                // 문자가 같으면: 대각선 위 값 + 현재 문자
                if (str1.charAt(j) == str2.charAt(i)) {
                    dp[i][j] = dp[i - 1][j - 1] + str1.charAt(j);
                }
                // 문자가 다르면: 왼쪽과 위쪽 중 더 긴 문자열 선택
                else {
                    if (dp[i][j - 1].length() > dp[i - 1][j].length()) {
                        dp[i][j] = dp[i][j - 1];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        String resultStr = dp[len2 - 1][len1 - 1];
        System.out.println(resultStr.length());

        if (resultStr.length() != 0) {
            System.out.println(resultStr);
        }
        sc.close();
    }
}