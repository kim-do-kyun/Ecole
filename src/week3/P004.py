# 1715 카드 정렬하기
import heapq
import sys
input = sys.stdin.readline

n = int(input())
cards = []
result = 0

for _ in range(n):
    heapq.heappush(cards, int(input()))

if len(cards) == 1:
    print(0)
else:
    while cards:
        if len(cards) == 2:
            print(result + sum(cards))
            break
        tmp = heapq.heappop(cards) + heapq.heappop(cards)
        heapq.heappush(cards, tmp)
        result += tmp