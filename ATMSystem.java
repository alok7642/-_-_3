import java.util.Scanner;
import java.util.ArrayList;

class User {
    private String userId;
    private String userPin;
    private double balance;
    private ArrayList<String> transactionHistory;

    public User(String userId, String userPin, double balance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public boolean validatePin(String enteredPin) {
        return userPin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: +" + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdraw: -" + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void transfer(User recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add("Transfer: -" + amount + " to " + recipient.getUserId());
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History for " + userId);
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        User user = new User("123456", "1234", 1000.0);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM System");
        System.out.print("Enter User ID: ");
        String enteredUserId = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();

        if (user.getUserId().equals(enteredUserId) && user.validatePin(enteredPin)) {
            System.out.println("Authentication successful!");
            showMenu(user);
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
    }

    public static void showMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: $" + user.getBalance());
                    break;
                case 2:
                    System.out.print("Enter the amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    user.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;
                case 3:
                    System.out.print("Enter the amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    user.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient's User ID: ");
                    String recipientUserId = scanner.next();
                    System.out.print("Enter the amount to transfer: $");
                    double transferAmount = scanner.nextDouble();
                   
                    break;
                case 5:
                    user.printTransactionHistory();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
