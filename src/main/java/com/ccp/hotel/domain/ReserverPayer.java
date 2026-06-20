package com.ccp.hotel.domain;
public class ReserverPayer {
    private final CreditCard creditCardDetails;
    private final Identity id;
    private ReserverPayer(CreditCard creditCardDetails, Identity id){this.creditCardDetails=Validation.notNull(creditCardDetails,"credit card"); this.id=Validation.notNull(id,"identity");}
    public static ReserverPayer create(CreditCard creditCardDetails, Identity id){return new ReserverPayer(creditCardDetails,id);} 
    public CreditCard getCreditCardDetails(){return creditCardDetails;} public Identity getId(){return id;}
}
