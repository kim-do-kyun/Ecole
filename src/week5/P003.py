# 1197 최소 신장 트리 구하기
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

v,e = map(int,input().split())
edges = [list(map(int,input().split())) for _ in range(e)] # a,b,c
parent = list(range(v+1))
res = 0

# 간선을 최소 비용 순으로 오름차순 정렬
edges.sort(key = lambda x: x[2])

# Union-Find 알고리즘
def find(x):
    if x == parent[x]:
        return x
    parent[x] = find(parent[x])
    return parent[x]

def union(x,y):
    x = find(x)
    y = find(y)

    if x <= y:
        parent[y] = x
    else:
        parent[x] = y
        
# 크루스칼 알고리즘
for i in range(e):
    x,y,c = edges[i]
    if find(x) != find(y): # 부모 노드가 다름
        union(x,y) # 최소 신장트리에 포함시킴
        res += c

print(res)