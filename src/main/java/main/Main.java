package main;

import bank.Bank;
import bank.CloverBank;
import bank.SuperBank;
import menu.MainMenu;
import utils.FileRead;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CloverBank cloverBank = new CloverBank();
        SuperBank superBank = new SuperBank();

        List<Bank> banks = new ArrayList<>();
        banks.add(cloverBank);
        banks.add(superBank);

        String fileName = "src/deposits.txt";
        FileRead fileReader = new FileRead();
        fileReader.readFromFile(fileName, banks);

        MainMenu menu = new MainMenu(banks);
        Scanner sc = new Scanner(System.in);

        while (true) {
            menu.displayMenu();
            System.out.println("\nWrite your option: ");
            String command = sc.nextLine();
            menu.executeCommand(command);
        }
    }
}
