"""
5.	Write first seven Fibinacci numbers using generator next function/ yield in python. 
Trace and memorize the function. Also check whether a user given number is Fibinacci or not.
"""

import bisect

class FibonacciGenerator:
    def __init__(self, count):
        self.count = count
        self.memo = {0: 0, 1: 1}
        self.fib_list = list(self.generate_fibonacci())

    def generate_fibonacci(self):
        a, b = 0, 1
        for _ in range(self.count):
            yield a
            a, b = b, a + b 

    def fibonacci_memoized(self, num):
        if num not in self.memo:
            self.memo[num] = self.fibonacci_memoized(num - 1) + self.fibonacci_memoized(num - 2)
        return self.memo[num]

    def is_fibonacci(self, num):
        index = bisect.bisect_left(self.fib_list, num)
        return index < len(self.fib_list) and self.fib_list[index] == num


def main():
    count = 20
    fib_gen = FibonacciGenerator(count)
    
    print(f"First {count} Fibonacci numbers:")
    print(fib_gen.fib_list)

    user_num = int(input("Enter a number to check if it's a Fibonacci number: "))
    
    if fib_gen.is_fibonacci(user_num):
        print(f" {user_num} is a Fibonacci number!")
    else:
        print(f"{user_num} is NOT a Fibonacci number.")


if __name__ == "__main__":
    main()
