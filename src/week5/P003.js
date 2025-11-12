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

  const [v, e] = lines[0].split(' ').map(Number);
  let edges = [];
  for (let i = 1; i <= e; i++) {
    edges.push(lines[i].split(' ').map(Number)); // [a, b, c]
  }

  let parent = Array.from({ length: v + 1 }, (_, i) => i);
  let res = 0;

  edges.sort((a, b) => a[2] - b[2]);

  // Union-Find 알고리즘
  function find(x) {
    if (x === parent[x]) {
      return x;
    }
    return (parent[x] = find(parent[x]));
  }

  function union(x, y) {
    x = find(x);
    y = find(y);

    if (x !== y) {
      if (x <= y) {
        parent[y] = x;
      } else {
        parent[x] = y;
      }
    }
  }
  // 크루스칼 알고리즘
  for (let i = 0; i < e; i++) {
    const [x, y, c] = edges[i];
    if (find(x) !== find(y)) {
      // 부모 노드가 다름 (사이클 형성 X)
      union(x, y); // 최소 신장 트리에 포함시킴
      res += c;
    }
  }
  console.log(res);
});