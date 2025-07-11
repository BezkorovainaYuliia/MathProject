package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private Map<String, Account> accounts = new HashMap<>();

    public String createAccount(List<Client> clients){
        Account newAccount = new Account(clients);
        newAccount.plusAmount(new BigDecimal(1000));
        accounts.put(newAccount.getAccountNummer(),  newAccount);
        System.out.println("New account has been created with 1000 Euro");
        System.out.println(clients);
        System.out.println(newAccount.getAccountNummer());
        return newAccount.getAccountNummer();
    }

    public String createAccount(Client newClient, BigDecimal newAmount){
        Account newAccount = new Account(newClient, newAmount);
        accounts.put(newAccount.getAccountNummer(),  newAccount);
        System.out.println("New account has been created with 1000 Euro");
        System.out.println(newClient);
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

    public void applyInterest(BigDecimal interestRatePercent) {
        for (Account account : accounts.values()) {
            BigDecimal balance = account.getAmount();

            // Zinsen berechnen: balance * (zinssatz / 100)
            BigDecimal interest = balance.multiply(interestRatePercent)
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN); // auf 2 Nachkommastellen

            // Gutschrift
            BigDecimal newBalance = balance.add(interest);
            account.setAmount(newBalance);

            System.out.println("Konto " + account.getAccountNummer() +
                    ": Zinsen " + interest + " €, neuer Kontostand: " + newBalance + " €");
        }
    }

    public List<String> split(String accountNumber){
        Account original = accounts.get(accountNumber);
        if (original == null) {
            throw new IllegalArgumentException("Konto nicht gefunden");
        }

        List<Client> owners = original.getClients();
        int count = owners.size();
        if (count < 2) {
            throw new IllegalStateException("Kein Gemeinschaftskonto");
        }

        BigDecimal total = original.getAmount();

        // Aufteilen in Cent → Ganzzahlige Rechnung vermeiden Rundungsfehler
        int totalCents = total.multiply(BigDecimal.valueOf(100)).intValueExact();
        int baseAmount = totalCents / count;
        int remainder = totalCents % count;

        List<String> newAccounts = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int cents = baseAmount + (i < remainder ? 1 : 0);  // Die ersten "remainder" bekommen 1 Cent extra
            BigDecimal amount = BigDecimal.valueOf(cents).divide(BigDecimal.valueOf(100));

            String newAccount = createAccount(owners.get(i), amount);
            newAccounts.add(newAccount);
        }

        // Originalkonto leeren
        original.setAmount(BigDecimal.ZERO);

        return newAccounts;
    }
    public void printAccounts(){
        for (Map.Entry<String, Account> entry : accounts.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}
