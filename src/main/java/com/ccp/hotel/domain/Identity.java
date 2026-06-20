package com.ccp.hotel.domain;
public record Identity(String value) { public Identity { value = Validation.text(value, "identity"); } @Override public String toString(){return value;} }
