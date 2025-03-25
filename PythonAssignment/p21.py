import re

class NumberToWords:
    """Class to convert numbers into words without external libraries."""

    def __init__(self):
        self.ones = ["", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]
        self.teens = ["", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"]
        self.tens = ["", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"]
        self.thousands = ["", "thousand", "million", "billion"]

    def convert_number(self, num):
        """Convert a number to its word representation."""
        num = int(num)  # Convert to integer
        if num == 0:
            return "zero"
        words = []
        chunk_count = 0  # To track thousand, million, etc.

        while num > 0:
            chunk = num % 1000
            if chunk:
                words.insert(0, self.convert_chunk(chunk) + (" " + self.thousands[chunk_count] if self.thousands[chunk_count] else ""))
            num //= 1000
            chunk_count += 1
        
        return " ".join(words).strip()

    def convert_chunk(self, num):
        """Convert a 3-digit chunk to words."""
        words = []
        if num >= 100:
            words.append(self.ones[num // 100] + " hundred")
            num %= 100
        if 10 < num < 20:
            words.append(self.teens[num - 10])
        else:
            words.append(self.tens[num // 10])
            words.append(self.ones[num % 10])
        return " ".join(filter(bool, words))

def extract_numbers(text):
    """Extract numbers from text using regex."""
    return re.findall(r'\b\d+\b', text)  # Match only whole numbers

def process_file(filename):
    """Read file, extract numbers, and convert them to words."""
    converter = NumberToWords()

    try:
        with open(filename, "r", encoding="utf-8") as file:
            text = file.read()
        
        numbers = extract_numbers(text)
        number_words = {num: converter.convert_number(num) for num in numbers}
        
        return number_words

    except FileNotFoundError:
        print(f"Error: File '{filename}' not found!")
        return {}

# ==============================
# 🚀 Run the Program
# ==============================
if __name__ == "__main__":
    filename = "numbers.txt"  # Update with your file name

    converted_numbers = process_file(filename)

    if converted_numbers:
        print("\n🔹 Converted Numbers:")
        for num, words in converted_numbers.items():
            print(f"{num} ➝ {words}")
