// 1. 최소 힙(Min Heap) 구현
// (BigInt 비교가 가능하도록 수정)
class MinPriorityQueue {
    constructor() {
        this.heap = [];
    }
    isEmpty() {
        return this.heap.length === 0;
    }
    size() {
        return this.heap.length;
    }
    // [0]번째 요소를 기준으로 비교 (BigInt)
    push(value) { 
        this.heap.push(value);
        this.bubbleUp();
    }
    pop() {
        if (this.isEmpty()) return null;
        if (this.heap.length === 1) return this.heap.pop();
        const min = this.heap[0];
        this.heap[0] = this.heap.pop();
        this.bubbleDown();
        return min;
    }
    peek() {
        return this.heap[0] || null;
    }
    // --- 내부 헬퍼 함수 ---
    bubbleUp() {
        let index = this.heap.length - 1;
        const lastNode = this.heap[index];
        while (index > 0) {
            const parentIndex = Math.floor((index - 1) / 2);
            const parentNode = this.heap[parentIndex];
            if (parentNode[0] > lastNode[0]) { // BigInt 비교
                this.heap[index] = parentNode;
                index = parentIndex;
            } else {
                break;
            }
        }
        this.heap[index] = lastNode;
    }
    bubbleDown() {
        let index = 0;
        const rootNode = this.heap[index];
        const len = this.heap.length;
        while (true) {
            let leftChildIndex = index * 2 + 1;
            let rightChildIndex = index * 2 + 2;
            let swapIndex = -1;
            if (leftChildIndex < len) {
                if (this.heap[leftChildIndex][0] < rootNode[0]) {
                    swapIndex = leftChildIndex;
                }
            }
            if (rightChildIndex < len) {
                if (
                    (swapIndex === -1 && this.heap[rightChildIndex][0] < rootNode[0]) ||
                    (swapIndex !== -1 && this.heap[rightChildIndex][0] < this.heap[leftChildIndex][0])
                ) {
                    swapIndex = rightChildIndex;
                }
            }
            if (swapIndex === -1) break;
            this.heap[index] = this.heap[swapIndex];
            index = swapIndex;
        }
        this.heap[index] = rootNode;
    }
}

// 2. 입력 처리
const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let line = 0;

const [n, m, k] = input[line++].split(' ').map(Number);

// 3. 그래프 및 힙 배열 초기화
const graph = Array.from({ length: n + 1 }, () => []);
// distance[i] = i번 노드까지의 K개 최단 경로 (최대 힙 시뮬레이션)
const distance = Array.from({ length: n + 1 }, () => new MinPriorityQueue());

// 4. 간선 정보 입력 (BigInt 사용)
for (let i = 0; i < m; i++) {
    const [a, b, c] = input[line++].split(' ').map(Number);
    // [비용(BigInt), 도착노드(Number)]
    graph[a].push([BigInt(c), b]); 
}

// 5. 다익스트라 큐 초기화
const q = new MinPriorityQueue(); // (전체 경로 탐색용)

// 6. 시작 노드 설정
// [비용(BigInt), 노드(Number)]
q.push([0n, 1]); 
// distance 힙에는 [음수비용(BigInt)]만 저장
distance[1].push([0n]); 

// 7. 다익스트라 수행
while (!q.isEmpty()) {
    const [dist, now] = q.pop(); // dist: BigInt, now: Number

    // 8. 인접 노드 탐색
    for (const [c, b] of graph[now]) { // c: BigInt, b: Number
        const cost = dist + c; // cost: BigInt
        const nextNode = b;

        // 9. K번째 경로 갱신
        // 9-1. 아직 K개의 경로가 채워지지 않았다면
        if (distance[nextNode].size() < k) {
            distance[nextNode].push([-cost]); // (음수 비용 저장)
            q.push([cost, nextNode]);
        } 
        // 9-2. K개가 찼고, 새 경로(cost)가 K번째 경로(-distance[nextNode].peek()[0])보다 작다면
        else if (cost < -distance[nextNode].peek()[0]) {
            distance[nextNode].pop();
            distance[nextNode].push([-cost]);
            q.push([cost, nextNode]);
        }
    }
}

// 10. 결과 출력
const output = [];
for (let i = 1; i <= n; i++) {
    if (distance[i].size() === k) {
        // K번째 경로는 최소 힙의 루트(음수)를 뒤집은 값
        // BigInt는 String()으로 변환하여 출력
        output.push(String(-distance[i].peek()[0]));
    } else {
        output.push("-1");
    }
}
console.log(output.join('\n'));