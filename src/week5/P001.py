# 11657 타임머신으로 빨리 가기
import sys
input = sys.stdin.readline
MAX = 1e9

n, m = map(int, input().split())
graph = [[] for _ in range(n+1)]
arr = [MAX]*(n+1)
arr[1] = 0
for _ in range(m):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
for i in range(n):
    for idx, temp in enumerate(graph):
        if idx == 0:
            continue
        for to_node, cost in temp:
            if arr[idx] != MAX and arr[idx] + cost < arr[to_node]:
                arr[to_node] = arr[idx] + cost
                if i == n-1:
                    print(-1)
                    exit()
for i in range(2, n+1):
    if arr[i] == MAX:
        print(-1)
    else:
        print(arr[i])