const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 입력 데이터 파싱
const n = parseInt(input[0]);
const nList = input[1].split(' ').map(Number);
const m = parseInt(input[2]);
const mList = input[3].split(' ').map(Number);

// 이진 탐색을 위해 배열을 오름차순으로 정렬
// sort() 메서드에 비교 함수를 전달해야 숫자를 제대로 정렬할 수 있음
nList.sort((a, b) => a - b);

const results = []; // 결과값을 저장할 배열

// 찾아야 할 숫자 목록(mList)을 순회
for (const target of mList) {
    let left = 0;
    let right = n - 1;
    let found = false; // 찾았는지 여부를 저장하는 변수

    // 이진 탐색 시작
    while (left <= right) {
        // 중간 인덱스 계산 (소수점 이하 버림)
        let mid = Math.floor((left + right) / 2);

        if (target > nList[mid]) {
            left = mid + 1;
        } else if (target < nList[mid]) {
            right = mid - 1;
        } else { // 숫자를 찾은 경우
            found = true;
            break;
        }
    }
    
    results.push(found ? 1 : 0);
}

console.log(results.join('\n'));