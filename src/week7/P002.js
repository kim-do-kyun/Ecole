const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const N = Number(input[0]);
const TP = [];

// 입력 데이터 파싱 (TP 배열 생성)
for (let i = 1; i <= N; i++) {
    TP.push(input[i].trim().split(' ').map(Number));
}

// DP 테이블 생성 (0으로 채움)
const dp = new Array(N + 1).fill(0);

// 뒤에서부터 역순으로 반복 (N-1 부터 0까지)
for (let i = N - 1; i >= 0; i--) {
    const time = TP[i][0]; // 상담 걸리는 시간
    const pay = TP[i][1];  // 상담 비용

    // 상담 기간이 퇴사일(N)을 넘기는 경우
    if (i + time > N) {
        dp[i] = dp[i + 1];
    } else {
        // 상담을 안 하는 경우(dp[i+1]) vs 하는 경우(pay + dp[i+time])
        dp[i] = Math.max(dp[i + 1], pay + dp[i + time]);
    }
}

console.log(dp[0]);