# 11399 ATM 인출 시간 계산하기
n = int(input())
pList = list(map(int, input().split()))

pList.sort()

result = 0
for i in range(1, n+1):
    result += sum(pList[0:i])

print(result)

# 삽입 정렬
n = int(input())
pList = list(map(int, input().split()))

result = 0
for i in range(1, len(pList)):
    for j in range(i, 0, -1):
        if pList[j] < pList[j-1]:
            pList[j], pList[j-1] = pList[j-1], pList[j]
        else:
            break

for i in range(1, n+1):
    result += sum(pList[0:i])
    
print(result)