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
  const arr = lines[1].split(' ').map(Number);
  const k = parseInt(lines[2]);

  // 삭제할 노드와 그 자손들을 -2로 표시하는 DFS 함수
  function dfs(num, arr) {
    arr[num] = -2; // 현재 노드 삭제 표시
    for (let i = 0; i < arr.length; i++) {
      // 현재 노드(num)를 부모로 가지는 노드(i)를 찾아 재귀 호출
      if (num === arr[i]) {
        dfs(i, arr);
      }
    }
  }

  // k번 노드 삭제 수행
  dfs(k, arr);

  let count = 0;
  for (let i = 0; i < arr.length; i++) {
    // 삭제되지 않은 노드 중에서
    if (arr[i] !== -2) {
      // i번 노드를 부모로 가지는 노드가 있는지 확인
      let isLeaf = true;
      for (let j = 0; j < arr.length; j++) {
        if (arr[j] === i) { // i를 부모로 가지는 노드 j가 존재하면 리프 노드 아님
          isLeaf = false;
          break;
        }
      }
      // 자식이 없다면 리프 노드
      if (isLeaf) {
        count++;
      }
    }
  }

  console.log(count);
});