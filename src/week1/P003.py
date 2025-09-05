# 11659
# 합 배열 만드는 공식
# A[i] 부터 A[j] 까지의 배열 합
# S[i] = S[i-1] + A[i]
# 구간 합 구하는 공식
# S[j] - S[i-1]
import sys
input = sys.stdin.readline
n, m = map(int, input().split())
numList = list(map(int, input().split()))
prev = [0]
result = 0

for i in range(n):
    result += numList[i]
    prev.append(result)

for num in range(m):
    i, j = map(int, input().split())
    print(prev[j]-prev[i-1])
