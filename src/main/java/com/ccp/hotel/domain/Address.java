package com.ccp.hotel.domain;
public record Address(String value) { public Address { value = Validation.text(value, "address"); } @Override public String toString(){return value;} }
