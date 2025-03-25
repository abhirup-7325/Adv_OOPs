import string

lowercase_letters = string.ascii_lowercase  # 'abcdefghijklmnopqrstuvwxyz'

enumerated_letters = list(enumerate(lowercase_letters, start=1))

for index, letter in enumerated_letters:
    print(index, letter)
