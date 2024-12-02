package menu;

import bank.Bank;
import deposit.Deposit;
import utils.Utils;

import java.util.List;
import java.util.Scanner;

public class RemoveDeposit implements MenuCommand {
    private List<Bank> banks;

    public RemoveDeposit(List<Bank> banks) {
        this.banks = banks;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Bank selectedBank = Utils.selectBank(scanner, banks);
        if (selectedBank.getDeposits().isEmpty()) {
            System.out.println("\nThe bank has no deposits to remove");
            return;
        }

        Deposit selectedDeposit = Utils.selectDeposit(scanner, selectedBank);

        System.out.print("\nAre you sure you want to remove this deposit? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            selectedBank.removeDeposit(selectedDeposit);
            System.out.println("\nDeposit is removed from " + selectedBank.getName());
        } else {
            System.out.println("\nDeposit removal is canceled!");
        }
    }
}
