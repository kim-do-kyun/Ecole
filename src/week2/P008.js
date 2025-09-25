const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const [n, m, v] = input[0].split(' ').map(Number);
const edges = input.slice(1);

// 그래프와 방문 배열 초기화
const graph = Array.from({ length: n + 1 }, () => new Array(n + 1).fill(0));
let visited = new Array(n + 1).fill(false);

// 인접 행렬 방식으로 그래프 구성
for (const edge of edges) {
  const [a, b] = edge.split(' ').map(Number);
  graph[a][b] = 1;
  graph[b][a] = 1;
}

const dfsResult = [];
const bfsResult = [];

// DFS (깊이 우선 탐색) - 재귀 방식
function dfs(current) {
  visited[current] = true;
  dfsResult.push(current);

  for (let i = 1; i <= n; i++) {
    if (graph[current][i] === 1 && !visited[i]) {
      dfs(i);
    }
  }
}

// BFS (너비 우선 탐색) - 큐 방식
function bfs(start) {
  const queue = [start];
  visited[start] = true;

  while (queue.length > 0) {
    const current = queue.shift(); // 큐의 맨 앞에서 요소를 꺼냄
    bfsResult.push(current);

    for (let i = 1; i <= n; i++) {
      if (graph[current][i] === 1 && !visited[i]) {
        queue.push(i); // 큐의 맨 뒤에 요소를 추가
        visited[i] = true;
      }
    }
  }
}

// --- 실행 ---
dfs(v);

// BFS를 위해 방문 배열 초기화
visited.fill(false); 

bfs(v);

console.log(dfsResult.join(' '));
console.log(bfsResult.join(' '));