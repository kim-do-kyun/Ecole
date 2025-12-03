const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split(/\s+/);

let cursor = 0;
const N = parseInt(input[cursor++]);
const type = parseInt(input[cursor++]);

// 팩토리얼 함수 (BigInt 사용)
function getFactorial(n) {
    let val = 1n;
    for (let i = 2n; i <= BigInt(n); i++) {
        val *= i;
    }
    return val;
}

// 1~N 숫자 리스트 생성
let num = [];
for (let i = 1; i <= N; i++) num.push(i);

if (type === 1) {
    // 입력받은 K를 BigInt로 변환
    let K = BigInt(input[cursor++]);
    K -= 1n; // 0-index

    let result = [];
    
    for (let i = N; i > 0; i--) {
        // (i-1)! 계산
        let f = getFactorial(i - 1);
        
        // 몫 구하기 (BigInt 나눗셈은 소수점 버림)
        let idx = K / f;
        
        // 배열 인덱스로 쓰기 위해 Number로 변환
        let idxNum = Number(idx);
        
        result.push(num[idxNum]);
        num.splice(idxNum, 1); // 해당 인덱스 요소 제거
        
        K %= f;
    }
    console.log(result.join(" "));

} else {
    // 문제 유형 2
    let data = [];
    for (let i = 0; i < N; i++) {
        data.push(parseInt(input[cursor++]));
    }

    let rs = 0n; // 결과값도 BigInt

    for (let i = 0; i < N; i++) {
        let f = getFactorial(N - 1 - i);
        
        // 현재 숫자의 인덱스 찾기
        let idx = num.indexOf(data[i]);
        
        // 계산
        rs += BigInt(idx) * f;
        
        // 사용한 숫자 제거
        num.splice(idx, 1);
    }
    
    // 결과에 1 더해서 출력 (String 변환 권장)
    console.log((rs + 1n).toString());
}