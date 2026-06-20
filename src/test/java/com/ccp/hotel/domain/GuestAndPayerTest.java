package com.ccp.hotel.domain;
import org.junit.jupiter.api.Test; import static org.junit.jupiter.api.Assertions.*;
class GuestAndPayerTest {
    @Test void createsGuest(){ Guest guest = Guest.create(new Name("Ali"), new Address("Karachi")); assertEquals("Ali", guest.getName().value()); }
    @Test void rejectsGuestWithoutAddress(){ assertThrows(IllegalArgumentException.class, () -> Guest.create(new Name("Ali"), null)); }
    @Test void createsReserverPayer(){ ReserverPayer payer = ReserverPayer.create(new CreditCard("4111111111111111"), new Identity("CNIC1")); assertEquals("CNIC1", payer.getId().value()); }
}
