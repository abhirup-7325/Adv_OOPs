"""
1.	Write a prime generator program using only primes and using python loops.
"""

class SievePrimeGenerator:
    def __init__(self, limit):
        self.limit = limit
        self.primes = []

    def generate(self):
        if self.limit < 2:
            return []
        
        sieve = [True] * (self.limit + 1)
        sieve[0] = sieve[1] = False

        for num in range(2, int(self.limit ** 0.5) + 1):
            if sieve[num]: 
                for multiple in range(num * num, self.limit + 1, num):
                    sieve[multiple] = False 

        self.primes = [num for num, is_prime in enumerate(sieve) if is_prime]
        return self.primes


def main():
    limit = int(input("Enter the upper limit to generate primes: "))
    prime_gen = SievePrimeGenerator(limit)
    primes = prime_gen.generate()
    print(f"Primes up to {limit}: {primes}")


if __name__ == "__main__":
    main()
