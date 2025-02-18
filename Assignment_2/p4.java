package Assignment_2;

/*
 * Every bank account holds an account no and a calculateInterest method. A customer can have a
“SavingsAccount” and/or a “CurrentAccount”. For current account, there is a method called
displayOverdraftAmount(). Different accounts can have different interest rates. User should be able
to verify the existence of an account, adding new account and displaying all accounts. Implement
appropriate objects utilizing inheritance and show its behavior from the parent class.
 */

import java.util.*;

abstract class Account {
    protected long accountNumber;
    protected double balance;
    protected double interestRate;

    public Account(long accountNumber, double balance, double interestRate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interestRate = interestRate;
    }

    public abstract double calculateInterest(double time);
    public abstract void withdraw(double amount);
    public abstract void deposit(double amount);
    
    public long getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double newRate) { this.interestRate = newRate; }
}

class SavingsAccount extends Account {
    public SavingsAccount(long accountNumber, double balance, double interestRate) {
        super(accountNumber, balance, interestRate);
    }

    @Override
    public double calculateInterest(double time) {
        return balance * interestRate * time / 100;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        }
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }
}

class CurrentAccount extends Account {
    private double overdraft = 0;
    private static final double OVERDRAFT_LIMIT = 2000.00;

    public CurrentAccount(long accountNumber, double balance, double interestRate) {
        super(accountNumber, balance, interestRate);
    }

    @Override
    public double calculateInterest(double time) {
        return overdraft > 0 ? overdraft * interestRate * time / 100 : 0;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > balance) {
            double overdraftNeeded = amount - balance;
            if (overdraft + overdraftNeeded > OVERDRAFT_LIMIT) {
                System.out.println("Overdraft limit reached. Withdrawal denied.");
                return;
            }
            overdraft += overdraftNeeded;
            balance = 0;
        } else {
            balance -= amount;
        }
        System.out.println("Withdrawal successful. Balance: " + balance + ", Overdraft: " + overdraft);
    }

    @Override
    public void deposit(double amount) {
        if (overdraft > 0) {
            if (amount >= overdraft) {
                amount -= overdraft;
                overdraft = 0;
            } else {
                overdraft -= amount;
                amount = 0;
            }
        }
        balance += amount;
        System.out.println("Deposit successful. Balance: " + balance + ", Overdraft: " + overdraft);
    }
}

class Bank {
    private final Map<Long, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        if (accounts.containsKey(account.getAccountNumber())) {
            System.out.println("Account already exists.");
        } else {
            accounts.put(account.getAccountNumber(), account);
            System.out.println("Account successfully created.");
        }
    }

    public Account getAccount(long accountNumber) {
        return accounts.get(accountNumber);
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }
        for (Account acc : accounts.values()) {
            System.out.println("Account Number: " + acc.getAccountNumber() + ", Balance: " + acc.getBalance() + ", Type: " + (acc instanceof SavingsAccount ? "Savings" : "Current"));
        }
    }
}

public class p4 {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Account\n2. Withdraw\n3. Deposit\n4. Calculate Interest\n5. Set Interest Rate\n6. Display All Accounts\n7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    long accNo = scanner.nextLong();
                    System.out.print("Enter Initial Balance: ");
                    double balance = scanner.nextDouble();
                    System.out.print("Enter Interest Rate: ");
                    double rate = scanner.nextDouble();
                    System.out.print("Account Type (1: Savings, 2: Current): ");
                    int type = scanner.nextInt();

                    Account account = type == 1 ? new SavingsAccount(accNo, balance, rate) : new CurrentAccount(accNo, balance, rate);
                    bank.addAccount(account);
                    break;
                case 2:
                    System.out.print("Enter Account Number: ");
                    Account acc = bank.getAccount(scanner.nextLong());
                    if (acc != null) {
                        System.out.print("Enter amount to withdraw: ");
                        acc.withdraw(scanner.nextDouble());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    acc = bank.getAccount(scanner.nextLong());
                    if (acc != null) {
                        System.out.print("Enter amount to deposit: ");
                        acc.deposit(scanner.nextDouble());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Account Number: ");
                    acc = bank.getAccount(scanner.nextLong());
                    if (acc != null) {
                        System.out.print("Enter time period (years): ");
                        System.out.println("Interest: " + acc.calculateInterest(scanner.nextDouble()));
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter Account Number: ");
                    acc = bank.getAccount(scanner.nextLong());
                    if (acc != null) {
                        System.out.print("Enter new interest rate: ");
                        acc.setInterestRate(scanner.nextDouble());
                        System.out.println("Interest rate updated.");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 6:
                    bank.displayAllAccounts();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
