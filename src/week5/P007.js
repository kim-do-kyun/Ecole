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

  const N = parseInt(lines[0]);
  const tree = {};

  for (let i = 1; i <= N; i++) {
    const [root, left, right] = lines[i].trim().split(' ');
    tree[root] = [left, right];
  }

  let result = '';
  // 전위 순회 (Preorder): Root -> Left -> Right
  function preorder(root) {
    if (root === '.') return;
    result += root;
    preorder(tree[root][0]);
    preorder(tree[root][1]);
  }
  // 중위 순회 (Inorder): Left -> Root -> Right
  function inorder(root) {
    if (root === '.') return;
    inorder(tree[root][0]);
    result += root;
    inorder(tree[root][1]);
  }
  // 후위 순회 (Postorder): Left -> Right -> Root
  function postorder(root) {
    if (root === '.') return;
    postorder(tree[root][0]);
    postorder(tree[root][1]);
    result += root;
  }

  preorder('A');
  result += '\n';
  inorder('A');
  result += '\n';
  postorder('A');
  
  console.log(result);
});