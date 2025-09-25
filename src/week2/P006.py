# 10989 수 정렬하기
import sys
input = sys.stdin.readline
#메모리 초과
n = int(input())
numList = [int(input()) for _ in range(n)]
numList.sort()
for num in numList:
    print(num)

# 기수 정렬(메모리 초과)
from collections import deque
n = int(input())
numList = [int(input()) for _ in range(n)]
# 10진수에서 각 자릿수는 0부터 9까지 이므로 버킷은 10개 사용
buckets = [deque() for _ in range(10)]

maxVal = max(numList)
queue = deque(numList)
digit = 1

while(maxVal >= digit):
    while queue:
        num = queue.popleft()
        buckets[(num//digit)%10].append(num)

    for bucket in buckets:
        while bucket:
            queue.append(bucket.popleft())

    digit *= 10

for num in queue:
    print(num)

# 계수 정렬(통과)
n = int(input())
# 메모리 문제 해결을 위해 수의 범위 만큼의 카운트 배열만 생성
numList = [0]*10001
# 입력을 받아서 리스트에 저장하는게 아닌 바로 카운트 배열에 반영
for _ in range(n):
    numList[int(input())] += 1

# 카운트 배열을 순회하며 정렬된 결과를 출력
for i in range(10001):
    if numList[i] != 0:
        for _ in range(numList[i]):
            print(i)

