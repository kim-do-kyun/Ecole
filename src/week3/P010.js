const fs = require('fs');
const [a, b, c] = fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(Number);

function gcd(a, b) {
    while (b !== 0) {
        [a, b] = [b, a % b];
    }
    return Math.abs(a);
}
function extendedEuclidean(a, b) {
    // 베이스 케이스: b가 0이면, gcd(a, 0) = a
    // a*1 + 0*y = a 이므로 x=1, y=0
    if (b === 0) {
        return [1, 0];
    }

    // 재귀 호출을 통해 b, a % b 에 대한 해 [x', y']를 구함
    const [xPrime, yPrime] = extendedEuclidean(b, a % b);

    // 현재 단계의 해 [x, y]를 계산
    // x = y'
    // y = x' - (a / b) * y'
    const x = yPrime;
    const y = xPrime - Math.floor(a / b) * yPrime;

    return [x, y];
}

// 1. a와 b의 최대공약수를 구함
const commonDivisor = gcd(a, b);

// 2. 해가 존재하는지 확인 (C가 최대공약수로 나누어 떨어지는가?)
if (c % commonDivisor !== 0) {
    console.log(-1);
} else {
    // 3. ax + by = gcd(a, b)의 해 [x0, y0]를 구함
    const [x0, y0] = extendedEuclidean(a, b);
    
    // 4. C를 만들기 위한 배율(mok)을 계산
    // 이미 나누어 떨어짐을 확인했으므로 Math.floor는 생략 가능
    const mok = c / commonDivisor;
    
    // 5. 최종 해를 계산하여 출력
    console.log(`${x0 * mok} ${y0 * mok}`);
}