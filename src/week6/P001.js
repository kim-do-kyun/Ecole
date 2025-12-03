const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split('\n');
let inputIdx = 0;

const readLine = () => input[inputIdx++].trim();
const [N, M, K] = readLine().split(' ').map(Number);

const arr = [];
for (let i = 0; i < N; i++) {
    arr.push(BigInt(readLine())); 
}

// 세그먼트 트리 배열 (4N 크기, BigInt로 초기화)
// 자바스크립트는 배열 크기가 가변적이지만, 메모리 관점에서 미리 4N 크기로 초기화
const tree = new Array(N * 4).fill(0n); // 0n은 BigInt 0

// start, end: 숫자 배열의 인덱스 (0-based)
// idx: 세그먼트 트리의 인덱스 (1부터)
function makeTree(start, end, idx) {
    if (start === end) {
        tree[idx] = arr[start];
        return tree[idx];
    }

    const mid = Math.floor((start + end) / 2);
    // BigInt 덧셈을 사용
    tree[idx] = makeTree(start, mid, idx * 2n) + makeTree(mid + 1, end, idx * 2n + 1n);

    return tree[idx];
}

// target: 수정할 값의 숫자 배열 인덱스 (0-based)
// diff: 기존 값에 '얼만큼 더해야 하는지의 값' (BigInt)
function updateTree(start, end, idx, target, diff) {
    if (target < start || target > end) {
        return;
    }
    tree[idx] += diff;

    if (start === end) {
        return;
    }
    
    const mid = Math.floor((start + end) / 2);
    updateTree(start, mid, idx * 2n, target, diff);
    updateTree(mid + 1, end, idx * 2n + 1n, target, diff);
}

// left, right: 구하고자 하는 범위의 숫자 배열 인덱스 (0-based)
function sumTree(start, end, idx, left, right) {
    if (right < start || left > end) {
        return 0n; 
    }

    if (left <= start && right >= end) {
        return tree[idx];
    }

    const mid = Math.floor((start + end) / 2);
    return sumTree(start, mid, idx * 2n, left, right) + sumTree(mid + 1, end, idx * 2n + 1n, left, right);
}

// 초기 세그먼트 트리 생성
makeTree(0, N - 1, 1n); // 루트 노드 인덱스 1n (BigInt)

const result = [];
// M + K 번의 연산 처리
for (let i = 0; i < M + K; i++) {
    const line = readLine().split(' ');
    // a, b는 숫자, c는 BigInt로 처리
    const a = Number(line[0]);
    const b = Number(line[1]);
    const c = BigInt(line[2]); 
    
    if (a === 1) { // 1: 값 변경
        const targetIndex = b - 1; // 0-based 인덱스
        const newValue = c;
        
        // 기존 값과 새로운 값의 차이 (diff)를 계산하여 updateTree 호출
        const diff = newValue - arr[targetIndex];
        
        // idx를 BigInt로 전달 (1n)
        updateTree(0, N - 1, 1n, targetIndex, diff);
        
        // 원본 배열 값 변경 (필수)
        arr[targetIndex] = newValue;
    } else { // 2: 구간 합
        const left = b - 1;
        const right = Number(c) - 1; // c가 인덱스이므로 Number로 변환
        
        const sum = sumTree(0, N - 1, 1n, left, right);
        result.push(sum.toString()); // 결과를 문자열로 저장
    }
}
console.log(result.join('\n'));