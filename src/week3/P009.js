const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const t = parseInt(input[0]);

// 유클리드 호제법으로 최대공약수(GCD)를 구하는 함수
function gcd(a, b) {
    while (b > 0) {
        // Python의 a, b = b, a%b 와 동일한 로직
        [a, b] = [b, a % b];
    }
    return a;
}

const results = [];
for (let i = 1; i <= t; i++) {
    const [a, b] = input[i].split(' ').map(Number);
    
    // 최소공배수 = (a * b) / 최대공약수
    const lcm = (a * b) / gcd(a, b);
    results.push(lcm);
}

console.log(results.join('\n'));