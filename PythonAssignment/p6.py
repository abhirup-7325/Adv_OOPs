"""
    
    6.	Write a simple program which loops over a list of user data (tuples containing a username, email and age) and adds each user to a directory if the user is at least 16 years old. You do not need to store the age. Write a simple exception hierarchy which defines a different exception for each of these error conditions:
•	the username is not unique
•	the age is not a positive integer
•	the user is under 16
•	the email address is not valid (a simple check for a username, the @ symbol and a domain name is sufficient)

    """
    
import re

# Custom Exception Hierarchy
class UserRegistrationError(Exception):
    """Base exception class for user registration errors."""
    pass

class UsernameNotUniqueError(UserRegistrationError):
    """Raised when the username is not unique."""
    pass

class InvalidAgeError(UserRegistrationError):
    """Raised when the age is not a positive integer."""
    pass

class UnderageError(UserRegistrationError):
    """Raised when the user is under 16 years old."""
    pass

class InvalidEmailError(UserRegistrationError):
    """Raised when the email address is not valid."""
    pass


class UserDirectory:
    def __init__(self):
        self.users = {}  # Dictionary to store users {username: email}

    def validate_email(self, email):
        """Checks if the email is valid using a basic regex pattern."""
        pattern = r"^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$"
        if not re.match(pattern, email):
            raise InvalidEmailError(f"❌ Invalid email format: {email}")

    def add_user(self, username, email, age):
        """Validates and adds a user to the directory if valid."""
        if username in self.users:
            raise UsernameNotUniqueError(f"❌ Username '{username}' is already taken.")
        if not isinstance(age, int) or age <= 0:
            raise InvalidAgeError(f"❌ Invalid age '{age}'. Age must be a positive integer.")
        if age < 16:
            raise UnderageError(f"❌ User '{username}' is under 16 years old.")
        
        self.validate_email(email)  # Validate email format

        # Store user if all validations pass
        self.users[username] = email
        print(f"✅ User '{username}' added successfully!")

    def display_users(self):
        """Displays all successfully registered users."""
        if not self.users:
            print("📂 No users registered yet.")
        else:
            print("\n📜 Registered Users:")
            for username, email in self.users.items():
                print(f"👤 {username} - 📧 {email}")


def main():
    """Menu-driven program to handle user registration dynamically."""
    directory = UserDirectory()

    user_data = [
        ("alice", "alice@example.com", 20),
        ("bob", "bob_at_example.com", 25),  # Invalid email
        ("charlie", "charlie@example.com", 15),  # Underage
        ("david", "david@example.com", -5),  # Invalid age
        ("alice", "alice2@example.com", 30),  # Duplicate username
        ("eve", "eve@example.com", 18)
    ]

    print("🚀 Processing User Data...\n")

    for username, email, age in user_data:
        try:
            directory.add_user(username, email, age)
        except UserRegistrationError as e:
            print(f"⚠️ Error: {e}")
        except ValueError:
            print("⚠️ Error: Age must be a number.")

    print("\n===== Final User Directory =====")
    directory.display_users()


if __name__ == "__main__":
    main()
