const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const n = parseInt(input[0], 10);
const numbers = input[1].split(' ').map(Number);

// 숫자 오름차순으로 정렬
numbers.sort((a, b) => a - b);

let count = 0;

for (let k = 0; k < n; k++) {
    const target = numbers[k];
    let start = 0;
    let end = n - 1;

    while (start < end) {
        // 포인터가 자기 자신을 가리키는 경우 스킵
        if (start === k) {
            start++;
            continue;
        }
        if (end === k) {
            end--;
            continue;
        }

        const currentSum = numbers[start] + numbers[end];

        if (currentSum === target) {
            count++;
            break;
        } else if (currentSum < target) {
            start++;
        } else {
            end--;
        }
    }
}
console.log(count);