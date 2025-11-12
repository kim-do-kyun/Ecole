package week5;

import java.io.*;
import java.util.*;

public class P006 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> nString = new HashSet<>();

        // N개의 문자열을 HashSet에 추가
        for (int i = 0; i < n; i++) {
            nString.add(br.readLine());
        }

        int count = 0;
        // M개의 문자열이 HashSet에 존재하는지 확인
        for (int i = 0; i < m; i++) {
            if (nString.contains(br.readLine())) {
                count++;
            }
        }

        System.out.println(count);
    }
}
