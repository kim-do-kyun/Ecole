# 1260 DFS와 BFS
n, m, v = map(int, input().split())
graph = [[0]*(n+1) for _ in range(n+1)]
for i in range(m):
    a, b = map(int, input().split())
    graph[a][b] = graph[b][a] = 1

visitedDfs = [0]*(n+1)
visitedBfs = visitedDfs.copy()

def dfs(v):
    visitedDfs[v] = 1
    print(v, end = ' ')
    for i in range(1, n+1):
        if graph[v][i] == 1 and visitedDfs[i] == 0:
            dfs(i)

def bfs(v):
    queue = [v]
    visitedBfs[v] = 1
    while queue:
        v = queue.pop(0)
        print(v, end = ' ')
        for i in range(1, n+1):
            if (visitedBfs[i] == 0 and graph[v][i] == 1):
                queue.append(i)
                visitedBfs[i] = 1

dfs(v)
print()
bfs(v)
