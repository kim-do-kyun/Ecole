package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class P008 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // LinkedList를 사용하여 큐를 생성
        Queue<Integer> queue = new LinkedList<>();

        // 1부터 N까지의 카드를 큐에 순서대로 넣음
        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }

        // 카드가 1장 남을 때까지 반복
        while (queue.size() > 1) {
            queue.poll();

            int frontCard = queue.poll();
            queue.add(frontCard);
        }

        // 마지막에 남은 카드 출력
        System.out.println(queue.peek());

        br.close();
    }
}
