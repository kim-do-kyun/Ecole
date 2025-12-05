const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim();

const n = Number(input);

if (n === 0) {
    console.log(0);
} else {
    // BigInt 배열 생성 (0n 으로 초기화)
    // 0이 아니라 0n (BigInt 형태의 0)이어야 계산 가능
    const dp = new Array(n + 1).fill(0n); 
    
    dp[1] = 1n; // 1 뒤에 n을 붙여야 BigInt 리터럴

    for (let i = 2; i <= n; i++) {
        dp[i] = dp[i - 2] + dp[i - 1];
    }

    // BigInt는 console.log로 그냥 찍으면 숫자 뒤에 'n'이 붙을 수 있으므로 toString() 사용 권장
    console.log(dp[n].toString());
}