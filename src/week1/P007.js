const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(Number);

const n = input[0];
const sequence = input.slice(1);

const stack = [];
const result = [];
let currentNum = 1;
let isPossible = true;

for (let i = 0; i < n; i++) {
    const targetNum = sequence[i];

    while (currentNum <= targetNum) {
        stack.push(currentNum);
        result.push('+');
        currentNum++;
    }

    if (stack[stack.length - 1] === targetNum) {
        stack.pop();
        result.push('-');
    } else {
        isPossible = false;
        break;
    }
}

if (isPossible) {
    console.log(result.join('\n'));
} else {
    console.log('NO');
}