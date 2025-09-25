# 110004 k번째 수
import math
n, k = map(int, input().split())
numList = list(map(int, input().split()))
def solution(n, k, numList):
    numList.sort()
    return numList[k-1]

print(solution(n, k, numList))

# 퀵정렬 사용
n, k = map(int, input().split())
numList = list(map(int, input().split()))
def solution(n, k, numList):
    quickSort(0, n-1, k-1)
    return numList[k-1]

def quickSort(start, end, k):
    global numList
    if (start < end):
        pivot = partition(start, end)
        if (pivot < k):
            quickSort(pivot + 1, end, k)
        elif (k < pivot):
            quickSort(start, pivot - 1, k)
        else:
            return

def partition(start, end):
    global numList

    if (start + 1 == end):
        if numList[start] > numList[end]:
            swap(start, end)
        return end
    
    pivot = (start + end) // 2
    swap(start, pivot)
    pivot = start
    i = start + 1
    j = end
    while(i <= j):
        # 피벗보다 큰 수가 나올 때 까지 i 증가
        while numList[pivot] > numList[i] and i < len(numList) - 1:
            i += 1
        
        # 피벗보다 작은 수가 나올때 까지 j 감소
        while(numList[pivot] < numList[j] and j > 0):
            j -= 1
        if i <= j:
            swap(i, j)
            i += 1
            j -= 1
    tmp = numList[pivot]
    numList[start] = numList[j]
    numList[j] = tmp 
    return j
    
def swap(a, b):
    global numList
    numList[a], numList[b] = numList[b], numList[a]

print(solution(n, k, numList))