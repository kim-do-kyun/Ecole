# 2162 선분으로 그룹 나누기
import sys
input = sys.stdin.readline


def ccw(x1, y1, x2, y2, x3, y3):
    return (x2-x1)*(y3-y1) - (y2-y1)*(x3-x1)


def is_two_lines_intersecting(line1, line2):
    x1, y1, x2, y2 = line1
    x3, y3, x4, y4 = line2

    mx1, my1 = min(x1, x2), min(y1, y2)
    mx2, my2 = max(x1, x2), max(y1, y2)
    mx3, my3 = min(x3, x4), min(y3, y4)
    mx4, my4 = max(x3, x4), max(y3, y4)

    ccw123 = ccw(x1, y1, x2, y2, x3, y3)
    ccw124 = ccw(x1, y1, x2, y2, x4, y4)
    ccw341 = ccw(x3, y3, x4, y4, x1, y1)
    ccw342 = ccw(x3, y3, x4, y4, x2, y2)

    if ccw123*ccw124 == 0 and ccw341*ccw342 == 0:
        if mx1 <= mx4 and mx3 <= mx2 and my1 <= my4 and my3 <= my2:
            return True
    else:
        if ccw123*ccw124 <= 0 and ccw341*ccw342 <= 0:
            return True

    return False


def findParent(x):
    if parents[x] == x:
        return x

    parents[x] = findParent(parents[x])
    return parents[x]


def union(x, y):
    px, py = findParent(x), findParent(y)

    if px < py:
        parents[py] = px
    else:
        parents[px] = py


if __name__ == '__main__':
    N = int(input())
    positions = [list(map(int, input().split())) for _ in range(N)]

    parents = [i for i in range(N)]

    for i in range(N-1):
        for j in range(i+1, N):
            # 선분이 교차할 시 두 그룹을 합친다.
            if is_two_lines_intersecting(positions[i], positions[j]):
                union(i, j)

    # parents를 통해 출력값을 얻는다.
    group_count = 0
    group_line_counts = [0]*N

    for i in range(N):
        # 자기 자신을 가리킨다 = 루트 노드
        if i == parents[i]:
            group_count += 1

        group_line_counts[findParent(i)] += 1

    print(group_count)
    print(max(group_line_counts))