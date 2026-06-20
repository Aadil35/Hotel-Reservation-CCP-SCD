# Hotel Reservation CCP

Java implementation of the Hotel Room Reservation UML diagram.

## Build
```bash
mvn clean compile
```

## Run
```bash
mvn exec:java -Dexec.mainClass="com.ccp.hotel.domain.Main"
```
Or run `Main.java` from an IDE.

## Test
```bash
mvn test
```

## Main Use Cases
- Create hotel chain, hotel, room types, rooms, guests and reserver/payer.
- Make reservation with date validation and room availability check.
- Check in guest and mark room occupied.
- Check out guest and free room.
- Cancel reservation before check-in.
