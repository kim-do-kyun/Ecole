const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const [n, m] = input[0].split(' ').map(Number);
const numbers = input[1].split(' ').map(Number);

// 구간 합 배열 생성
const prefixSum = new Array(n + 1).fill(0);
for (let i = 0; i < n; i++) {
    prefixSum[i + 1] = prefixSum[i] + numbers[i];
}

const results = [];
for (let k = 2; k < m + 2; k++) {
    const [i, j] = input[k].split(' ').map(Number);
    // 구간 합 공식: S[j] - S[i-1]
    const result = prefixSum[j] - prefixSum[i - 1];
    results.push(result);
}

console.log(results.join('\n'));