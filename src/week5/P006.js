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

  const [n, m] = lines[0].split(' ').map(Number);
  const nString = new Set();

  let currentLine = 1;
  // N개의 문자열을 Set에 추가
  for (let i = 0; i < n; i++) {
    nString.add(lines[currentLine++]);
  }

  let count = 0;
  // M개의 문자열이 Set에 존재하는지 확인
  for (let i = 0; i < m; i++) {
    if (nString.has(lines[currentLine++])) {
      count++;
    }
  }

  console.log(count);
});