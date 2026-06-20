package com.ccp.hotel.domain;
import java.time.LocalDate;
public class Reservation {
    private final LocalDate reservationDate; private final LocalDate startDate; private final LocalDate endDate; private final Integer number;
    private final Room room; private final RoomType roomType; private final ReserverPayer reserverPayer;
    private boolean cancelled; private boolean checkedIn;
    private Reservation(LocalDate reservationDate, LocalDate startDate, LocalDate endDate, Integer number, Room room, RoomType roomType, ReserverPayer reserverPayer){
        this.reservationDate=Validation.notNull(reservationDate,"reservation date"); this.startDate=Validation.notNull(startDate,"start date"); this.endDate=Validation.notNull(endDate,"end date");
        if(!endDate.isAfter(startDate)) throw new IllegalArgumentException("end date must be after start date");
        this.number=Validation.positive(Validation.notNull(number,"reservation number"),"reservation number"); this.room=Validation.notNull(room,"room"); this.roomType=Validation.notNull(roomType,"room type"); this.reserverPayer=Validation.notNull(reserverPayer,"reserver payer");
    }
    public static Reservation create(LocalDate startDate, LocalDate endDate, Integer number, Room room, ReserverPayer reserverPayer){ return new Reservation(LocalDate.now(), startDate, endDate, number, room, room.getRoomType(), reserverPayer); }
    public boolean overlaps(LocalDate otherStart, LocalDate otherEnd){ return startDate.isBefore(otherEnd) && otherStart.isBefore(endDate); }
    public void cancel(){ if(checkedIn) throw new IllegalStateException("checked-in reservation cannot be cancelled"); cancelled=true; }
    public void markCheckedIn(){ if(cancelled) throw new IllegalStateException("cancelled reservation cannot be checked in"); checkedIn=true; }
    public boolean isActive(){return !cancelled;} public boolean isCancelled(){return cancelled;} public boolean isCheckedIn(){return checkedIn;}
    public LocalDate getReservationDate(){return reservationDate;} public LocalDate getStartDate(){return startDate;} public LocalDate getEndDate(){return endDate;} public Integer getNumber(){return number;} public Room getRoom(){return room;} public RoomType getRoomType(){return roomType;} public ReserverPayer getReserverPayer(){return reserverPayer;}
    @Override public String toString(){return "Reservation " + number + " for room " + room.getNumber() + " from " + startDate + " to " + endDate;}
}
