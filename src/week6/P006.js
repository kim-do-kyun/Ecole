const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split(/\s+/);

// 입력 커서 관리
let cursor = 0;

// 1. T 입력
const T = parseInt(input[cursor++]);

for (let t = 0; t < T; t++) {
    // 2. N, M 입력
    const N = parseInt(input[cursor++]);
    const M = parseInt(input[cursor++]);

    // 3. dp 테이블 정의
    // (N+1) x (M+1) 크기의 2차원 배열 생성 및 0으로 초기화
    const D = Array.from({ length: N + 1 }, () => new Array(M + 1).fill(0));

    // 4. dp 테이블 채우기
    for (let j = 1; j <= M; j++) {
        D[1][j] = j;
        D[0][j] = 1;
    }

    for (let i = 2; i <= N; i++) {
        // j는 i부터 M까지
        for (let j = i; j <= M; j++) {
            D[i][j] = D[i - 1][j - 1] + D[i][j - 1];
        }
    }

    // 5. 원하는 형식으로 출력
    console.log(D[N][M]);
}