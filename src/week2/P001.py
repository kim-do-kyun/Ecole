#2750(버블정렬)
n = int(input())
result = []
for i in range(n):
    result.append(int(input()))

for i in range(n-1):
    for j in range(n-i-1):
        # 앞의 원소가 뒤의 원소보다 크면 swap
        if result[j] > result[j+1]:
            result[j], result[j+1] = result[j+1], result[j]

for i in range(n):
    print(result[i])