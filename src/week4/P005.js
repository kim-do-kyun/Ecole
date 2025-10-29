const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const n = Number(input[0]);

// 2. 그래프 및 배열 초기화
const graph = Array.from({ length: n + 1 }, () => []);
const indegree = new Array(n + 1).fill(0);
const cost = new Array(n + 1).fill(0);
// result: 각 건물이 시작하기까지(선수 건물이 모두 완료되기까지) 걸리는 시간
const result = new Array(n + 1).fill(0); 

// 3. 입력 파싱
for (let i = 1; i <= n; i++) {
    // 마지막 -1을 제외하고 파싱
    const data = input[i].split(' ').map(Number).slice(0, -1);
    
    cost[i] = data[0]; // 건물 짓는 시간
    const preBuildings = data.slice(1); // 선수 건물 목록

    for (const j of preBuildings) {
        // 간선 연결: j -> i
        graph[j].push(i);
        indegree[i]++; // i의 진입 차수 증가
    }
}

// 4. 위상 정렬 (Kahn's Algorithm)
const queue = [];
let head = 0;

// 진입 차수가 0인 노드 큐에 추가
for (let i = 1; i <= n; i++) {
    if (indegree[i] === 0) {
        queue.push(i);
    }
}

const answer = new Array(n + 1).fill(0); // 최종 완료 시간을 저장할 배열

while (head < queue.length) {
    const now = queue[head++];

    // ★ 현재 건물 완공 시간 = (선수 건물 완료 시간) + (현재 건물 짓는 시간)
    const completionTime = result[now] + cost[now];
    answer[now] = completionTime; // 최종 답안에 저장

    for (const b of graph[now]) {
        indegree[b]--;

        // ★ b의 선수 건물 완료 시간 갱신
        //    b의 선수 건물(now)의 완료 시간(completionTime)과
        //    b의 다른 선수 건물들의 완료 시간(result[b]) 중 더 오래 걸리는 시간으로 갱신
        result[b] = Math.max(result[b], completionTime);

        if (indegree[b] === 0) {
            queue.push(b);
        }
    }
}

// 5. 결과 출력
const output = [];
for (let i = 1; i <= n; i++) {
    output.push(answer[i]);
}
console.log(output.join('\n'));