# 1541 최솟값을 만드는 괄호 배치 찾기
str = input()
# '-'를 기준으로 수식 분리
nums = str.split('-')

result = sum(map(int, nums[0].split('+')))

for i in range(1, len(nums)):
    subSum = sum(map(int, nums[i].split('+')))
    result -= subSum

print(result)