// 1. 최소 힙(Min Heap) 구현
// (JavaScript에는 표준 우선순위 큐가 없으므로 직접 구현)
class MinPriorityQueue {
    constructor() {
        this.heap = [];
    }

    isEmpty() {
        return this.heap.length === 0;
    }

    // 삽입 (O(log N))
    push(value) { // [거리, 노드]
        this.heap.push(value);
        this.bubbleUp();
    }

    // 삭제 (O(log N))
    pop() {
        if (this.isEmpty()) return null;
        if (this.heap.length === 1) return this.heap.pop();

        const min = this.heap[0];
        this.heap[0] = this.heap.pop();
        this.bubbleDown();
        return min;
    }

    // --- 내부 헬퍼 함수 ---
    bubbleUp() {
        let index = this.heap.length - 1;
        const lastNode = this.heap[index];

        while (index > 0) {
            const parentIndex = Math.floor((index - 1) / 2);
            const parentNode = this.heap[parentIndex];

            // 부모의 거리(cost)가 더 크면 swap
            if (parentNode[0] > lastNode[0]) {
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

const [v, e] = input[0].split(' ').map(Number);
const snode = Number(input[1]);
const INF = 1e9; // 무한대 값

// 3. 그래프 및 최단 거리 테이블 초기화
const graph = Array.from({ length: v + 1 }, () => []);
const distance = new Array(v + 1).fill(INF);

// 4. 간선 정보 입력
for (let i = 2; i < e + 2; i++) {
    const [u, v, w] = input[i].split(' ').map(Number);
    // graph[u]에 [도착노드, 가중치] 저장
    graph[u].push([v, w]);
}

// 5. 다익스트라 함수
function dijkstra(start) {
    const pq = new MinPriorityQueue();

    // 1. 시작 노드 설정
    pq.push([0, start]); // [거리, 노드번호]
    distance[start] = 0;

    while (!pq.isEmpty()) {
        const [dist, now] = pq.pop();

        // 2. 이미 처리된 노드(더 짧은 경로를 찾은 경우)는 무시
        if (distance[now] < dist) {
            continue;
        }

        // 3. 현재 노드와 연결된 인접 노드 확인
        for (const [neighbor, weight] of graph[now]) {
            const cost = dist + weight; // 현재 노드를 거쳐 가는 비용

            // 4. 현재 노드를 거쳐 가는 것이 더 짧은 경우
            if (cost < distance[neighbor]) {
                distance[neighbor] = cost;
                pq.push([cost, neighbor]);
            }
        }
    }
}

// 6. 다익스트라 알고리즘 수행
dijkstra(snode);

// 7. 결과 출력
const output = [];
for (let i = 1; i <= v; i++) {
    if (distance[i] === INF) {
        output.push("INF");
    } else {
        output.push(distance[i]);
    }
}
console.log(output.join('\n'));