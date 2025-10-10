const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const [n, m] = input[0].split(' ').map(Number);
const times = input[1].split(' ').map(Number);

// 이진 탐색의 시작점: 가장 긴 강의 길이
let start = Math.max(...times);
// 이진 탐색의 끝점: 모든 강의 길이의 합
let end = times.reduce((acc, cur) => acc + cur, 0);

while (start <= end) {
    // 테스트해 볼 블루레이의 크기
    let mid = Math.floor((start + end) / 2);
    let count = 0;    // 사용된 블루레이 개수
    let tmpSum = 0;   // 현재 블루레이에 담긴 강의 길이 합

    for (const t of times) {
        // 현재 블루레이에 더 담을 수 없으면, 새 블루레이를 사용
        if (tmpSum + t > mid) {
            count++;
            tmpSum = 0; // 새 블루레이이므로 합계 초기화
        }
        tmpSum += t;
    }
    
    // 마지막에 담긴 강의가 있다면 블루레이 개수 +1
    if (tmpSum !== 0) {
        count++;
    }

    // 블루레이 개수가 M보다 많으면, 블루레이 크기를 늘려야 함
    if (count > m) {
        start = mid + 1;
    } 
    // 블루레이 개수가 M 이하면, 크기를 더 줄여볼 수 있음
    else {
        end = mid - 1;
    }
}

// 최적의 크기(최솟값)는 start에 저장됨
console.log(start);