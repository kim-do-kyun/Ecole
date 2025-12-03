const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split(' ');

const N = parseInt(input[0]);
const K = parseInt(input[1]);

if (K === 0 || K === N) {
    console.log(1);
} else {
    // 파스칼 삼각형 배열 초기화
    let pascal = [];
    
    // n=1 일 때 [1, 1]
    pascal[1] = [1, 1];

    for (let depth = 2; depth < N; depth++) {
        // 자바스크립트는 배열 크기를 미리 지정하지 않아도 되지만, 
        // 명시적으로 depth + 1 크기의 배열을 생성하는 것이 로직상 안전함
        pascal[depth] = new Array(depth + 1);

        pascal[depth][0] = 1; // 첫 원소

        for (let idx = 1; idx < depth; idx++) {
            // 모듈러 연산 적용
            pascal[depth][idx] = (pascal[depth - 1][idx - 1] + pascal[depth - 1][idx]) % 10007;
        }

        pascal[depth][depth] = 1; // 마지막 원소
    }

    // 결과 출력
    console.log((pascal[N - 1][K - 1] + pascal[N - 1][K]) % 10007);
}