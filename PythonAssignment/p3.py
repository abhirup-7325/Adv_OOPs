"""
3.	Print first 10 odd and even numbers using iterators and compress. You can use duck typing.
"""

from itertools import compress

class OddEvenIterator:
    def __init__(self, count):
        self.count = count
        self.numbers = range(1, count * 2 + 1)

    def get_odds(self):
        return compress(self.numbers, [(num % 2 != 0) for num in self.numbers])

    def get_evens(self):
        return compress(self.numbers, [(num % 2 == 0) for num in self.numbers])


def print_numbers(iterator, label):
    print(f"{label}: ", list(iterator))


def main():
    count = 10
    iterator = OddEvenIterator(count)
    
    print_numbers(iterator.get_odds(), "First 10 Odd Numbers")
    print_numbers(iterator.get_evens(), "First 10 Even Numbers")


if __name__ == "__main__":
    main()
