"""
    Assignment 1
Given two 3-dimensional matrices M1 and M2 of dimensions k × m × n. Each slice of the 3D
matrices M1 and M2 at depth i (i = 1,2,3,..., k) is a 2D submatrix of size m×n. design an efficient
algorithm to compute the following:
1. The difference between two consecutive (m×n) submatrices in both M1 and M2.
The difference between consecutive slices in a matrix M is defined as:
DM(i) = M[i]− M[i−1], for 1 ≤ i < k
2. The difference between the i
th (m×n) submatrix in M1 and the i
th (m×n) submatrix in M2.
The difference between corresponding slices in M1 and M2 is defined as:
DM1,M2
(i) = M1[i]− M2[i], for 1 ≤ i ≤ k
Input
• Two 3D integer matrices M1 and M2 of size k ×m×n.
Output
• A matrix representing the differences between consecutive slices in M1.
• A matrix representing the differences between consecutive slices in M2.
• A matrix representing the differences between corresponding slices in M1 and M2.
Constraints
• 1 ≤ k,m,n ≤ 500
• −106 ≤ M1[i][ j][l],M2[i][ j][l] ≤ 106

"""


import numpy as np

n, m, k = map(int, input().split())

m1 = np.zeros((n, m, k), dtype=int)
m2 = np.zeros((n, m, k), dtype=int)

for i in range(n):
    for j in range(m):
        values = list(map(int, input().split()))
        for l in range(k):
            m1[i][j][l] = values[l]
            
            

for i in range(n):
    for j in range(m):
        values = list(map(int, input().split()))
        for l in range(k):
            m2[i][j][l] = values[l]

d1 = np.zeros((n, m, k), dtype=int)
d2 = np.zeros((n, m, k), dtype=int)
d12 = np.zeros((n, m, k), dtype=int)

for i in range(1, n):
    for j in range(1, m):
        for l in range(1, k):
            d1[i][j][l] = m1[i][j][l] - m1[i - 1][j][l]
            d2[i][j][l] = m2[i][j][l] - m2[i - 1][j][l]

for i in range(n):
    for j in range(m):
        for l in range(k):
            d12[i][j][l] = m1[i][j][l] - m2[i][j][l]
