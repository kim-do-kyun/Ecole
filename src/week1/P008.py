# 2164
# 일반 큐 쓰면 시간초과
n = int(input())
queue = list(range(1, n+1))

while len(queue) > 1:
    queue.pop(0)
    queue.append(queue[0])
    queue.pop(0)

print(queue[0])

# deque사용으로 시간초과 해결
from collections import deque
n = int(input())
queue = deque(range(1, n+1))

while len(queue) > 1:
    queue.popleft()
    queue.append(queue[0])
    queue.popleft()

print(queue[0])