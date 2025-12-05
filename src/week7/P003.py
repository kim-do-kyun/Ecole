# 2193 이친수 구하기
import sys
input = sys.stdin.readline

n = int(input())

# n 자리 수 만큼 dp 테이블 생성 => n+1
dp = [0] * (n + 1)
dp[1] = 1

for i in range(2, n+1):
    dp[i] = dp[i-2] + dp[i-1]

print(dp[n])