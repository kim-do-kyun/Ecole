const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim();

const n = Number(input);

// 크기 1001 배열 생성 (0으로 초기화)
const dp = new Array(1001).fill(0);

// 초기값 설정
dp[1] = 1;
dp[2] = 2;

// 3부터 n까지 반복
for (let i = 3; i <= n; i++) {
    // 매 계산마다 10007로 나눈 나머지 저장
    dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
}

// 결과 출력
console.log(dp[n]);