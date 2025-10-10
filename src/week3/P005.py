# 1931 회의실 배정
import sys
input = sys.stdin.readline

n = int(input())
meeting_times = []

for _ in range(n):
    start, end = map(int, input().split())
    meeting_times.append((start, end))

# 회의 시간을 종료 시간 기준 및 시작 시간 기준으로 정렬
meeting_times = sorted(meeting_times, key = lambda x: (x[1], x[0]))

count = 1
# 이전 회의 종료 시간
end_time = meeting_times[0][1]

for i in range(1, n):
    # 다음 회의 시작 시간이 이전 회의 종료 시간보다 크거나 같다면
    if (meeting_times[i][0] >= end_time):
        end_time = meeting_times[i][1]
        count += 1

print(count)