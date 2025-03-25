"""8.	Create a list of all the numbers up to N=50 which are multiples of five using anonymous function."""


N = 50
multiples_of_five = list(filter(lambda x: x % 5 == 0, range(1, N+1)))

print(multiples_of_five)
