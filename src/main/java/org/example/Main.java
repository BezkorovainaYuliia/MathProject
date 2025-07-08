package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Client yuliia = new Client("Yuliia", "Bezkorovaina", 12345678);
        Client tom = new Client("Tom", "Berlin", 987654321);
        Client hermann = new Client("Hermann", "Hermann", 4567678);
        Client vika = new Client("Vika", "Vika", 789789);



        BankService bank = new BankService();
        String yuliiasNummer = bank.createAccount(yuliia);
        String tomsNummer = bank.createAccount(tom);
        String hermannsNummer = bank.createAccount(hermann);
        String vikasNummer = bank.createAccount(vika);

        System.out.println("---------------");
        bank.printAccounts();
        System.out.println("---------------");

        System.out.println("Yullia transfer 10 Euro to Tom");
        bank.transferMoney(yuliiasNummer, tomsNummer, new BigDecimal(10));

        System.out.println("Herman transfer 100 Euro to Yuliia");
        bank.transferMoney(hermannsNummer, yuliiasNummer, new BigDecimal(100));

        System.out.println("Herman transfer 100 Euro to Vika");
        bank.transferMoney(hermannsNummer, vikasNummer, new BigDecimal(100));

        System.out.println("---------------");
        bank.printAccounts();
        System.out.println("---------------");
    }
}