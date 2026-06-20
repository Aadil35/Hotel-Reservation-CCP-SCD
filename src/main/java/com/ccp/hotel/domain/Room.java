package com.ccp.hotel.domain;
public class Room {
    private final Integer number; private final RoomType roomType; private Guest occupant;
    public Room(Integer number, RoomType roomType){this.number=Validation.positive(Validation.notNull(number,"room number"),"room number"); this.roomType=Validation.notNull(roomType,"room type");}
    public Guest createGuest(Name name, Address address){ Guest guest = Guest.create(name,address); this.occupant = guest; return guest; }
    public void occupy(Guest guest){ if(isOccupied()) throw new IllegalStateException("room is already occupied"); occupant=Validation.notNull(guest,"guest"); }
    public void vacate(){ occupant=null; }
    public boolean isOccupied(){ return occupant != null; }
    public Integer getNumber(){return number;} public RoomType getRoomType(){return roomType;} public Guest getOccupant(){return occupant;}
    @Override public String toString(){return "Room " + number + " - " + roomType.getKind() + (isOccupied()?" occupied":" available");}
}
