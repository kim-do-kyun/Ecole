const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

let lines = [];

rl.on('line', (line) => {
  lines.push(line);
});
rl.on('close', () => {
  if (lines.length === 0) return;

  const [N, M] = lines[0].split(' ').map(Number);
  const edges = [];
  const dist = new Array(N + 1).fill(Infinity);

  for (let i = 1; i <= M; i++) {
    const [u, v, w] = lines[i].split(' ').map(Number);
    edges.push({ u, v, w });
  }

  dist[1] = 0;

  for (let i = 1; i <= N; i++) {
    for (let j = 0; j < M; j++) {
      const { u, v, w } = edges[j];
      if (dist[u] !== Infinity && dist[u] + w < dist[v]) {
        dist[v] = dist[u] + w;
        if (i === N) {
          console.log(-1);
          return;
        }
      }
    }
  }

  for (let i = 2; i <= N; i++) {
    if (dist[i] === Infinity) {
      console.log(-1);
    } else {
      console.log(dist[i]);
    }
  }
});