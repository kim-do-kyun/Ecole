# 11047 동전 개수의 최솟값 구하기
n, k = map(int, input().split())

coins = []
for _ in range(n):
    coins.append(int(input()))

coins.sort(reverse=True)
result = 0

for i in coins:
    result += k // i
    k = k % i
    if k == 0:
        break

print(result)