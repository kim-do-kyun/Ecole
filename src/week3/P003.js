const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

let [n, k] = input[0].split(' ').map(Number);
const coins = input.slice(1).map(Number);

let result = 0;

// 가장 가치가 큰 동전부터 확인 (배열의 끝부터 시작)
for (let i = n - 1; i >= 0; i--) {
    const coin = coins[i];
    
    // 남은 금액(k)이 현재 동전 가치보다 크거나 같으면
    if (k >= coin) {
        // 현재 동전으로 지불할 수 있는 개수를 계산해서 더함
        result += Math.floor(k / coin);
        // 남은 금액을 갱신
        k %= coin;
    }

    // 남은 금액이 0이 되면 더 이상 계산할 필요가 없으므로 종료
    if (k === 0) {
        break;
    }
}
console.log(result);