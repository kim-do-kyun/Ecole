const fs = require('fs');
const expression = fs.readFileSync('/dev/stdin').toString().trim();

// 1. '-'를 기준으로 수식을 나눔
const parts = expression.split('-');

// 2. 첫 번째 부분의 합을 계산
// split('+') -> map(Number) -> reduce(sum)
const firstSum = parts[0]
    .split('+')
    .map(Number)
    .reduce((acc, cur) => acc + cur, 0);

let result = firstSum;

// 3. 두 번째 부분부터는 모두 빼줌
for (let i = 1; i < parts.length; i++) {
    const subSum = parts[i]
        .split('+')
        .map(Number)
        .reduce((acc, cur) => acc + cur, 0);
    
    result -= subSum;
}

console.log(result);