package menu;

import bank.Bank;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainMenu {
    private Map<String, Option> menuItems;

    public MainMenu(List<Bank> banks) {
        menuItems = new LinkedHashMap<>();
        menuItems.put("0", new Option("Exit program", new ExitOption()));
        menuItems.put("1", new Option("Add deposit", new AddDeposit(banks)));
        menuItems.put("2", new Option("Remove deposit", new RemoveDeposit(banks)));
        menuItems.put("3", new Option("Search deposits", new SearchDeposits(banks)));
        menuItems.put("4", new Option("Sort deposits", new SortDeposits(banks)));
        menuItems.put("5", new Option("Change deposit info", new ChangeDepositInfo(banks)));
    }

    public void displayMenu() {
        System.out.println("\n\tMenu");
        menuItems.forEach((key, option) ->
                System.out.println(key + "-" + option.getName()));
    }

    public void executeCommand(String command) {
        menuItems.getOrDefault(command, new Option("Invalid", () -> {
            System.out.println("Incorrect command was used");
            System.out.println("Available commands: " + getAvailableCommands());
        })).getCommand().execute();
    }

    private Set<String> getAvailableCommands() {
        return menuItems.keySet();
    }
}
