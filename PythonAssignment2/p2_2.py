m, n = map(int, input().split())

matrix = [list(map(int, input().split())) for _ in range(m)]

prefix = [[0] * (n + 1) for _ in range(m + 1)]

for i in range(1, m + 1):
    for j in range(1, n + 1):
        prefix[i][j] = (matrix[i - 1][j - 1] +
                            prefix[i - 1][j] +
                            prefix[i][j - 1] -
                            prefix[i - 1][j - 1])

q = int(input())

for _ in range(q):
    r1, c1, r2, c2 = map(int, input().split())
    r1 += 1; c1 += 1; r2 += 1; c2 += 1
    total = (prefix[r2][c2]
             - prefix[r1 - 1][c2]
             - prefix[r2][c1 - 1]
             + prefix[r1 - 1][c1 - 1])
    print(total)
