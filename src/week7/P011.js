const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const [x1, y1, x2, y2] = input[0].trim().split(/\s+/).map(Number);
const [x3, y3, x4, y4] = input[1].trim().split(/\s+/).map(Number);

function ccw(x1, y1, x2, y2, x3, y3) {
    // 좌표값이 매우 클 경우 BigInt를 써야 하지만, 문제 조건(100만 이하)에서는
    // 일반 연산 후 부호만 판별해도 무방합니다.
    const result = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
    
    if (result > 0) return 1;
    if (result < 0) return -1;
    return 0;
}

function solution() {
    const ccw123 = ccw(x1, y1, x2, y2, x3, y3);
    const ccw124 = ccw(x1, y1, x2, y2, x4, y4);
    const ccw341 = ccw(x3, y3, x4, y4, x1, y1);
    const ccw342 = ccw(x3, y3, x4, y4, x2, y2);

    if (ccw123 * ccw124 === 0 && ccw341 * ccw342 === 0) {
        // Bounding Box 비교 (겹치는지 확인)
        const mx1 = Math.min(x1, x2);
        const my1 = Math.min(y1, y2);
        const mx2 = Math.max(x1, x2);
        const my2 = Math.max(y1, y2);

        const mx3 = Math.min(x3, x4);
        const my3 = Math.min(y3, y4);
        const mx4 = Math.max(x3, x4);
        const my4 = Math.max(y3, y4);

        if (mx1 <= mx4 && mx3 <= mx2 && my1 <= my4 && my3 <= my2) {
            return 1;
        }
    } 
    else {
        if (ccw123 * ccw124 <= 0 && ccw341 * ccw342 <= 0) {
            return 1;
        }
    }
    return 0;
}

console.log(solution());