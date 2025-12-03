const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split('\n'); 
let inputIdx = 0;

// 다음 줄의 입력을 읽고 공백 기준으로 나누는 함수
const readLine = () => input[inputIdx++].trim().split(' ').map(Number);
// 노드의 개수 N
const N = readLine()[0]; 

// 전역 배열 및 그래프 초기화 (1-based index)
const parent = new Array(N + 1).fill(0); // 각 노드의 부모 노드
const depth = new Array(N + 1).fill(0);  // 각 노드까지의 깊이
const graph = new Array(N + 1).fill(0).map(() => []); // 인접 리스트

// 트리의 연결 정보 입력 (N-1개의 간선)
for (let i = 0; i < N - 1; i++) {
    const [a, b] = readLine();
    graph[a].push(b);
    graph[b].push(a); // 양방향 연결
}
function dfs(curr, dep, pcurr) {
    depth[curr] = dep;
    parent[curr] = pcurr;

    for (const next of graph[curr]) {
        // 부모 노드가 아닌 경우에만 탐색
        if (next !== pcurr) {
            dfs(next, dep + 1, curr);
        }
    }
}
function lca(a, b) {
    // 1. 깊이 맞추기 (항상 b가 더 깊거나 같은 깊이가 되도록 조정)
    if (depth[a] > depth[b]) {
        [a, b] = [b, a]; // 스왑
    }

    // 깊이가 같아질 때까지 깊이가 더 깊은 노드(b)를 위로 올림
    while (depth[a] !== depth[b]) {
        b = parent[b];
    }

    // 2. 노드 맞추기 (두 노드가 같아질 때까지 동시에 위로 올림)
    while (a !== b) {
        a = parent[a];
        b = parent[b];
    }

    return a;
}
dfs(1, 0, 0);

// LCA 질의 개수 M
const M = readLine()[0]; 

const result = [];
// M번의 LCA 질의 처리
for (let i = 0; i < M; i++) {
    const [a, b] = readLine();
    result.push(lca(a, b));
}
console.log(result.join('\n'));