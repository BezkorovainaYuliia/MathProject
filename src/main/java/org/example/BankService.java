package org.example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BankService {
    private Map<String, Account> accounts = new HashMap<>();

    public String createAccount(Client client){
        Account newAccount = new Account(client);
        newAccount.plusAmount(new BigDecimal(1000));
        accounts.put(newAccount.getAccountNummer(),  newAccount);
        System.out.println("New account has been created with 1000 Euro");
        System.out.println(client);
        System.out.println(newAccount.getAccountNummer());
        return newAccount.getAccountNummer();
    }

    public void transferMoney(String fromAccount, String toAccount, BigDecimal amount){
        Account fromAccountClient = accounts.get(fromAccount);
        Account toAccountClient = accounts.get(toAccount);
        BigDecimal currentFromAccoun = fromAccountClient.minusAmount(amount);
        BigDecimal currentToAccoun = toAccountClient.plusAmount(amount);
        System.out.println("Current amount " + fromAccount + " " +  currentFromAccoun);
        System.out.println("Current amount " + fromAccount + " " + currentToAccoun);
    }

    public void printAccounts(){
        for (Map.Entry<String, Account> entry : accounts.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}
