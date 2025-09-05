package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P007 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        int currentNum = 1;
        boolean isPossible = true; // 수열 생성 가능 여부를 판단하는 플래그

        for (int i = 0; i < n; i++) {
            int targetNum = Integer.parseInt(br.readLine());

            // 현재 스택에 넣을 숫자가 만들어야 할 숫자보다 작거나 같으면 push
            while (currentNum <= targetNum) {
                stack.push(currentNum);
                result.append("+\n");
                currentNum++;
            }
            // 스택의 top이 만들어야 할 숫자와 같으면 pop
            if (!stack.isEmpty() && stack.peek() == targetNum) {
                stack.pop();
                result.append("-\n");
            }
            // 스택의 top이 만들어야 할 숫자와 다르면 수열 생성 불가
            else {
                isPossible = false;
                break;
            }
        }

        if (isPossible) {
            System.out.println(result.toString());
        } else {
            System.out.println("NO");
        }

        br.close();
    }
}
