const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 첫 줄에서 n, m 추출
const [n, m] = input[0].split(' ').map(Number);

// 2차원 배열 생성
const arr = [];
const dp = Array.from({ length: n }, () => Array(m).fill(0));

// 입력 데이터 파싱 (두 번째 줄부터)
for (let i = 0; i < n; i++) {
    // 문자열을 하나씩 쪼개서 숫자로 변환하여 배열에 담음
    // input[i + 1]을 trim() 하여 \r 등을 제거
    arr.push(input[i + 1].trim().split('').map(Number));
}

let answer = 0;

for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
        // 첫 행이나 첫 열인 경우
        if (i === 0 || j === 0) {
            dp[i][j] = arr[i][j];
        } 
        // 0인 경우
        else if (arr[i][j] === 0) {
            dp[i][j] = 0;
        } 
        // 점화식 적용
        else {
            dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1;
        }
        
        answer = Math.max(answer, dp[i][j]);
    }
}

console.log(answer * answer);