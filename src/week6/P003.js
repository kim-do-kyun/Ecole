const fs = require('fs');

function getOptimizedInput() {
    // 모든 입력을 읽어와서 공백 기준으로 분리
    const inputData = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
    let dataIdx = 0;
    
    // 다음 정수 토큰을 읽는 함수. 이 함수를 통해 N, 간선, M, 쿼리를 순차적으로 읽습니다.
    const readInt = () => inputData[dataIdx++];
    
    return { readInt };
}
function solve() {
    const { readInt } = getOptimizedInput();
    
    // [수정] N을 readInt()로 소비해야 다음 입력부터 간선 정보가 시작됩니다.
    const N = readInt();

    if (N <= 0) return;

    // LENGTH: 2^k 번째 부모를 저장하기 위한 최대 깊이 (log2(N) + 1)
    // N.toString(2).length는 N의 이진수 길이를 반환하며, 이는 ceil(log2(N+1))과 같습니다.
    const LENGTH = N.toString(2).length; 

    // 배열 초기화 (1-based index)
    const parent = Array(N + 1).fill(0).map(() => Array(LENGTH).fill(0));
    const depth = Array(N + 1).fill(0); 
    const graph = Array(N + 1).fill(0).map(() => []);

    // 1. 그래프 구성
    for (let i = 0; i < N - 1; i++) {
        const u = readInt();
        const v = readInt();
        graph[u].push(v);
        graph[v].push(u); 
    }

    // 2. DFS를 통한 기본 정보(depth, 1번째 부모) 계산
    function calculateBaseInfo() {
        // Stack 기반 DFS: [노드, 깊이, 부모]
        const stack = [[1, 0, 0]]; // 루트 노드 1, 깊이 0, 부모 0
        const visited = Array(N + 1).fill(false);
        
        while (stack.length > 0) {
            const [curr, dep, p] = stack.pop();
            
            if (visited[curr]) continue;
            visited[curr] = true;
            
            depth[curr] = dep;
            parent[curr][0] = p; // 1번째 부모 저장

            for (const next_node of graph[curr]) {
                if (!visited[next_node]) {
                    stack.push([next_node, dep + 1, curr]);
                }
            }
        }
    }

    // 3. 희소 배열(Sparse Array) 채우기: 2^i 번째 부모 계산
    function fillSparseArray() {
        for (let i = 1; i < LENGTH; i++) {
            for (let j = 1; j <= N; j++) {
                // parent[j][i] = parent[ parent[j][i-1] ][i-1]
                const intermediateParent = parent[j][i - 1];
                parent[j][i] = parent[intermediateParent][i - 1];
            }
        }
    }

    // 4. 최소 공통 조상(LCA) 함수
    function lca(a, b) {
        // 무조건 b의 깊이가 더 깊거나 같도록 설정 (구조 분해 할당으로 스왑)
        if (depth[a] > depth[b]) {
            [a, b] = [b, a]; 
        }

        // 1. 깊이 맞추기 (O(log N))
        let diff = depth[b] - depth[a];
        for (let i = 0; i < LENGTH; i++) {
            // diff의 i번째 비트가 1이면 2^i만큼 점프
            if ((diff >> i) & 1) {
                b = parent[b][i];
            }
        }

        if (a === b) {
            return a;
        }

        // 2. 공통 조상의 바로 아래까지 동시에 올라가기 (O(log N))
        for (let i = LENGTH - 1; i >= 0; i--) {
            // 두 노드의 2^i 번째 부모가 다르면 동시에 점프
            if (parent[a][i] !== parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        // LCA 바로 아래에 위치한 두 노드의 부모가 LCA
        return parent[a][0];
    }

    // 전처리 실행
    calculateBaseInfo();
    fillSparseArray();

    // 5. LCA 질의 처리
    const M = readInt(); // 쿼리 개수

    const results = [];
    for (let i = 0; i < M; i++) {
        const u = readInt();
        const v = readInt();
        results.push(lca(u, v));
    }
        
    // 결과를 한 번에 출력
    console.log(results.join('\n'));
}

solve();