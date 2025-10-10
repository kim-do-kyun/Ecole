const fs = require('fs');
const [m, n] = fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(Number);

// 소수 판별 함수
function isPrime(num) {
    if (num < 2) { // 1은 소수가 아님
        return false;
    }
    // 2부터 해당 수의 제곱근까지 반복
    for (let i = 2; i * i <= num; i++) {
        if (num % i === 0) {
            return false; // 나누어 떨어지면 소수가 아님
        }
    }
    return true;
}

const results = [];
for (let i = m; i <= n; i++) {
    if (isPrime(i)) {
        results.push(i);
    }
}

console.log(results.join('\n'));