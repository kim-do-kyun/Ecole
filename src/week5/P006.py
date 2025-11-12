# 14425 문자열 찾기
n, m = map(int, input().split())

n_string = set()
for i in range(n):
    n_string.add(input())

count = 0
for i in range(m):
    string = input()
    if string in n_string:
        count += 1

print(count)