# 11438 최소 공통 조상 구하기2
import sys
# 재귀 한도 설정 (이미 되어 있음)
# sys.setrecursionlimit(100000) 

# 입력을 한 번에 읽어와서 줄 단위로 분리하고 처리하는 것이 가장 빠릅니다.
def solve():
    # 모든 입력을 읽어와서 줄 단위로 분리
    input_data = sys.stdin.read().split()
    if not input_data:
        return

    # 입력 포인터 초기화
    data_idx = 0
    def read_int():
        nonlocal data_idx
        val = int(input_data[data_idx])
        data_idx += 1
        return val

    # 트리 크기 N 입력
    n = read_int()
    
    # 2^i <= N을 만족하는 최대 i + 1. N=100000일 때 log2(100000) ~= 16.6, 21은 충분함.
    # log2(N) + 1 로 계산하는 것이 더 정확합니다.
    LENGTH = 0
    if n > 0:
        LENGTH = n.bit_length() # log2(N) + 1
    
    # 배열 초기화
    parent = [[0] * LENGTH for _ in range(n + 1)]
    d = [0] * (n + 1)
    graph = [[] for _ in range(n + 1)]

    # 간선 정보 입력
    for _ in range(n - 1):
        a = read_int()
        b = read_int()
        graph[a].append(b)
        graph[b].append(a)

    # DFS를 통해 깊이와 2^0번째 부모를 설정
    stack = [(1, 0, 0)]  # (노드, 깊이, 부모)
    visited = [False] * (n + 1)
    
    # 재귀 대신 반복문(Stack) 기반 DFS 사용 (파이썬 재귀 깊이 제한 및 오버헤드 회피)
    while stack:
        x, depth, p = stack.pop()
        
        if visited[x]:
            continue
        visited[x] = True
        d[x] = depth
        parent[x][0] = p # 현재 부모 저장
        
        for node in graph[x]:
            if not visited[node]:
                # 다음 탐색을 위해 스택에 추가 (깊이 + 1, 현재 노드를 부모로)
                stack.append((node, depth + 1, x)) 

    # 희소 배열 부모 관계 갱신
    for i in range(1, LENGTH):
        for j in range(1, n + 1):
            parent[j][i] = parent[parent[j][i - 1]][i - 1]

    # LCA 함수
    def lca(a, b):
        # 1. b의 깊이가 더 깊도록 설정
        if d[a] > d[b]:
            a, b = b, a

        # 2. 깊이 맞추기 (O(log N))
        diff = d[b] - d[a]
        for i in range(LENGTH):
            if (diff >> i) & 1:
                b = parent[b][i]

        if a == b:
            return a

        # 3. 올라가면서 공통 조상 찾기 (O(log N))
        for i in range(LENGTH - 1, -1, -1):
            if parent[a][i] != parent[b][i]:
                a = parent[a][i]
                b = parent[b][i]

        return parent[a][0]

    # LCA 질의 개수 M
    m = read_int()
    
    # 출력을 모으기 위한 리스트
    results = []
    
    # M번의 LCA 질의 처리
    for _ in range(m):
        a = read_int()
        b = read_int()
        results.append(str(lca(a, b)))
        
    # 한 번에 출력
    sys.stdout.write('\n'.join(results) + '\n')

solve()