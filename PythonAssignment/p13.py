from collections import Counter

class CustomString:
    """Class that overloads the len() operator to return word length differently 
       based on repetition and finds the most frequent words."""

    def __init__(self, text):
        self.text = text

    def __len__(self):
        """Overloaded len() function:
        - If all words are unique, returns normal length.
        - If there are repetitions, returns total length of repeated words only.
        """
        words = self.text.split()
        word_counts = Counter(words)

        # Check if there are repetitions
        repeated_words = {word: count for word, count in word_counts.items() if count > 1}
        
        if repeated_words:
            # Return total length of only repeated words
            return sum(len(word) * count for word, count in repeated_words.items())
        else:
            # Default: return total string length
            return len(self.text)

    def most_frequent(self, n=3):
        """Returns the `n` most common words in the text."""
        words = self.text.split()
        word_counts = Counter(words)
        return word_counts.most_common(n)


# ==============================
# 🚀 Testing the CustomString Class
# ==============================
def main():
    text = input("Enter a string: ")
    custom_str = CustomString(text)

    print(f"\n🔢 Overloaded len(): {len(custom_str)}")  # Custom len behavior
    print(f"🏆 Most common words: {custom_str.most_frequent()}")  # Most common words


if __name__ == "__main__":
    main()
