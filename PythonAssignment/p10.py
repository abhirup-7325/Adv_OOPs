import time

class GeometricProgression:
    def __init__(self, a, q):
        """Initialize with first term (a) and common ratio (q)."""
        self.a = a
        self.q = q

    def generate_terms(self):
        """Generator for the geometric progression."""
        term = self.a
        while term <= 100000:
            yield term
            term *= self.q  # Compute next term
        return  # Stops when term exceeds 100,000

def main():
    """Main function to compute and time execution."""
    a = float(input("Enter the first term (a): "))  # Example: 2
    q = float(input("Enter the common ratio (q): "))  # Example: 3

    gp = GeometricProgression(a, q)

    # Measure total execution time
    start_total = time.time()

    print("\n📌 Geometric Progression Terms:")
    
    start_loop = time.time()  # Measure loop execution time
    for term in gp.generate_terms():
        print(term)
    end_loop = time.time()

    end_total = time.time()

    print("\n⏳ Execution Time:")
    print(f"🔹 Time spent within the loop: {end_loop - start_loop:.6f} seconds")
    print(f"🔹 Total execution time: {end_total - start_total:.6f} seconds")

if __name__ == "__main__":
    main()
