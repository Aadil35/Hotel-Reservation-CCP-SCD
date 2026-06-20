package com.ccp.hotel.domain;
import org.junit.jupiter.api.Test; import static org.junit.jupiter.api.Assertions.*;
class RoomTest {
    private final RoomType type = new RoomType(RoomKind.SINGLE, Money.of(5000));
    @Test void createsRoom(){ Room room = new Room(1, type); assertFalse(room.isOccupied()); }
    @Test void rejectsZeroRoomNumber(){ assertThrows(IllegalArgumentException.class, () -> new Room(0, type)); }
    @Test void occupiesAndVacatesRoom(){ Room room = new Room(1, type); Guest guest = Guest.create(new Name("A"), new Address("B")); room.occupy(guest); assertTrue(room.isOccupied()); room.vacate(); assertFalse(room.isOccupied()); }
    @Test void rejectsDoubleOccupancy(){ Room room = new Room(1, type); Guest guest = Guest.create(new Name("A"), new Address("B")); room.occupy(guest); assertThrows(IllegalStateException.class, () -> room.occupy(guest)); }
}
