# 21568 Ax+By=C
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

def gcd(a, b):
    while b != 0:
        a, b = b, a % b
    return abs(a)

def extended_euclidean(a, b):
    if b == 0:
        return 1, 0
    
    x_prime, y_prime = extended_euclidean(b, a % b)
    return y_prime, x_prime - (a//b) * y_prime

a, b, c = map(int, input().split())
common_divisor = gcd(a, b)

if c % common_divisor != 0:
    print(-1)
else:
    x0, y0 = extended_euclidean(a, b)
    mok = c // common_divisor
    print(f"{x0 * mok} {y0 * mok}")