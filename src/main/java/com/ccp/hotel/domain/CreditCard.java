package com.ccp.hotel.domain;
public record CreditCard(String number) { public CreditCard { number = Validation.text(number, "credit card"); if (!number.matches("\\d{12,19}")) throw new IllegalArgumentException("credit card must contain 12 to 19 digits"); } }
