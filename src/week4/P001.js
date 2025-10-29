const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const [n, m, k, s] = input[0].split(' ').map(Number);
const graph = Array.from({ length: n + 1 }, () => []);

for (let i = 1; i <= m; i++) {
    const [a, b] = input[i].split(' ').map(Number);
    graph[a].push(b);
}
const dist = new Array(n + 1).fill(-1);
const queue = [];
let head = 0; // 큐의 shift() 연산(O(N))을 대체할 포인터 (O(1))

// 6. BFS 시작 노드 설정
queue.push(s);
dist[s] = 0; // 시작 도시의 거리는 0

// 7. BFS 수행
while (head < queue.length) {
    // 큐에서 요소를 꺼냅니다. (shift() 대신 head 포인터 사용)
    const now = queue[head++];

    // 현재 도시(now)에서 갈 수 있는 다음 도시(next)들을 순회합니다.
    for (const next of graph[now]) {
        // 만약 다음 도시(next)를 아직 방문하지 않았다면
        if (dist[next] === -1) {
            // 거리를 갱신하고 (현재 도시 거리 + 1)
            dist[next] = dist[now] + 1;
            // 큐에 추가합니다.
            queue.push(next);
        }
    }
}

const answer = [];
for (let i = 1; i <= n; i++) {
    // 거리가 k인 도시를 찾습니다.
    if (dist[i] === k) {
        answer.push(i);
    }
}

if (answer.length === 0) console.log(-1);
else console.log(answer.join('\n'));