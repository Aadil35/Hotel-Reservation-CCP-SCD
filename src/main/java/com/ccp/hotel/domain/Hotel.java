package com.ccp.hotel.domain;
import java.time.LocalDate; import java.util.*;
public class Hotel {
    private final Name name; private final List<RoomType> roomTypes=new ArrayList<>(); private final List<Room> rooms=new ArrayList<>(); private final List<Reservation> reservations=new ArrayList<>();
    public Hotel(Name name){this.name=Validation.notNull(name,"hotel name");}
    public Reservation createReservation(RoomType roomType, LocalDate startDate, LocalDate endDate, ReserverPayer payer){
        Validation.notNull(roomType,"room type"); Room room=available(roomType,startDate,endDate).orElseThrow(()->new IllegalStateException("no available room for " + roomType.getKind()));
        Reservation reservation=Reservation.create(startDate,endDate,reservations.size()+1,room,payer); reservations.add(reservation); return reservation;
    }
    public Optional<Room> available(RoomType roomType, LocalDate startDate, LocalDate endDate){
        Validation.notNull(startDate,"start date"); Validation.notNull(endDate,"end date"); if(!endDate.isAfter(startDate)) throw new IllegalArgumentException("end date must be after start date");
        return rooms.stream().filter(r->r.getRoomType().equals(roomType)).filter(r->reservations.stream().filter(Reservation::isActive).filter(x->x.getRoom().equals(r)).noneMatch(x->x.overlaps(startDate,endDate))).findFirst();
    }
    public void addRoomType(RoomType type){roomTypes.add(Validation.notNull(type,"room type"));}
    public void addRoom(Room room){rooms.add(Validation.notNull(room,"room")); if(!roomTypes.contains(room.getRoomType())) roomTypes.add(room.getRoomType());}
    public Name getName(){return name;} public List<Room> getRooms(){return List.copyOf(rooms);} public List<RoomType> getRoomTypes(){return List.copyOf(roomTypes);} public List<Reservation> getReservations(){return List.copyOf(reservations);}
}
