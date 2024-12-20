package menu;

import bank.Bank;
import deposit.Deposit;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchDeposits implements MenuCommand {
    private List<Bank> banks;

    public SearchDeposits(List<Bank> banks) {
        this.banks = banks;
    }

    @Override
    public void execute() {
        List<Deposit> result = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nSearch deposits");
        System.out.println("1-By replenishment (allowed)");
        System.out.println("2-By term in months");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                result = searchByReplenishment();
                break;
            case "2":
                result = searchByTerm(scanner);
                break;
            default:
                System.out.println("\nWrong choice!");
                return;
        }
        displayResult(result);
    }

    private List<Deposit> searchByReplenishment() {
        List<Deposit> result = new ArrayList<>();
        for (Bank bank : banks) {
            for (Deposit deposit : bank.getDeposits()) {
                if (deposit.isReplenishable()) {
                    result.add(deposit);
                }
            }
        }
        return result;
    }

    private List<Deposit> searchByTerm(Scanner scanner) {
        System.out.print("\nEnter the term in months to search for: ");
        int term = Utils.readPositiveInt(scanner);
        List<Deposit> result = new ArrayList<>();
        for (Bank bank : banks) {
            for (Deposit deposit : bank.getDeposits()) {
                if (deposit.getTerm() == term) {
                    result.add(deposit);
                }
            }
        }
        return result;
    }

    private void displayResult(List<Deposit> result) {
        if (result.isEmpty()) {
            System.out.println("\nNo deposits in here");
        } else {
            System.out.println("\nFound deposits: ");
            for (Deposit deposit : result) {
                System.out.println(deposit);
            }
        }
    }
}
