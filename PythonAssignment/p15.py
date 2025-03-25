import heapq

def find_largest_n(collection, n):
    """Returns the N largest elements in the collection."""
    return heapq.nlargest(n, collection)

def find_smallest_n(collection, n):
    """Returns the N smallest elements in the collection."""
    return heapq.nsmallest(n, collection)


data = [10, 50, 2, 90, 34, 77, 23, 4, 88, 67]

N = 3
print(f"🔹 {N} Largest Elements: {find_largest_n(data, N)}")
print(f"🔹 {N} Smallest Elements: {find_smallest_n(data, N)}")
