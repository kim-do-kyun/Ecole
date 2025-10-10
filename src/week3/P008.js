const fs = require('fs');
let n = BigInt(fs.readFileSync('/dev/stdin').toString().trim());

let result = n;

// 2부터 n의 제곱근까지 반복
// BigInt는 Math.sqrt를 직접 사용할 수 없으므로, i*i <= n 조건으로 반복
for (let i = 2n; i * i <= n; i++) {
    // i가 n의 소인수인지 확인
    if (n % i === 0n) {
        // 오일러 피 함수 공식 적용
        // result = result * (1 - 1/i)
        result = result - result / i;

        // 해당 소인수를 모두 제거
        while (n % i === 0n) {
            n /= i;
        }
    }
}

// 루프가 끝난 후에도 n이 1보다 크다면,
// 남은 n은 n의 소인수 중 하나임 (제곱근보다 큰 소인수)
if (n > 1n) {
    result = result - result / n;
}

console.log(result.toString());