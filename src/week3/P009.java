package week3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class P009 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i <  T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            bw.write(A*B/findGCD(A, B) +"\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    //최대 공약수 구하기
    static int findGCD(int A, int B){
        /*
            GCD(A, B) = GCD(B, R) = GCD(R, R1) = GCD(R1, R2) = ... = GCD(Rn, 0)
            => Rn이 최대 공약수
        */
        while(B != 0){ //B가 0이 되기 전까지 반복(나머지가 0일때까지)
            int R = A%B; //나머지 저장
            A = B; //B를 A에 저장
            B = R; //A에 B를 저장
        }
        return A;
    }
}
