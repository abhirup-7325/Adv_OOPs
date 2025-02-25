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
import java.util.ArrayList;
import java.util.Scanner;

abstract class Customer {
    private final int customerId;
    private String name;
    private String phoneNumber;
    protected double currentLoanAmount;
    protected final double creditLimit;

    public Customer(int customerId, String name, String phoneNumber, double creditLimit) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.creditLimit = creditLimit;
        this.currentLoanAmount = 0.0;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean requestLoan(double amount) {
        if (currentLoanAmount + amount <= creditLimit) {
            currentLoanAmount += amount;
            System.out.println("Loan granted! New loan balance: $" + currentLoanAmount);
            return true;
        } else {
            System.out.println("Loan request denied! Exceeds credit limit.");
            return false;
        }
    }

    public void displayInfo() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Credit Limit: $" + creditLimit);
        System.out.println("Current Loan Amount: $" + currentLoanAmount);
        System.out.println("Available Loan Amount: $" + (creditLimit - currentLoanAmount));
    }

    @Override
    public String toString() {
        return name;
    }
}

class RegularCustomer extends Customer {
    private static final double REGULAR_CREDIT_LIMIT = 500000.0;

    public RegularCustomer(int customerId, String name, String phoneNumber) {
        super(customerId, name, phoneNumber, REGULAR_CREDIT_LIMIT);
    }
}

class PrivilegedCustomer extends Customer {
    private static final double PRIVILEGED_CREDIT_LIMIT = 1000000.0;

    public PrivilegedCustomer(int customerId, String name, String phoneNumber) {
        super(customerId, name, phoneNumber, PRIVILEGED_CREDIT_LIMIT);
    }
}

class BankSystem {
    private final ArrayList<Customer> customers = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("\nBank System Menu:");
            System.out.println("1. Add Customer");
            System.out.println("2. Modify Customer Details");
            System.out.println("3. Request Loan");
            System.out.println("4. View Customer Info");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> modifyCustomer();
                case 3 -> requestLoan();
                case 4 -> viewCustomer();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void addCustomer() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Is the customer privileged? (yes/no): ");
        String type = scanner.nextLine();

        Customer customer = type.equalsIgnoreCase("yes") ?
                new PrivilegedCustomer(id, name, phone) :
                new RegularCustomer(id, name, phone);

        customers.add(customer);
        System.out.println("Customer added successfully!");
    }

    private void modifyCustomer() {
        Customer customer = findCustomer();
        if (customer != null) {
            System.out.print("Enter new name (or press Enter to keep unchanged): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) customer.setName(newName);

            System.out.print("Enter new phone number (or press Enter to keep unchanged): ");
            String newPhone = scanner.nextLine();
            if (!newPhone.isEmpty()) customer.setPhoneNumber(newPhone);

            System.out.println("Details updated successfully!");
        }
    }

    private void requestLoan() {
        Customer customer = findCustomer();
        if (customer != null) {
            System.out.print("Enter loan amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            customer.requestLoan(amount);
        }
    }

    private void viewCustomer() {
        Customer customer = findCustomer();
        if (customer != null) {
            customer.displayInfo();
        }
    }

    private Customer findCustomer() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Customer c : customers) {
            if (c.getCustomerId() == id) {
                return c;
            }
        }
        System.out.println("Customer not found!");
        return null;
    }
}

class p5 {
    public static void main(String[] args) {
        BankSystem bankSystem = new BankSystem();
        bankSystem.run();
    }
}
