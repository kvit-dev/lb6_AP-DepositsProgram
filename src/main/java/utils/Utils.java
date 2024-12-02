package utils;

import bank.Bank;
import deposit.Deposit;

import java.util.List;
import java.util.Scanner;

public class Utils {
    public static Bank selectBank(Scanner scanner, List<Bank> banks) {
        while (true) {
            System.out.println("\nSelect a bank: ");
            System.out.println("1-Clover Bank");
            System.out.println("2-Super Bank");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) return banks.get(0);
            if (choice.equals("2")) return banks.get(1);

            System.out.println("Wrong choice! Try again");
        }
    }

    public static Deposit selectDeposit(Scanner scanner, Bank bank) {
        while (true) {
            System.out.println("\nAvailable deposits in " + bank.getName() + ":");
            for (int i = 0; i < bank.getDeposits().size(); i++) {
                Deposit deposit = bank.getDeposits().get(i);
                String withdrawal = deposit.isWithdrawable() ? "yes" : "no";
                String replenishment = deposit.isReplenishable() ? "yes" : "no";
                System.out.println((i + 1) + "-Type: " + deposit.getClass().getSimpleName() +
                        ", amount: " + deposit.getAmount() +
                        ", interest rate: " + deposit.getInterestRate() +
                        ", term: " + deposit.getTerm() +
                        ", withdrawal: " + withdrawal +
                        ", replenishment: " + replenishment);
            }
            System.out.print("Enter the number of the deposit: ");
            String input = scanner.nextLine();

            if (input.matches("\\d+")) {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < bank.getDeposits().size()) {
                    return bank.getDeposits().get(index);
                }
            }
            System.out.println("Wrong choice! Try again");
        }
    }

    public static int readPositiveInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            if (input.matches("\\d+")) {
                int value = Integer.parseInt(input);
                if (value > 0) return value;
            }
            System.out.print("Invalid input! Enter a positive integer: ");
        }
    }

    public static double readPositiveDouble(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            if (input.matches("\\d+(\\.\\d+)?")) {
                double value = Double.parseDouble(input);
                if (value > 0) return value;
            }
            System.out.print("Invalid input! Enter a positive number: ");
        }
    }

    public static boolean getBooleanInput(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes")) {
                return true;
            } else if (input.equals("no")) {
                return false;
            } else {
                System.out.print("Invalid input. Try again: ");
            }
        }
    }
}
