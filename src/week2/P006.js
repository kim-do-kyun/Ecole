// 기수정렬 초과로 인한 계수정렬 코드
const fs = require('fs');

const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const n = parseInt(input[0]);
const counts = new Array(10001).fill(0);
for (let i = 1; i <= n; i++) {
  const num = parseInt(input[i]);
  counts[num]++;
}

const result = [];
for (let i = 1; i < counts.length; i++) {
  // counts[i]에 저장된 횟수만큼 숫자 i를 결과 배열에 추가합니다.
  if (counts[i] > 0) {
    for (let j = 0; j < counts[i]; j++) {
      result.push(i);
    }
  }
}
console.log(result.join('\n'));