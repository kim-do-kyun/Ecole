const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const N = Number(input[0]);
const positions = [];

for (let i = 1; i <= N; i++) {
    positions.push(input[i].trim().split(/\s+/).map(Number));
}

// Union-Find용 배열
const parents = Array.from({ length: N }, (_, i) => i);

// CCW 함수: 1, -1, 0 반환 (오버플로우 방지)
function ccw(x1, y1, x2, y2, x3, y3) {
    const result = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
    if (result > 0) return 1;
    if (result < 0) return -1;
    return 0;
}

function isTwoLinesIntersecting(line1, line2) {
    const [x1, y1, x2, y2] = line1;
    const [x3, y3, x4, y4] = line2;

    const ccw123 = ccw(x1, y1, x2, y2, x3, y3);
    const ccw124 = ccw(x1, y1, x2, y2, x4, y4);
    const ccw341 = ccw(x3, y3, x4, y4, x1, y1);
    const ccw342 = ccw(x3, y3, x4, y4, x2, y2);

    if (ccw123 * ccw124 === 0 && ccw341 * ccw342 === 0) {
        const mx1 = Math.min(x1, x2), mx2 = Math.max(x1, x2);
        const my1 = Math.min(y1, y2), my2 = Math.max(y1, y2);
        const mx3 = Math.min(x3, x4), mx4 = Math.max(x3, x4);
        const my3 = Math.min(y3, y4), my4 = Math.max(y3, y4);

        if (mx1 <= mx4 && mx3 <= mx2 && my1 <= my4 && my3 <= my2) {
            return true;
        }
    } else {
        if (ccw123 * ccw124 <= 0 && ccw341 * ccw342 <= 0) {
            return true;
        }
    }
    return false;
}

function findParent(x) {
    if (parents[x] === x) return x;
    parents[x] = findParent(parents[x]);
    return parents[x];
}

function union(x, y) {
    const px = findParent(x);
    const py = findParent(y);

    if (px < py) parents[py] = px;
    else parents[px] = py;
}

// 메인 로직 실행
for (let i = 0; i < N - 1; i++) {
    for (let j = i + 1; j < N; j++) {
        if (isTwoLinesIntersecting(positions[i], positions[j])) {
            union(i, j);
        }
    }
}

let groupCount = 0;
const groupLineCounts = new Array(N).fill(0);

for (let i = 0; i < N; i++) {
    const root = findParent(i);
    
    if (i === parents[i]) {
        groupCount++;
    }
    
    groupLineCounts[root]++;
}

console.log(groupCount);
console.log(Math.max(...groupLineCounts));