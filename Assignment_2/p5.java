package Assignment_2;

/*
 * Each customer of a bank has customer id, name, and current loan amount and phone number. One
can change the attributes like name, phone number. A customer may ask for loan of certain amount.

It is granted provided the sum of current loan amount and asked amount does not exceed credit limit
(fixed amount for all customer). A customer can be a privileged customer. For such customers credit
limit is higher. Once a loan is sanctioned necessary updates should be made. Any type of customer
should be able to find his credit limit, current loan amount and amount of loan (s)he can seek. No
customer can change customer id once created. Print customer name when the object is printed by
toString() method.
Design and implement the classes. Show the working through a menu driven user interface.
 */
import java.util.Scanner;

interface Customer {
    void setPhone(long phone);
    void setName(String name);
    int getCreditLimit();
    void takeLoan(double amount);
    void payLoan(double amount);
    void displayDetails();
}

abstract class BaseCustomer implements Customer {
    protected long customerID;
    protected String name;
    protected long phone;
    public double loan;

    BaseCustomer(long customerID, String name, long phone) {
        this.customerID = customerID;
        this.name = name;
        this.phone = phone;
        this.loan = 0;
    }

    @Override
    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void takeLoan(double amount) {
        int creditLimit = getCreditLimit();
        if (loan >= creditLimit) {
            System.out.println("Credit limit reached. Please clear the debt.");
            return;
        }

        double availableCredit = creditLimit - loan;
        if (amount > availableCredit) {
            System.out.println("You can only take up to " + availableCredit + " more.");
        } else {
            loan += amount;
            System.out.println("Loan of " + amount + " taken successfully.");
        }
    }

    @Override
    public void payLoan(double amount) {
        if (amount >= loan) {
            loan = 0;
            System.out.println("Loan fully repaid.");
        } else {
            loan -= amount;
            System.out.println("Partial payment made. Remaining loan: " + loan);
        }
    }

    @Override
    public void displayDetails() {
        System.out.println("Customer ID: " + customerID);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Loan: " + loan);
        System.out.println("Credit Limit: " + getCreditLimit());
    }
}

class PrivilegedCustomer extends BaseCustomer {
    private static final int CREDIT_LIMIT = 50000;

    PrivilegedCustomer(long customerID, String name, long phone) {
        super(customerID, name, phone);
    }

    @Override
    public int getCreditLimit() {
        return CREDIT_LIMIT;
    }
}

class NormalCustomer extends BaseCustomer {
    private static final int CREDIT_LIMIT = 25000;

    NormalCustomer(long customerID, String name, long phone) {
        super(customerID, name, phone);
    }

    @Override
    public int getCreditLimit() {
        return CREDIT_LIMIT;
    }
}

public class p5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Customer customer = null;

        System.out.println("Welcome to Customer Management System");
        System.out.print("Enter Customer ID: ");
        long customerID = in.nextLong();
        in.nextLine();
        System.out.print("Enter Name: ");
        String name = in.nextLine();
        System.out.print("Enter Phone Number: ");
        long phone = in.nextLong();

        System.out.println("Select Customer Type:");
        System.out.println("1. Privileged Customer");
        System.out.println("2. Normal Customer");
        int type = in.nextInt();

        customer = (type == 1) ? new PrivilegedCustomer(customerID, name, phone) : new NormalCustomer(customerID, name, phone);

        if (customer == null) {
            System.out.println("Invalid customer type. Exiting.");
            return;
        }

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. View Details");
            System.out.println("2. Update Name");
            System.out.println("3. Update Phone Number");
            System.out.println("4. Take Loan");
            System.out.println("5. Pay Loan");
            System.out.println("6. View Loan Details");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    customer.displayDetails();
                    break;
                case 2:
                    System.out.print("Enter new name: ");
                    customer.setName(in.nextLine());
                    System.out.println("Name updated successfully.");
                    break;
                case 3:
                    System.out.print("Enter new phone number: ");
                    customer.setPhone(in.nextLong());
                    System.out.println("Phone number updated successfully.");
                    break;
                case 4:
                    System.out.print("Enter loan amount to take: ");
                    customer.takeLoan(in.nextDouble());
                    break;
                case 5:
                    System.out.print("Enter loan amount to pay: ");
                    customer.payLoan(in.nextDouble());
                    break;
                case 6:
                    System.out.println("Current Loan Amount: " + customer.loan);
                    break;
                case 0:
                    System.out.println("Exiting the system. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        in.close();
    }
}
