"""
4.	Write a regular expression to validate a phone number.
"""
    
import re

class PhoneNumberValidator:
    def __init__(self):
        self.pattern = re.compile(r"^\+?\d{1,3}?\s?\d{10}$")

    def validate(self, phone_number):
        """Validate a phone number against the regex pattern."""
        return bool(self.pattern.match(phone_number))


def main():
    """Main function to test phone number validation."""
    validator = PhoneNumberValidator()
    phone_number = input("Enter a phone number to validate: ")
    
    if validator.validate(phone_number):
        print("Valid phone number!")
    else:
        print("Invalid phone number!")


if __name__ == "__main__":
    main()
