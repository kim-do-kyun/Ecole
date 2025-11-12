const readline = require('readline');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let lines = [];

rl.on('line', (line) => {
  lines.push(line);
});

rl.on('close', () => {
  if (lines.length === 0) return;

  const n = parseInt(lines[0]);
  const graph = Array.from({ length: n + 1 }, () => []);

  for (let i = 1; i < n; i++) {
    const [a, b] = lines[i].split(' ').map(Number);
    graph[a].push(b);
    graph[b].push(a);
  }

  const visited = new Array(n + 1).fill(0);

  // DFS 함수 (재귀)
  // JavaScript는 기본 재귀 깊이 제한이 있으므로,
  // 노드 수가 많으면 스택 오버플로우가 발생할 수 있습니다.
  // 필요 시 반복문 기반 DFS로 변경해야 할 수도 있습니다.
  function dfs(s) {
    for (const next of graph[s]) {
      if (visited[next] === 0) {
        visited[next] = s;
        dfs(next);
      }
    }
  }

  // 루트 노드 1부터 시작해서 부모 노드를 찾기 위해
  // visited[1]을 1로 설정하여 다시 방문하지 않도록 함
  visited[1] = 1;
  dfs(1);

  let result = '';
  for (let x = 2; x <= n; x++) {
    result += visited[x] + '\n';
  }
  console.log(result);
});