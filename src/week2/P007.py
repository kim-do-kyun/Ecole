# 11724 연결 요소의 개수
import sys
from collections import defaultdict

# 재귀 제한으로 인한 런타임 에러 방지(백준 기본 1000)
sys.setrecursionlimit(10**7)
input = sys.stdin.readline

n, m = map(int, input().split())

graph = [[] for _ in range(n+1)]
for _ in range(m):
    start, end = map(int, input().split())
    graph[start].append(end)
    graph[end].append(start)

visited = [False] * (n+1)

def dfs(v):
    visited[v] = True
    for i in graph[v]:
        if visited[i] == False:
            visited[i] == True
            dfs(i)
            
result = 0
for i in range(1, n+1):
    if visited[i] == False:
        result += 1
        dfs(i)

print(result)