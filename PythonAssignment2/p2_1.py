"""
    Problem Statement 2.1
You are given an array nums of size n consisting of integers. Your task is to preprocess the array
to efficiently compute the sum of elements within a given range. Specifically, you need to answer
multiple queries, where each query is given as a pair (left,right) and requires computing the sum of
elements from index left to index right (both inclusive).
Input Format
• An integer n representing the size of the array.
• An array nums of length n containing integer values.
• An integer q representing the number of queries.
• q pairs of integers (left,right) representing the range for each query.
Output Format
For each query, output a single integer representing the sum of elements from index left to index
right (inclusive).
Constraints
• 1 ≤ n ≤ 105
• −104 ≤ nums[i] ≤ 104
• 1 ≤ q ≤ 105
• 0 ≤ left ≤ right < n
Example
Input:
n = 10
nums = [3, 2, -1, 6, 5, 4, -3, 3, 7, 2]
q = 3
Queries = [(1, 4), (3, 7), (0, 9)]
Output:
12
15
28
Explanation:
• Sum from index 1 to 4 → 2+ (−1) +6+5 = 12
• Sum from index 3 to 7 → 6+5+4+ (−3) +3 = 15
• Sum from index 0 to 9 → Total sum of the array = 28
2
Implementation Guidelines
• Precompute the prefix sum array in O(n) time.
• Answer each query in O(1) time using the formula:
Range Sum(left,right) = prefixSum[right]−prefixSum[left−1]
If left = 0, then Range Sum(left,right) = prefixSum[right].

"""


n = int(input())
a = list(map(int, input().split()))

prefix = [0] * (n + 1)
for i in range(n):
    prefix[i + 1] = prefix[i] + a[i]

q = int(input())

for _ in range(q):
    l, r = map(int, input().split())
    total = prefix[r + 1] - prefix[l]
    print(total)
