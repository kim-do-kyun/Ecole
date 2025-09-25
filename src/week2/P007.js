const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const [n, m] = input[0].split(' ').map(Number);

const graph = Array.from({ length: n + 1 }, () => []);
const visited = new Array(n + 1).fill(false);

// 간선 정보를 입력받아 그래프를 구성합니다. (무방향 그래프)
for (let i = 1; i <= m; i++) {
  const [start, end] = input[i].split(' ').map(Number);
  // 양방향으로 간선을 추가합니다.
  graph[start].push(end);
  graph[end].push(start);
}

function dfs(v) {
  // 현재 정점을 방문 처리합니다.
  visited[v] = true;

  // 현재 정점과 연결된 모든 인접 정점들을 확인합니다.
  for (const neighbor of graph[v]) {
    // 인접 정점이 아직 방문하지 않은 상태라면
    if (!visited[neighbor]) {
      // 재귀적으로 DFS 호출
      dfs(neighbor);
    }
  }
}

let result = 0; // 연결 요소의 개수를 셀 변수

for (let i = 1; i <= n; i++) {
  // 아직 방문하지 않은 정점이라면
  if (!visited[i]) {
    result++; // 새로운 연결 요소이므로 개수를 증가시킵니다.
    dfs(i);   // 해당 정점에서 DFS 탐색을 시작합니다.
  }
}

console.log(result);