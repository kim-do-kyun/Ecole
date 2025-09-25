// Node.js의 파일시스템(fs) 모듈 호출
// 
const fs = require('fs');

const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const n = Number(input.shift());

const result = input.map(Number);

for (let i = 0; i < n-1; i++) {
    for (let j=0; i < n-1-i; j++) {
        if (result[j] > result[j+1]) {
            // JS의 '배열 구조 분해 할당' 사용
            [result[j], result[j+1]] = [result[j+1], result[j]];
        }
    }
}

console.log(result.join('\n'));