import string

class TextAnalyzer:
    def __init__(self, text):
        """Initialize with a given text."""
        self.text = text.lower()  # Convert text to lowercase for case insensitivity

    def clean_text(self):
        """Removes punctuation and splits text into words."""
        translator = str.maketrans('', '', string.punctuation)  # Remove punctuation
        return self.text.translate(translator).split()  # Convert to a list of words

    def find_palindromes(self):
        """Finds all palindromic words in the text."""
        words = self.clean_text()
        return [word for word in words if word == word[::-1] and len(word) > 1]  # Exclude single letters

    def find_unique_words(self):
        """Finds all unique words in the text."""
        words = self.clean_text()
        return list(set(words))  # Convert to a set to remove duplicates

    @classmethod
    def analyze_text(cls, text):
        """Class method to analyze text for palindromes and unique words."""
        analyzer = cls(text)
        return {
            "palindromes": analyzer.find_palindromes(),
            "unique_words": analyzer.find_unique_words()
        }

def main():
    """Main function to take user input and analyze text."""
    text = input("Enter a text to analyze: ")

    result = TextAnalyzer.analyze_text(text)

    print("\n🔍 Analysis Results:")
    print(f"📜 Unique Words: {result['unique_words']}")
    print(f"🔄 Palindromes: {result['palindromes']}")

if __name__ == "__main__":
    main()
