/**
 * 이항 계수 (N choose K) 계산 - 파스칼의 삼각형 (DP)
 * Node.js 환경에서 동작합니다.
 */

const fs = require('fs');

// 모듈러 연산 상수
const MOD = 10007;

function solve() {
    // 입력을 읽고 N과 K를 추출
    const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
    
    const N = parseInt(input[0], 10);
    const K = parseInt(input[1], 10);

    // 유효성 검사 및 예외 처리
    if (K < 0 || K > N || isNaN(N) || isNaN(K)) {
        console.log(0);
        return;
    }

    // DP 테이블 초기화. DP[i][j]는 iCj의 결과를 저장
    // 크기 (N+1) x (N+1)
    const DP = Array(N + 1).fill(0).map(() => Array(N + 1).fill(0));

    // 파스칼의 삼각형을 이용한 DP 테이블 채우기
    for (let i = 0; i <= N; i++) {
        DP[i][0] = 1; // nC0 = 1
        
        for (let j = 1; j < i; j++) {
            // 파스칼의 항등식: iCj = (i-1)C(j-1) + (i-1)Cj
            // 모든 중간 결과에 모듈러 연산 적용
            DP[i][j] = (DP[i - 1][j - 1] + DP[i - 1][j]) % MOD;
        }
        
        DP[i][i] = 1; // nCn = 1
    }

    // 최종 결과 출력
    console.log(DP[N][K]);
}

solve();