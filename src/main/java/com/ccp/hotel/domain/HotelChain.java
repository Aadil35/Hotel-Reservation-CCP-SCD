package com.ccp.hotel.domain;
import java.time.LocalDate; import java.util.*;
public class HotelChain {
    private final Name name; private final List<Hotel> hotels=new ArrayList<>(); private final List<ReserverPayer> reserverPayers=new ArrayList<>();
    public HotelChain(Name name){this.name=Validation.notNull(name,"chain name");}
    public Reservation makeReservation(Hotel hotel, RoomType roomType, LocalDate startDate, LocalDate endDate, ReserverPayer payer){ if(!canMakeReservation(hotel,roomType,startDate,endDate)) throw new IllegalStateException("reservation not allowed"); return hotel.createReservation(roomType,startDate,endDate,payer); }
    public void cancelReservation(Reservation reservation){ if(!canCancelReservation(reservation)) throw new IllegalStateException("reservation cannot be cancelled"); reservation.cancel(); }
    public void checkInGuest(Reservation reservation, Guest guest){ if(!canCheckInGuest(reservation)) throw new IllegalStateException("guest cannot check in"); reservation.getRoom().occupy(guest); reservation.markCheckedIn(); }
    public void checkOutGuest(Room room){ if(!canCheckOutGuest(room)) throw new IllegalStateException("room is not occupied"); room.vacate(); }
    public ReserverPayer createReserverPayer(CreditCard card, Identity id){ ReserverPayer payer=ReserverPayer.create(card,id); reserverPayers.add(payer); return payer; }
    private boolean canMakeReservation(Hotel hotel, RoomType roomType, LocalDate start, LocalDate end){ return hotel != null && hotel.available(roomType,start,end).isPresent(); }
    private boolean canCancelReservation(Reservation reservation){ return reservation != null && reservation.isActive() && !reservation.isCheckedIn(); }
    private boolean canCheckInGuest(Reservation reservation){ return reservation != null && reservation.isActive() && !reservation.isCheckedIn() && !reservation.getRoom().isOccupied(); }
    private boolean canCheckOutGuest(Room room){ return room != null && room.isOccupied(); }
    public void addHotel(Hotel hotel){hotels.add(Validation.notNull(hotel,"hotel"));}
    public Name getName(){return name;} public List<Hotel> getHotels(){return List.copyOf(hotels);} public List<ReserverPayer> getReserverPayers(){return List.copyOf(reserverPayers);}
}
