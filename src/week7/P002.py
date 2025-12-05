# 14501 퇴사 준비하기
N = int(input())
TP = [list(map(int, input().split())) for _ in range(N)]
dp = [0 for _ in range(N+1)]

for i in range(N-1,-1,-1):
    if i+TP[i][0]>N:
        dp[i] = dp[i+1]
    else:
        dp[i] = max (dp[i+1],dp[i+TP[i][0]]+TP[i][1])
print(dp[0])