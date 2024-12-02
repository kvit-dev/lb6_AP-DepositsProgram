package menu;

import bank.Bank;
import deposit.Deposit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SortDeposits implements MenuCommand {
    private final List<Bank> banks;

    public SortDeposits(List<Bank> banks) {
        this.banks = banks;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nSort deposits");
        System.out.println("1-By amount");
        System.out.println("2-By interest Rate");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        List<Deposit> allDeposits = gatherAllDeposits();
        if (allDeposits.isEmpty()) {
            System.out.println("\nNo deposits to sort");
            return;
        }

        List<Deposit> sortedDeposits;
        switch (choice) {
            case "1":
                sortedDeposits = sortByAmount(allDeposits);
                break;
            case "2":
                sortedDeposits = sortByInterestRate(allDeposits);
                break;
            default:
                System.out.println("\nWrong choice!");
                return;
        }
        displaySortedDeposits(sortedDeposits);
    }

    private List<Deposit> gatherAllDeposits() {
        List<Deposit> allDeposits = new ArrayList<>();
        for (Bank bank : banks) {
            allDeposits.addAll(bank.getDeposits());
        }
        return allDeposits;
    }

    private List<Deposit> sortByAmount(List<Deposit> deposits) {
        List<Deposit> sortedDeposits = new ArrayList<>(deposits);
        sortedDeposits.sort(Comparator.comparingDouble(Deposit::getAmount));
        return sortedDeposits;
    }

    private List<Deposit> sortByInterestRate(List<Deposit> deposits) {
        List<Deposit> sortedDeposits = new ArrayList<>(deposits);
        sortedDeposits.sort(Comparator.comparingDouble(Deposit::getInterestRate));
        return sortedDeposits;
    }

    private void displaySortedDeposits(List<Deposit> deposits) {
        System.out.println("\nSorted deposits: ");
        for (Deposit deposit : deposits) {
            System.out.println(deposit);
        }
    }
}
