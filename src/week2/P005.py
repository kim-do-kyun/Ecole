# 2751 수 정렬하기
import sys
input = sys.stdin.readline
n = int(input())
numList = []
for _ in range(n):
    numList.append(int(input()))

numList.sort()
for i in range(0, n):
    print(numList[i])

# 병합 정렬
n = int(input())
numlist = [int(input()) for _ in range(n)]

def mergeSort(numList):
    if len(numList) < 2:
        return numList
    
    mid = len(numList) // 2
    low = mergeSort(numList[:mid])
    high = mergeSort(numList[mid:])

    l, h = 0, 0
    merge = []
    while l < len(low) and h < len(high):
        if low[l] < high[h]:
            merge.append(low[l])
            l += 1
        else:
            merge.append(high[h])
            h += 1

    merge += low[l:]
    merge += high[h:]
    return merge

result = mergeSort(numlist)
for i in range(0, n):
    print(result[i])