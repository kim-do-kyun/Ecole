const fs = require('fs');
// 입력 전체를 한 번에 읽어서 공백/줄바꿈 기준으로 나눔
const input = fs.readFileSync('/dev/stdin').toString().trim().split(/\s+/);

const N = Number(input[0]);

const x = [];
const y = [];

// 입력 데이터 파싱 (BigInt로 변환하여 저장)
// input[0]은 N이므로, 1번 인덱스부터 시작
for (let i = 0; i < N; i++) {
    x.push(BigInt(input[1 + i * 2]));
    y.push(BigInt(input[1 + i * 2 + 1]));
}

// 신발끈 공식을 위해 마지막에 첫 번째 좌표 다시 추가
x.push(x[0]);
y.push(y[0]);

let sum = 0n; // BigInt 0으로 초기화

for (let i = 0; i < N; i++) {
    // BigInt끼리의 연산
    sum += x[i] * y[i + 1] - x[i + 1] * y[i];
}

// 절댓값 처리
if (sum < 0n) {
    sum = -sum;
}

// 출력 포맷팅
// JS의 Number는 약 9,000조(2^53)까지 정밀도를 보장하므로,
// 최종 결과인 sum(최대 약 100조)을 Number로 변환해도 안전합니다.
// toFixed(1)을 사용하여 "xxxx.0" 또는 "xxxx.5" 형식을 강제합니다.
console.log((Number(sum) / 2).toFixed(1));