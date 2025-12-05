const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 각 줄을 공백 기준으로 잘라 숫자 배열로 변환
const [x1, y1] = input[0].trim().split(' ').map(Number);
const [x2, y2] = input[1].trim().split(' ').map(Number);
const [x3, y3] = input[2].trim().split(' ').map(Number);

// CCW 공식 계산
const res = (x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3);

// 결과 판별
if (res > 0) {
    console.log(1);
} else if (res < 0) {
    console.log(-1);
} else {
    console.log(0);
}