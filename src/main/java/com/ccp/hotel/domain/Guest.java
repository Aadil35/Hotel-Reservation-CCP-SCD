package com.ccp.hotel.domain;
public class Guest {
    private final Name name;
    private final Address addressDetails;
    private Guest(Name name, Address addressDetails) { this.name=Validation.notNull(name,"name"); this.addressDetails=Validation.notNull(addressDetails,"address"); }
    public static Guest create(Name name, Address addressDetails) { return new Guest(name, addressDetails); }
    public Name getName(){return name;} public Address getAddressDetails(){return addressDetails;}
    @Override public String toString(){return name.toString();}
}
