# 2166 다각형의 넓이 구하기
N = int(input())
figure = []

for _ in range(N):
    figure.append(list(map(int,input().split(' '))))
figure.append(figure[0])

answer = 0
for i in range(N):
    answer += figure[i][0]*figure[i+1][1] - figure[i+1][0]*figure[i][1]

print(abs(answer)/2)