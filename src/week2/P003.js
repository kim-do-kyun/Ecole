const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const n = parseInt(input[0]);

// 두 번째 줄의 값들을 공백으로 나눈 뒤, 각 요소를 숫자로 변환하여 배열에 저장합니다.
const pList = input[1].split(' ').map(Number);

// 1. 배열을 오름차순으로 정렬합니다.
pList.sort((a, b) => a - b);

let result = 0;    // 최종 결과를 저장할 변수
let prefixSum = 0; // 각 단계까지의 누적 합을 저장할 변수

// 2. 누적 합의 총합을 계산합니다.
for (let i = 0; i < n; i++) {
  prefixSum += pList[i]; // 현재까지의 시간을 누적 합에 더합니다.
  result += prefixSum;   // 누적 합을 최종 결과에 더합니다.
}

// 최종 결과를 출력합니다.
console.log(result);