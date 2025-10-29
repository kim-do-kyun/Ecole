const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

let line = 0; // 현재 읽고 있는 줄을 추적
const t = Number(input[line++]); // 테스트 케이스 수
const answer = []; // 출력을 모을 배열

// 2. DFS 함수
// (변수들을 파라미터로 넘겨 재귀 스택 오버플로우를 조금이나마 방지)
function dfs(node, graph, visited) {
    // 현재 노드에 연결된 모든 이웃 탐색
    for (const neighbor of graph[node]) {
        // 1. 이웃이 미방문 상태라면
        if (visited[neighbor] === -1) {
            // 현재 노드와 반대되는 색(1 <-> 2)으로 칠함
            visited[neighbor] = visited[node] === 1 ? 2 : 1;
            // 재귀 호출. 만약 false(모순)가 반환되면 즉시 전파
            if (!dfs(neighbor, graph, visited)) {
                return false;
            }
        } 
        // 2. 이웃이 방문되었는데, 현재 노드와 색이 같다면
        else if (visited[node] === visited[neighbor]) {
            // 모순 발생!
            return false;
        }
    }
    // 이 노드에서 모순이 없었으면 true 반환
    return true;
}

// 3. 테스트 케이스 반복
for (let i = 0; i < t; i++) {
    const [v, e] = input[line++].split(' ').map(Number);
    const graph = Array.from({ length: v + 1 }, () => []);
    const visited = new Array(v + 1).fill(-1); // -1: 미방문, 1, 2: 그룹(색)

    for (let j = 0; j < e; j++) {
        const [start, end] = input[line++].split(' ').map(Number);
        graph[start].push(end);
        graph[end].push(start);
    }

    let isBipartite = true;

    // 4. 모든 정점(컴포넌트) 순회
    for (let j = 1; j <= v; j++) {
        // 아직 방문하지 않은 정점(새 컴포넌트) 발견
        if (visited[j] === -1) {
            visited[j] = 1; // 1번 그룹(색)으로 시작
            if (!dfs(j, graph, visited)) {
                isBipartite = false;
                break; // 모순이 발견되면 즉시 중단
            }
        }
    }
    answer.push(isBipartite ? "YES" : "NO");
}

// 5. 결과 출력
console.log(answer.join('\n'));