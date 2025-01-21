/*
 * Design a BankAcct class with account number, balance and interest rate as attribute. Interest
rate is same for all account. Support must be there to initialize, change and display the interest rate.
Also supports are to be there to return balance and calculate interest.
 */


class BankAcct {
    private static double _interestRate = 10.0;
    private int _accountNumber;
    private double _balance;

    public BankAcct(int accountNumber, double balance) {
        _accountNumber = accountNumber;
        _balance = balance;
    }
    
    public static void setInterestRate(double interestRate) {
        _interestRate = interestRate;
    }

    public static double getInterestRate() {
        return _interestRate;
    }

    public static void displayInterestRate() {
        System.out.println("Interest Rate: " + _interestRate);
    }

    public double getBalance() {
        return _balance;
    }

    public double calculateInterest() {
        return _balance * _interestRate / 100;
    }
}


public class P7 {
    public static void main(String[] args) {
        
    }
}
