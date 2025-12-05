const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 1-based indexing을 위해 앞에 더미 문자('0' 또는 공백) 추가
const str1 = ' ' + input[0].trim();
const str2 = ' ' + input[1].trim();

const len1 = str1.length;
const len2 = str2.length;

// DP 테이블 생성 및 빈 문자열('')로 초기화
// Array.from을 사용하여 2차원 배열 생성
const dp = Array.from({ length: len2 }, () => Array(len1).fill(''));

for (let i = 1; i < len2; i++) {
    for (let j = 1; j < len1; j++) {
        // 문자가 같을 때
        if (str1[j] === str2[i]) {
            dp[i][j] = dp[i - 1][j - 1] + str1[j];
        } 
        // 문자가 다를 때
        else {
            if (dp[i][j - 1].length > dp[i - 1][j].length) {
                dp[i][j] = dp[i][j - 1];
            } else {
                dp[i][j] = dp[i - 1][j];
            }
        }
    }
}

// 결과 가져오기 (마지막 요소)
const resultStr = dp[len2 - 1][len1 - 1];
const answer = resultStr.length;

console.log(answer);

if (answer !== 0) {
    console.log(resultStr);
}