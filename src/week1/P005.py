#1253
n = int(input())
numList = list(map(int, input().split()))
numList.sort()

result = 0

for i in range(n):
    target = numList[i]
    start, end = 0, n - 1

    while start < end:
        # 포인터가 자기 자신을 가리키는 경우 스킵
        if start == i:
            start += 1
            continue
        if end == i:
            end -= 1
            continue

        current_sum = numList[start] + numList[end]

        # 두 수의 합이 목표값과 같은 경우
        if current_sum == target:
            result += 1
            break
        # 합이 목표값보다 작은 경우
        elif current_sum < target:
            start += 1
        # 합이 목표값보다 큰 경우
        else:
            end -= 1

print(result)