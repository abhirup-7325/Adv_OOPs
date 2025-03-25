class PriorityQueue:
    """A min-heap based priority queue that always returns the highest priority item on pop."""
    
    def __init__(self):
        """Initialize an empty heap."""
        self.heap = []

    def _swap(self, i, j):
        """Swap two elements in the heap."""
        self.heap[i], self.heap[j] = self.heap[j], self.heap[i]

    def _heapify_up(self, index):
        """Restore heap property after insertion (bottom-up)."""
        parent = (index - 1) // 2
        while index > 0 and self.heap[index][0] < self.heap[parent][0]:  
            self._swap(index, parent)
            index = parent
            parent = (index - 1) // 2

    def _heapify_down(self, index):
        """Restore heap property after deletion (top-down)."""
        size = len(self.heap)
        while True:
            left = 2 * index + 1
            right = 2 * index + 2
            smallest = index

            if left < size and self.heap[left][0] < self.heap[smallest][0]:
                smallest = left
            if right < size and self.heap[right][0] < self.heap[smallest][0]:
                smallest = right

            if smallest != index:
                self._swap(index, smallest)
                index = smallest
            else:
                break

    def push(self, item, priority):
        """Insert an item into the priority queue."""
        self.heap.append((priority, item))  
        self._heapify_up(len(self.heap) - 1)

    def pop(self):
        """Remove and return the item with the highest priority (lowest priority number)."""
        if not self.heap:
            raise IndexError("Priority queue is empty.")

        if len(self.heap) == 1:
            return self.heap.pop()[1]

        top_priority = self.heap[0][1]
        self.heap[0] = self.heap.pop()
        self._heapify_down(0)
        return top_priority

    def peek(self):
        """Return the item with the highest priority without removing it."""
        if not self.heap:
            raise IndexError("Priority queue is empty.")
        return self.heap[0][1]

    def is_empty(self):
        """Check if the priority queue is empty."""
        return len(self.heap) == 0

    def __str__(self):
        """Print the priority queue for debugging."""
        return str([(priority, item) for priority, item in self.heap])


def main():
    pq = PriorityQueue()
    
    print("\n🔹 Inserting items with priority:")
    pq.push("Task A", 3)
    pq.push("Task B", 1)  # Highest priority
    pq.push("Task C", 2)
    pq.push("Task D", 4)
    
    print("\nCurrent Priority Queue:", pq)

    print("\n🔹 Popping items (should return highest priority first):")
    while not pq.is_empty():
        print(f"🛠️ Processing: {pq.pop()}")

if __name__ == "__main__":
    main()
