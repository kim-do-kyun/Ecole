# 2750
n = int(input())
result = []
for i in range(n):
    result.append(int(input()))
result.sort()

for i in range(n):
    print(result[i])

## 병합 정렬(merge sort)

import sys

def merge(left, right):
    result = []
    i, j = 0, 0
    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1

    result.extend(left[i:])
    result.extend(right[j:])
    return result

def merge_sort(arr):
    if len(arr) <= 1:
        return arr
    
    mid = len(arr) // 2
    left = arr[:mid]
    right = arr[mid:]

    return merge(merge_sort(left), merge_sort(right))

n = int(input())
numList = []

for i in range(n):
    numList.append(int(input()))

result = merge_sort(numList)

for nums in result:
    print(nums)