package com.ccp.hotel.domain;
public record HowMany(Integer number) { public HowMany { Validation.notNull(number,"number"); if(number < 0) throw new IllegalArgumentException("number cannot be negative"); } }
