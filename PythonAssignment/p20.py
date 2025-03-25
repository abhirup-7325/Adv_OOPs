import os

class Tokenizer:
    def __init__(self):
        self.token_dict = {}
        self.next_id = 1  # Unique token ID starts from 1
    
    def tokenize(self, text):
        tokens = text.strip().split()  # Split text into words
        token_ids = []
        for token in tokens:
            if token not in self.token_dict:
                self.token_dict[token] = self.next_id
                self.next_id += 1
            token_ids.append(self.token_dict[token])
        return token_ids

def read_and_tokenize(filename):
    """ Reads a file, tokenizes words, and converts them into unique numerical values. """
    tokenizer = Tokenizer()
    tokenized_lines = []
    
    with open(filename, 'r', encoding='utf-8') as file:
        for line in file:
            tokenized_lines.append(tokenizer.tokenize(line))
    
    return tokenized_lines, tokenizer.token_dict

def pad_lines(tokenized_lines):
    """ Converts lines of different lengths to the same length by padding with 0s. """
    max_length = max(len(line) for line in tokenized_lines)
    
    padded_lines = [line + [0] * (max_length - len(line)) for line in tokenized_lines]
    return padded_lines

# ==============================
# 🚀 Run the Program
# ==============================
if __name__ == "__main__":
    filename = "sample.txt"  # Change this to your actual file name

    if not os.path.exists(filename):
        print(f"File '{filename}' not found!")
    else:
        tokenized_lines, token_dict = read_and_tokenize(filename)
        padded_lines = pad_lines(tokenized_lines)

        print("\n🔹 Unique Token Dictionary:")
        print(token_dict)

        print("\n🔹 Tokenized Lines (Before Padding):")
        for line in tokenized_lines:
            print(line)

        print("\n🔹 Tokenized Lines (After Padding):")
        for line in padded_lines:
            print(line)
