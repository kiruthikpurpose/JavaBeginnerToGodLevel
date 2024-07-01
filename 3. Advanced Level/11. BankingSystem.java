import java.util.ArrayList;
import java.util.Scanner;

public class BankingSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create account");
            System.out.println("2. View accounts");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter customer name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter initial deposit:");
                    double initialDeposit = scanner.nextDouble();
                    bank.createAccount(new Customer(name), initialDeposit);
                    break;
                case 2:
                    bank.viewAccounts();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "'}";
    }
}

class Account {
    private static int accountCounter = 1;
    private int accountNumber;
    private Customer customer;
    private double balance;

    public Account(Customer customer, double initialDeposit) {
        this.accountNumber = accountCounter++;
        this.customer = customer;
        this.balance = initialDeposit;
    }

    @Override
    public String toString() {
        return "Account{accountNumber=" + accountNumber + ", customer=" + customer + ", balance=" + balance + "}";
    }
}

class Bank {
    private ArrayList<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public void createAccount(Customer customer, double initialDeposit) {
        accounts.add(new Account(customer, initialDeposit));
    }

    public void viewAccounts() {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
