const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const n = parseInt(input[0], 10);
const numList = input.slice(1).map(Number);

function merge(left, right) {
    const result = [];
    let i = 0;
    let j = 0;
    while (i < left.length && j < right.length) {
        if (left[i] <= right[j]) {
            result.push(left[i]);
            i++;
        } else {
            result.push(right[j]);
            j++;
        }
    }

    return result.concat(left.slice(i), right.slice(j));
}

function mergeSort(arr) {
    if (arr.length <= 1) {
        return arr;
    }
    
    // 1. 분할 (Divide)
    const mid = Math.floor(arr.length / 2);
    const left = arr.slice(0, mid);
    const right = arr.slice(mid);

    // 2. 정복 (Conquer) & 결합 (Combine)
    return merge(mergeSort(left), mergeSort(right));
}

const sortedList = mergeSort(numList);

console.log(sortedList.join('\n'));