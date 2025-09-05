const fs = require('fs');
const n = parseInt(fs.readFileSync('/dev/stdin').toString().trim(), 10);

const queue = Array.from({ length: n }, (_, i) => i + 1);
let head = 0; // 큐의 시작점을 가리키는 포인터

while (queue.length - head > 1) {
    // 1. 맨 위의 카드를 버림 (실제로 제거하지 않고 head 포인터만 이동)
    head++;
    
    // 2. 그 다음 맨 위 카드를 맨 아래로 옮김
    const frontCard = queue[head];
    head++;
    queue.push(frontCard);
}
console.log(queue[head]);