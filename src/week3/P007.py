# 1929 소수 구하기
import math
import sys
input = sys.stdin.readline

def isPrim(num):
    if num == 1:
        return False
    
    for i in range(2, int(math.sqrt(num)) + 1):
        if num % i == 0:
            return False
    return True

m, n = map(int, input().split())

for i in range(m, n+1):
    if isPrim(i):
        print(i)