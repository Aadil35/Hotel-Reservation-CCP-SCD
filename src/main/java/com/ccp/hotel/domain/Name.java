package com.ccp.hotel.domain;
public record Name(String value) { public Name { value = Validation.text(value, "name"); } @Override public String toString(){return value;} }
