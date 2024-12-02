package menu;

import bank.Bank;
import deposit.Deposit;
import utils.Utils;

import java.util.List;
import java.util.Scanner;

public class ChangeDepositInfo implements MenuCommand {
    private List<Bank> banks;

    public ChangeDepositInfo(List<Bank> banks) {
        this.banks = banks;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Bank selectedBank = Utils.selectBank(scanner, banks);
        if (selectedBank.getDeposits().isEmpty()) {
            System.out.println("\nThe bank has no deposits to modify");
            return;
        }
        Deposit selectedDeposit = Utils.selectDeposit(scanner, selectedBank);
        System.out.println("\nChanging deposit info: " + selectedDeposit.getClass().getSimpleName());

        System.out.print("Enter new interest rate (current: " + selectedDeposit.getInterestRate() + "): ");
        double newInterestRate = Utils.readPositiveDouble(scanner);

        System.out.print("Enter new term in months (current: " + selectedDeposit.getTerm() + "): ");
        int newTerm = Utils.readPositiveInt(scanner);

        System.out.print("Allow early replenish? (yes/no, current: " + (selectedDeposit.isReplenishable() ? "yes" : "no") + "): ");
        boolean newReplenishable = Utils.getBooleanInput(scanner);

        if (newInterestRate == selectedDeposit.getInterestRate() &&
                newTerm == selectedDeposit.getTerm() &&
                newReplenishable == selectedDeposit.isReplenishable()) {
            System.out.println("\nThe new data is equal to the current one, no updates have been made");
            return;
        }
        updateDeposit(selectedDeposit, newInterestRate, newTerm, newReplenishable);
        System.out.println("\nDeposit is updated");
    }

    private void updateDeposit(Deposit deposit, double newInterestRate, int newTerm, boolean newReplenishable) {
        deposit.setInterestRate(newInterestRate);
        deposit.setTerm(newTerm);
        deposit.setReplenishable(newReplenishable);
    }
}
