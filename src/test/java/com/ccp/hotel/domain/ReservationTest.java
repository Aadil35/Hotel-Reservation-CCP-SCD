package com.ccp.hotel.domain;
import org.junit.jupiter.api.Test; import java.time.LocalDate; import static org.junit.jupiter.api.Assertions.*;
class ReservationTest {
    private Room room(){ return new Room(101, new RoomType(RoomKind.DELUXE, Money.of(10000))); }
    private ReserverPayer payer(){ return ReserverPayer.create(new CreditCard("4111111111111111"), new Identity("ID1")); }
    @Test void createsReservation(){ Reservation r = Reservation.create(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), 1, room(), payer()); assertTrue(r.isActive()); }
    @Test void rejectsInvalidDateRange(){ assertThrows(IllegalArgumentException.class, () -> Reservation.create(LocalDate.now().plusDays(2), LocalDate.now().plusDays(2), 1, room(), payer())); }
    @Test void detectsOverlappingDates(){ Reservation r = Reservation.create(LocalDate.of(2026,6,20), LocalDate.of(2026,6,25), 1, room(), payer()); assertTrue(r.overlaps(LocalDate.of(2026,6,24), LocalDate.of(2026,6,26))); assertFalse(r.overlaps(LocalDate.of(2026,6,25), LocalDate.of(2026,6,28))); }
    @Test void cancelsBeforeCheckIn(){ Reservation r = Reservation.create(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), 1, room(), payer()); r.cancel(); assertTrue(r.isCancelled()); }
}
