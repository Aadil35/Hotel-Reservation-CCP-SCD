package com.ccp.hotel.domain;
import org.junit.jupiter.api.Test; import java.time.LocalDate; import static org.junit.jupiter.api.Assertions.*;
class HotelChainIntegrationTest {
    @Test void completeBookingCheckInAndCheckOutFlow(){
        HotelChain chain = new HotelChain(new Name("Chain")); Hotel hotel = new Hotel(new Name("Hotel")); chain.addHotel(hotel);
        RoomType type = new RoomType(RoomKind.DOUBLE, Money.of(9000)); hotel.addRoom(new Room(10, type));
        ReserverPayer payer = chain.createReserverPayer(new CreditCard("4111111111111111"), new Identity("ID1"));
        Reservation reservation = chain.makeReservation(hotel, type, LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), payer);
        chain.checkInGuest(reservation, Guest.create(new Name("Sara"), new Address("Hyderabad")));
        assertTrue(reservation.getRoom().isOccupied());
        chain.checkOutGuest(reservation.getRoom());
        assertFalse(reservation.getRoom().isOccupied());
    }
    @Test void preventsOverbookingSameRoom(){
        HotelChain chain = new HotelChain(new Name("Chain")); Hotel hotel = new Hotel(new Name("Hotel")); chain.addHotel(hotel);
        RoomType type = new RoomType(RoomKind.SUITE, Money.of(20000)); hotel.addRoom(new Room(1, type));
        ReserverPayer payer = chain.createReserverPayer(new CreditCard("4111111111111111"), new Identity("ID1"));
        chain.makeReservation(hotel, type, LocalDate.of(2026,6,20), LocalDate.of(2026,6,25), payer);
        assertThrows(IllegalStateException.class, () -> chain.makeReservation(hotel, type, LocalDate.of(2026,6,22), LocalDate.of(2026,6,24), payer));
    }
    @Test void allowsSecondReservationAfterCancelledBooking(){
        HotelChain chain = new HotelChain(new Name("Chain")); Hotel hotel = new Hotel(new Name("Hotel")); chain.addHotel(hotel);
        RoomType type = new RoomType(RoomKind.SINGLE, Money.of(6000)); hotel.addRoom(new Room(1, type));
        ReserverPayer payer = chain.createReserverPayer(new CreditCard("4111111111111111"), new Identity("ID1"));
        Reservation first = chain.makeReservation(hotel, type, LocalDate.of(2026,6,20), LocalDate.of(2026,6,25), payer);
        chain.cancelReservation(first);
        Reservation second = chain.makeReservation(hotel, type, LocalDate.of(2026,6,22), LocalDate.of(2026,6,24), payer);
        assertNotNull(second);
    }
}
