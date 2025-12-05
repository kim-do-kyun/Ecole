const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split('\n'); 
let inputIdx = 0;
const readLine = () => input[inputIdx++].trim().split(' ').map(Number);
const N = readLine()[0]; 
const parent = new Array(N + 1).fill(0); // 각 노드의 부모 노드
const depth = new Array(N + 1).fill(0);  // 각 노드까지의 깊이
const graph = new Array(N + 1).fill(0).map(() => []); // 인접 리스트
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
    if (depth[a] > depth[b]) {
        [a, b] = [b, a]; // 스왑
    }
    while (depth[a] !== depth[b]) {
        b = parent[b];
    }
    while (a !== b) {
        a = parent[a];
        b = parent[b];
    }

    return a;
}
dfs(1, 0, 0);
const M = readLine()[0]; 
const result = [];
for (let i = 0; i < M; i++) {
    const [a, b] = readLine();
    result.push(lca(a, b));
}
console.log(result.join('\n'));