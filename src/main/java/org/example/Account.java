package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Account {
    private String accountNummer;
    private BigDecimal amount;
    private List<Client> clients;

    public Account(List<Client> client) {
        Random random = new Random();
        this.accountNummer = "DE" + random.nextInt(10000000);
        this.amount = new  BigDecimal(0);
        this.clients = client;
    }

    public Account(Client client, BigDecimal amount) {
        Random random = new Random();
        this.accountNummer = "DE" + random.nextInt(10000000);
        this.clients = new ArrayList<>();
        this.clients.add(client);
        this.amount = amount;
    }

    public String getAccountNummer() {
        return accountNummer;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal minusAmount(BigDecimal minusAmount) {
        if (minusAmount == null){ throw new IllegalArgumentException("minusAmount is null"); }
        if (amount.compareTo(minusAmount) < 0){ throw new IllegalArgumentException("it's not enough money"); }
        amount = amount.subtract(minusAmount);
        return amount;
    }

    public BigDecimal plusAmount(BigDecimal plusAmount) {
        if (plusAmount == null){ throw new IllegalArgumentException("minusAmount is null"); }
        amount = amount.add(plusAmount);
        return amount;
    }

    public List<Client> getClients() {
        return clients;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNummer='" + accountNummer + '\'' +
                ", amount=" + amount +
                ", client=" + clients +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Account account = (Account) object;
        return Objects.equals(accountNummer, account.accountNummer) && Objects.equals(amount, account.amount) && Objects.equals(clients, account.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNummer, amount, clients);
    }
}
