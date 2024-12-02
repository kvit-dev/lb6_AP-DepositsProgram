package menu;

import bank.Bank;
import deposit.*;
import utils.Utils;

import java.util.List;
import java.util.Scanner;

public class AddDeposit implements MenuCommand {
    private final List<Bank> banks;

    public AddDeposit(List<Bank> banks) {
        this.banks = banks;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Bank selectedBank = Utils.selectBank(scanner, banks);

        Deposit newDeposit = null;
        while (newDeposit == null) {
            System.out.println("\nSelect the type of deposit");
            if (selectedBank.getName().equals("Clover Bank")) {
                System.out.println("1-Flexible Deposit");
                System.out.println("2-Standard Deposit");
            } else if (selectedBank.getName().equals("Super bank")) {
                System.out.println("1-Junior Deposit");
                System.out.println("2-Special Deposit");
            }
            System.out.print("Enter your choice: ");

            String typeChoice = scanner.nextLine();
            if (selectedBank.getName().equals("Clover Bank")) {
                if (typeChoice.equals("1")) {
                    newDeposit = new FlexibleDeposit(0, 0, 0, false, false);
                } else if (typeChoice.equals("2")) {
                    newDeposit = new StandartDeposit(0, 0, 0, false, false);
                } else {
                    System.out.println("Wrong choice! Try again");
                }
            } else if (selectedBank.getName().equals("Super bank")) {
                if (typeChoice.equals("1")) {
                    newDeposit = new JuniorDeposit(0, 0, 0, false, false);
                } else if (typeChoice.equals("2")) {
                    newDeposit = new SpecialDeposit(0, 0, 0, false, false);
                } else {
                    System.out.println("Wrong choice! Try again");
                }
            }
        }

        System.out.print("\nEnter the deposit amount: ");
        double amount = Utils.readPositiveDouble(scanner);

        System.out.print("Enter the interest rate: ");
        double interestRate = Utils.readPositiveDouble(scanner);

        System.out.print("Enter the term (in months): ");
        int term = Utils.readPositiveInt(scanner);

        System.out.print("Is early withdrawal allowed? (yes/no): ");
        boolean isWithdrawable = Utils.getBooleanInput(scanner);

        System.out.print("Is replenishment allowed? (yes/no): ");
        boolean isReplenishable = Utils.getBooleanInput(scanner);

        newDeposit = createDeposit(newDeposit.getClass(), amount, interestRate, term, isWithdrawable, isReplenishable);

        selectedBank.addDeposit(newDeposit);
        System.out.println("\nDeposit is successfully added to " + selectedBank.getName());

        System.out.println("\nCurrent list of deposits in " + selectedBank.getName() + ":");
        for (Deposit deposit : selectedBank.getDeposits()) {
            String withdrawal = deposit.isWithdrawable() ? "yes" : "no";
            String replenishment = deposit.isReplenishable() ? "yes" : "no";
            System.out.println("Type: " + deposit.getClass().getSimpleName() +
                    ", amount: " + deposit.getAmount() +
                    ", interest rate: " + deposit.getInterestRate() +
                    ", term: " + deposit.getTerm() +
                    ", withdrawal: " + withdrawal +
                    ", replenishment: " + replenishment);
        }
    }

    private Deposit createDeposit(Class<?> depositClass, double amount, double rate, int term, boolean withdrawable, boolean replenishable) {
        if (depositClass == FlexibleDeposit.class) {
            return new FlexibleDeposit(amount, rate, term, withdrawable, replenishable);
        } else if (depositClass == StandartDeposit.class) {
            return new StandartDeposit(amount, rate, term, withdrawable, replenishable);
        } else if (depositClass == JuniorDeposit.class) {
            return new JuniorDeposit(amount, rate, term, withdrawable, replenishable);
        } else if (depositClass == SpecialDeposit.class) {
            return new SpecialDeposit(amount, rate, term, withdrawable, replenishable);
        }
        return null;
    }
}
