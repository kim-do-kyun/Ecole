#1920 수 찾기(이진탐색)
import sys
input = sys.stdin.readline

n = int(input())
nList = list(map(int, input().split()))
nList.sort()

m = int(input())
mList = list(map(int, input().split()))

for m in mList:
    left = 0
    right = n-1

    while left <= right:
        mid = (left + right) // 2
        if m > nList[mid]:
            left = mid + 1
        elif m < nList[mid]:
            right = mid - 1
        else:
            left = mid
            right = mid
            break

    if left == mid and right == mid:
        print(1)
    else:
        print(0)