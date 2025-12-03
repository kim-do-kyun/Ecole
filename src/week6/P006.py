# 1010 다리놓기
import sys

input = sys.stdin.readline

# 1. T 입력
T = int(input())

# 2. N, M 입력
for _ in range(T):
    N, M = map(int, input().split())
	
    # 3. dp 테이블 정의
    D = [[0 for _ in range(M + 1)] for _ in range(N + 1)]
	
    # 4. dp 테이블 채우기
    for j in range(1, M+1):
        D[1][j] = j
        D[0][j] = 1

    for i in range(2, N+1):
        for j in range(i, M+1):
            D[i][j] = D[i-1][j-1] + D[i][j-1]
	
    # 5. 원하는 형식으로 출력
    print(D[N][M])