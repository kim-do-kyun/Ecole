const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const n = parseInt(input[0], 10);
const numString = input[1];

const sum = numString.split('').reduce((acc, current) => {
    return acc + parseInt(current, 10);
}, 0);

console.log(sum);