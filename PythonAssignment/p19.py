import cProfile

def find_pythagorean_triples(limit):
    """Find all Pythagorean triples up to a given limit."""
    triples = []
    for a in range(1, limit):
        for b in range(a, limit):  # Avoid duplicate pairs (b >= a)
            c = (a**2 + b**2) ** 0.5
            if c.is_integer():  # Check if c is a whole number
                triples.append((a, b, int(c)))
    return triples

# ==============================
# 🚀 Run and Profile Code
# ==============================
if __name__ == "__main__":
    cProfile.run("find_pythagorean_triples(500)")
