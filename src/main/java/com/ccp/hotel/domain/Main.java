    package com.ccp.hotel.domain;

    import java.time.LocalDate;

    public class Main {
    public static void main(String[] args) {
    HotelChain chain = new HotelChain(new Name("City Hotel Chain"));
    Hotel hotel = new Hotel(new Name("Iqra Grand Hotel"));
    chain.addHotel(hotel);

        RoomType deluxe = new RoomType(RoomKind.DELUXE, Money.of(15000));
        RoomType single = new RoomType(RoomKind.SINGLE, Money.of(8000));
        hotel.addRoom(new Room(101, deluxe));
        hotel.addRoom(new Room(102, deluxe));
        hotel.addRoom(new Room(201, single));

        ReserverPayer payer = chain.createReserverPayer(new CreditCard("4111111111111111"), new Identity("CNIC-42101"));
        Guest guest = Guest.create(new Name("Ahmed Ali"), new Address("Karachi, Pakistan"));

        System.out.println("Hotel created: " + hotel.getName());
        System.out.println("Rooms created: " + hotel.getRooms());
        System.out.println("Availability before booking: " + hotel.available(deluxe, LocalDate.now().plusDays(1),LocalDate.now().plusDays(3)).isPresent());

        Reservation reservation = chain.makeReservation(hotel, deluxe, LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), payer);
        System.out.println("Booking created: " + reservation);

        chain.checkInGuest(reservation, guest);
        System.out.println("Guest checked in: " + reservation.getRoom());

        chain.checkOutGuest(reservation.getRoom());
        System.out.println("Guest checked out: " + reservation.getRoom());

        Reservation second = chain.makeReservation(hotel, single, LocalDate.now().plusDays(5), LocalDate.now().plusDays(6), payer);
        chain.cancelReservation(second);
        System.out.println("Second booking cancelled: " + second.isCancelled());
    }
}
