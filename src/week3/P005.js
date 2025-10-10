const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const n = parseInt(input[0]);
const meetingTimes = [];

for (let i = 1; i <= n; i++) {
    meetingTimes.push(input[i].split(' ').map(Number));
}

// 회의 시간을 정렬 (핵심 로직)
// 1. 종료 시간을 기준으로 오름차순 정렬
// 2. 종료 시간이 같다면, 시작 시간을 기준으로 오름차순 정렬
meetingTimes.sort((a, b) => {
    if (a[1] !== b[1]) {
        return a[1] - b[1];
    } else {
        return a[0] - b[0];
    }
});

let count = 0;
let prevEndTime = 0;

for (const meeting of meetingTimes) {
    const startTime = meeting[0];
    const endTime = meeting[1];

    // 이전 회의 종료 시간보다 현재 회의 시작 시간이 크거나 같다면
    if (startTime >= prevEndTime) {
        // 해당 회의를 선택하고, 종료 시간을 갱신
        prevEndTime = endTime;
        count++;
    }
}

console.log(count);