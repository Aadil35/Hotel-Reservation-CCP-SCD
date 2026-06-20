package com.ccp.hotel.domain;
import java.math.BigDecimal;
public record Money(BigDecimal amount) { public Money { Validation.notNull(amount,"amount"); if(amount.compareTo(BigDecimal.ZERO)<0) throw new IllegalArgumentException("amount cannot be negative"); } public static Money of(double amount){return new Money(BigDecimal.valueOf(amount));} @Override public String toString(){return "PKR " + amount;} }
