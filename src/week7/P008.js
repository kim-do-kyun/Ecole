const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const N = Number(input[0]);
const world = [];

for (let i = 1; i <= N; i++) {
    world.push(input[i].trim().split(' ').map(Number));
}
const dp = Array.from({ length: N }, () => Array(1 << N).fill(-1));
const INF = 1e9; // 10억

function dfs(now, visited) {
    // 모든 도시 방문 완료 (비트가 모두 1)
    if (visited === (1 << N) - 1) {
        // 출발점(0)으로 돌아갈 수 있는 경우
        if (world[now][0] !== 0) {
            return world[now][0];
        }
        return INF; // 불가
    }

    if (dp[now][visited] !== -1) {
        return dp[now][visited];
    }

    let min_cost = INF;
    for (let next = 1; next < N; next++) {
        // 갈 수 없거나(0), 이미 방문했다면 스킵
        // (visited & (1 << next)) !== 0 은 방문했다는 뜻
        if (world[now][next] === 0 || (visited & (1 << next)) !== 0) {
            continue;
        }

        const cost = dfs(next, visited | (1 << next));
        
        // 유효한 경로라면 비용 갱신
        if(cost !== INF) {
             min_cost = Math.min(min_cost, cost + world[now][next]);
        }
    }

    dp[now][visited] = min_cost;
    return min_cost;
}
console.log(dfs(0, 1));