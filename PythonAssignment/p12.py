class BankAccount:
    """Base class for a bank account with basic deposit, withdrawal, and balance functionalities."""
    
    def __init__(self, account_number, pin, balance=0.0):
        """Initialize account with account number, PIN, and optional balance."""
        self.account_number = account_number
        self.__pin = pin  # Private attribute for security
        self.__balance = balance  # Private attribute for balance
    
    def deposit(self, amount):
        """Deposit money into the account."""
        if amount > 0:
            self.__balance += amount
            print(f"✅ Deposited ${amount}. New balance: ${self.__balance:.2f}")
        else:
            print("❌ Deposit amount must be positive.")
    
    def withdraw(self, amount, pin):
        """Withdraw money if PIN is correct and sufficient balance is available."""
        if pin != self.__pin:
            print("❌ Incorrect PIN.")
            return
        if amount > self.__balance:
            print("❌ Insufficient balance.")
            return
        if amount <= 0:
            print("❌ Withdrawal amount must be positive.")
            return
        
        self.__balance -= amount
        print(f"✅ Withdrawn ${amount}. New balance: ${self.__balance:.2f}")
    
    def get_balance(self, pin):
        """Get account balance if PIN is correct."""
        if pin == self.__pin:
            return self.__balance
        print("❌ Incorrect PIN.")
        return None
    
    def change_pin(self, old_pin, new_pin):
        """Change account PIN if old PIN is correct."""
        if old_pin == self.__pin:
            self.__pin = new_pin
            print("✅ PIN changed successfully.")
        else:
            print("❌ Incorrect old PIN.")


class SavingsAccount(BankAccount):
    """Savings account that includes interest calculation."""
    
    def __init__(self, account_number, pin, balance=0.0, interest_rate=0.02):
        """Initialize savings account with an interest rate."""
        super().__init__(account_number, pin, balance)
        self.interest_rate = interest_rate
    
    def apply_interest(self, pin):
        """Apply interest to the balance if the PIN is correct."""
        balance = self.get_balance(pin)
        if balance is not None:
            interest = balance * self.interest_rate
            self.deposit(interest)
            print(f"✅ Interest of ${interest:.2f} applied. New balance: ${self.get_balance(pin):.2f}")


class FeeSavingsAccount(SavingsAccount):
    """Savings account with withdrawal fees."""
    
    def __init__(self, account_number, pin, balance=0.0, interest_rate=0.02, withdrawal_fee=5.0):
        """Initialize fee savings account with a withdrawal fee."""
        super().__init__(account_number, pin, balance, interest_rate)
        self.withdrawal_fee = withdrawal_fee
    
    def withdraw(self, amount, pin):
        """Withdraw money after deducting the fee."""
        total_amount = amount + self.withdrawal_fee
        balance = self.get_balance(pin)
        
        if balance is None:
            return  
        if total_amount > balance:
            print("❌ Insufficient balance after applying withdrawal fee.")
            return

        super().withdraw(total_amount, pin)
        print(f"💸 Withdrawal fee of ${self.withdrawal_fee:.2f} applied.")


# ==============================
# 🚀 Menu-Driven System
# ==============================
def main():
    accounts = {}  # Store accounts with account numbers as keys

    while True:
        print("\n🏦 Welcome to the Bank System 🏦")
        print("1️⃣  Create Bank Account")
        print("2️⃣  Deposit Money")
        print("3️⃣  Withdraw Money")
        print("4️⃣  Check Balance")
        print("5️⃣  Apply Interest (Savings & FeeSavings Only)")
        print("6️⃣  Change PIN")
        print("7️⃣  Exit")
        
        choice = input("Enter your choice: ")

        if choice == "1":
            account_number = input("Enter account number: ")
            pin = input("Set your 4-digit PIN: ")
            balance = float(input("Enter initial balance: "))
            print("Choose account type:")
            print("1️⃣  Bank Account")
            print("2️⃣  Savings Account")
            print("3️⃣  Fee Savings Account")
            acc_type = input("Enter choice: ")

            if acc_type == "1":
                accounts[account_number] = BankAccount(account_number, pin, balance)
            elif acc_type == "2":
                interest_rate = float(input("Enter interest rate (e.g., 0.02 for 2%): "))
                accounts[account_number] = SavingsAccount(account_number, pin, balance, interest_rate)
            elif acc_type == "3":
                interest_rate = float(input("Enter interest rate: "))
                fee = float(input("Enter withdrawal fee: "))
                accounts[account_number] = FeeSavingsAccount(account_number, pin, balance, interest_rate, fee)
            else:
                print("❌ Invalid choice. Try again.")
                continue

            print(f"✅ Account created successfully for {account_number}.")

        elif choice == "2":
            acc_num = input("Enter account number: ")
            if acc_num in accounts:
                amount = float(input("Enter deposit amount: "))
                accounts[acc_num].deposit(amount)
            else:
                print("❌ Account not found.")

        elif choice == "3":
            acc_num = input("Enter account number: ")
            if acc_num in accounts:
                pin = input("Enter PIN: ")
                amount = float(input("Enter withdrawal amount: "))
                accounts[acc_num].withdraw(amount, pin)
            else:
                print("❌ Account not found.")

        elif choice == "4":
            acc_num = input("Enter account number: ")
            if acc_num in accounts:
                pin = input("Enter PIN: ")
                balance = accounts[acc_num].get_balance(pin)
                if balance is not None:
                    print(f"💰 Current Balance: ${balance:.2f}")
            else:
                print("❌ Account not found.")

        elif choice == "5":
            acc_num = input("Enter account number: ")
            if acc_num in accounts and isinstance(accounts[acc_num], SavingsAccount):
                pin = input("Enter PIN: ")
                accounts[acc_num].apply_interest(pin)
            else:
                print("❌ Not a valid Savings or Fee Savings account.")

        elif choice == "6":
            acc_num = input("Enter account number: ")
            if acc_num in accounts:
                old_pin = input("Enter old PIN: ")
                new_pin = input("Enter new PIN: ")
                accounts[acc_num].change_pin(old_pin, new_pin)
            else:
                print("❌ Account not found.")

        elif choice == "7":
            print("👋 Exiting... Thank you for using our bank system!")
            break

        else:
            print("❌ Invalid choice. Please enter a number from 1 to 7.")


if __name__ == "__main__":
    main()
