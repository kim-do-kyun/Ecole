# 1934 최소 공배수 구하기
import sys
input = sys.stdin.readline

t = int(input())

for _ in range(t):
    a, b = map(int, input().split())
    aa, bb = a, b

    while b > 0:
        a, b = b, a%b

    print(aa*bb//a)