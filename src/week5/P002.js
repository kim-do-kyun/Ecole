const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

let input = [];
rl.on('line', (line) => {
  input.push(line);
});
rl.on('close', () => {
  if (input.length === 0) return;

  const n = parseInt(input[0]);
  const m = parseInt(input[1]);
  const INF = 1e9;
  const graph = Array.from(Array(n + 1), () => Array(n + 1).fill(INF));

  for (let i = 1; i <= n; i++) {
    graph[i][i] = 0;
  }

  for (let i = 2; i < m + 2; i++) {
    const [a, b, c] = input[i].split(' ').map(Number);
    graph[a][b] = Math.min(graph[a][b], c);
  }

  // 플로이드-워셜 알고리즘
  for (let k = 1; k <= n; k++) {
    for (let a = 1; a <= n; a++) {
      for (let b = 1; b <= n; b++) {
        graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
      }
    }
  }

  let result = '';
  for (let a = 1; a <= n; a++) {
    for (let b = 1; b <= n; b++) {
      if (graph[a][b] === INF) {
        result += '0 ';
      } else {
        result += graph[a][b] + ' ';
      }
    }
    result += '\n';
  }
  console.log(result);
});