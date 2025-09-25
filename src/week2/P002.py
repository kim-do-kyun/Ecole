# 1427 내림차순으로 자릿수 정렬하기
n = input()
numList = list(map(int, n))
numList.sort(reverse=True)
for i in numList:
    print(i, end='')

# 선택 정렬
n = input()
numList = list(map(int, n))

for i in range(len(numList)):
    maxIndex = i
    for j in range(i + 1, len(numList)):
        if numList[j] > numList[maxIndex]:
            maxIndex = j
    
    numList[i], numList[maxIndex] = numList[maxIndex], numList[i]
        
    
for i in numList:
    print(i, end='')