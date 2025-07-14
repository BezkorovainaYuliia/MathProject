package org.example;

import java.math.BigDecimal;

public class Ubung {
    public static void main(String[] args) {
        BigDecimal amount = new BigDecimal("100");
        amount = amount.divide(new BigDecimal("100"));
        System.out.println(amount);
        System.out.println("Branche1");
    }
}
