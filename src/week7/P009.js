const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const n = Number(input[0]);
const arr = input[1].trim().split(/\s+/).map(Number);

const lis = [arr[0]]; // LIS 배열
const record = []; // 추적용 배열 (Python의 dp 변수)

// (0, arr[0]) 저장
record.push({ idx: 0, val: arr[0] });

// 이분 탐색 함수
function binarySearch(e) {
    let start = 0;
    let end = lis.length - 1;

    while (start <= end) {
        let mid = Math.floor((start + end) / 2);

        if (lis[mid] === e) {
            return mid;
        } else if (lis[mid] < e) {
            start = mid + 1;
        } else {
            end = mid - 1;
        }
    }
    return start;
}

for (let i = 1; i < n; i++) {
    // 현재 값이 LIS 마지막 값보다 크면 추가
    if (arr[i] > lis[lis.length - 1]) {
        lis.push(arr[i]);
        record.push({ idx: lis.length - 1, val: arr[i] });
    } 
    // 아니면 위치를 찾아 교체
    else {
        const idx = binarySearch(arr[i]);
        lis[idx] = arr[i];
        record.push({ idx: idx, val: arr[i] });
    }
}

// 1. 길이 출력
console.log(lis.length);

// 2. 역추적
let lastIdx = lis.length - 1;
const result = [];

for (let i = record.length - 1; i >= 0; i--) {
    // 현재 요소의 LIS 상 인덱스가 찾고자 하는 인덱스와 같다면
    if (record[i].idx === lastIdx) {
        result.push(record[i].val);
        lastIdx--;
    }
}

// 역순으로 담겼으므로 뒤집어서 출력
console.log(result.reverse().join(' '));