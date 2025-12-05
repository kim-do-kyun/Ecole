const fs = require('fs');

// 입력 처리 (로컬 테스트 시 경로 수정 필요, 백준 제출 시 '/dev/stdin')
const input = fs.readFileSync('/dev/stdin').toString().trim();
const x = Number(input);

// DP 테이블 생성 (0으로 채움)
const d = new Array(x + 1).fill(0);

// 2부터 x까지 반복
for (let i = 2; i <= x; i++) {
    // 1. 1을 빼는 연산
    d[i] = d[i - 1] + 1;

    // 2. 2로 나누어 떨어질 때
    if (i % 2 === 0) {
        d[i] = Math.min(d[i], d[i / 2] + 1);
    }

    // 3. 3으로 나누어 떨어질 때
    if (i % 3 === 0) {
        d[i] = Math.min(d[i], d[i / 3] + 1);
    }
}

console.log(d[x]);