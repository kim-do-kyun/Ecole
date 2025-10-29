# 1717 집합 표현하기
import sys
sys.setrecursionlimit(10**5)

n, m = map(int, sys.stdin.readline().rstrip().split())

parent = [i for i in range(n+1)]

def get_parent(x):
    if parent[x] == x:
        return x
    parent[x] = get_parent(parent[x]) # get_parent 거슬러 올라가면서 parent[x] 값도 갱신
    return parent[x]

def union_parent(a, b):
    a = get_parent(a)
    b = get_parent(b)

    if a < b: # 작은 쪽이 부모가 된다. (한 집합 관계라서 부모가 따로 있는 건 아님)
        parent[b] = a
    else:
        parent[a] = b        

def find_parent(a, b):
    return get_parent(a) == get_parent(b)

for _ in range(m):
    op, a, b = map(int, sys.stdin.readline().rstrip().split())
    if op == 0:
        union_parent(a, b)
    else:
        print('YES' if find_parent(a, b) else 'NO')