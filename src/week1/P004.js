const fs = require('fs');
const n = parseInt(fs.readFileSync('/dev/stdin').toString().trim(), 10);

let count = 0;
let currentSum = 0;
let start = 1;

// end 포인터가 1부터 n까지 순차적으로 이동
for (let end = 1; end <= n; end++) {
    // 1. 윈도우 확장
    currentSum += end;
    
    // 2. 윈도우 축소
    while (currentSum > n) {
        currentSum -= start;
        start++;
    }
    
    // 3. 결과 확인
    if (currentSum === n) {
        count++;
    }
}

console.log(count);