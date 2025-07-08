package org.example;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;

public class Account {
    private String accountNummer;
    private BigDecimal amount;
    private Client client;

    public Account(Client client) {
        Random random = new Random();
        this.accountNummer = "DE" + random.nextInt(10000000);
        this.amount = new  BigDecimal(0);
        this.client = client;
    }

    public String getAccountNummer() {
        return accountNummer;
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

    @Override
    public String toString() {
        return "Account{" +
                "accountNummer='" + accountNummer + '\'' +
                ", amount=" + amount +
                ", client=" + client +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Account account = (Account) object;
        return Objects.equals(accountNummer, account.accountNummer) && Objects.equals(amount, account.amount) && Objects.equals(client, account.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNummer, amount, client);
    }
}
