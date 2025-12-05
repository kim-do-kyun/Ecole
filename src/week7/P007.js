const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const N = Number(input[0]);
const arr = [];

// 행렬 정보 파싱
for (let i = 1; i <= N; i++) {
    arr.push(input[i].trim().split(' ').map(Number));
}

// DP 테이블 생성 (0으로 초기화)
const dp = Array.from({ length: N }, () => Array(N).fill(0));

// term: 구간의 간격
for (let term = 1; term < N; term++) {
    // start: 시작점
    for (let start = 0; start < N; start++) {
        
        // 범위 체크
        if (start + term >= N) {
            break;
        }

        const end = start + term; // 끝점

        // 최솟값 갱신을 위해 무한대로 초기화
        dp[start][end] = Infinity; // 파이썬의 int(1e9) 대신 Infinity 사용 권장

        // t: 자르는 지점
        for (let t = start; t < end; t++) {
            // 점화식: 왼쪽 비용 + 오른쪽 비용 + 합치는 비용
            // arr[start][0]: 전체 구간의 행 개수
            // arr[t][1]: 앞부분의 열 개수 (뒷부분의 행 개수와 같음)
            // arr[end][1]: 전체 구간의 열 개수
            const cost = dp[start][t] + dp[t + 1][end] 
                       + arr[start][0] * arr[t][1] * arr[end][1];

            dp[start][end] = Math.min(dp[start][end], cost);
        }
    }
}

console.log(dp[0][N - 1]);