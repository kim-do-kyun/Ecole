#2018
# 투포인터 이동 원칙
# sum > n(목표) : 값이 너무 크므로 start를 앞으로 이동
# sum < n: 값이 너무 작으므로 end를 앞으로 이동
# sum == n : 문제에서 해를 카운트 하라 했으니 end를 증가시켜 새로운 해를 찾음
n = int(input())
cnt, current_sum, start = 0, 0, 1

# end 포인터가 1부터 n까지 순차적으로 이동
for end in range(1, n+1):
    # 윈도우 확장: 현재 end값에 합계를 더함
    current_sum += end

    # 윈도우 축소: 합계가 n보다 크면, n보다 작거나 같아질때까지 start를 이동시키며 값을 감소
    while current_sum > n:
        current_sum -= start
        start += 1

    # 값 확인: 합계가 n경우 cnt += 1
    if current_sum == n:
        cnt += 1

print(cnt)