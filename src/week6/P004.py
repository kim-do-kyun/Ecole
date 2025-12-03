# 11050 이항계수 구하기1
import sys

input = sys.stdin.readline

# N, K 입력
N, K = map(int, input().split())

# DP 테이블 초기화
DP = [[0] * (N + 1) for _ in range(N + 1)]

# 파스칼의 삼각형을 이용한 DP 테이블 채우기
for i in range(N + 1):
    DP[i][0] = 1  # nC0는 항상 1
    DP[i][i] = 1  # nCn은 항상 1

for i in range(2, N + 1):
    for j in range(1, i):
        DP[i][j] = (DP[i - 1][j - 1] + DP[i - 1][j]) % 10007  # 중간 값들도 모듈러 연산 적용

# 원하는 형식으로 출력
print(DP[N][K] % 10007)