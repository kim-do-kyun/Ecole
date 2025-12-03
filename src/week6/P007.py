# 1722 순열의 순서 구하기
import sys
input = sys.stdin.readline

N = int(input())
c = list(map(int, input().split()))

def fact(i):
    if i <= 1:
        return 1
    else:
        return i * fact(i-1)

if c[0] == 1:
    k = c[1] - 1
    num = list(range(1,N+1))
    tmp = []

    while N > 0:
        f = fact(N-1)
        idx = k // f
        tmp.append(num.pop(idx))
        k %= f
        N -= 1
    print(" ".join(map(str, tmp)))

elif c[0] == 2:
    data = c[1:]
    num = list(range(1, N+1))
    rs = 0

    for i in range(N):
        f = fact(N-i-1)
        idx = num.index(data[i])
        rs += idx * f
        num.pop(idx)
    print(rs + 1)