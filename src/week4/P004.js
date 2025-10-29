const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const [n, m] = input[0].split(' ').map(Number);
const graph = Array.from({ length: n + 1 }, () => []);
const inDegree = new Array(n + 1).fill(0);

for (let i = 1; i <= m; i++) {
    const [a, b] = input[i].split(' ').map(Number);
    // a -> b (a가 b의 선행)
    graph[a].push(b);
    // b의 진입 차수 증가
    inDegree[b]++;
}

const queue = [];
let head = 0; // shift()를 대체할 포인터
const ans = [];

for (let i = 1; i <= n; i++) {
    if (inDegree[i] === 0) {
        queue.push(i);
    }
}
while (head < queue.length) {
    // shift() 대신 head 포인터로 O(1) 디큐
    const s = queue[head++];
    // 결과 배열에 추가
    ans.push(s);

    // 현재 노드(s)와 연결된 노드들(adj_s)을 순회
    for (const adj_s of graph[s]) {
        // 연결된 노드의 진입 차수 1 감소
        inDegree[adj_s]--;

        // 진입 차수가 0이 되었다면, 큐에 추가
        if (inDegree[adj_s] === 0) {
            queue.push(adj_s);
        }
    }
}
console.log(ans.join(' '));