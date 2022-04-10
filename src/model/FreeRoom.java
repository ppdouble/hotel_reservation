package model;

public class FreeRoom extends Room {

    public FreeRoom (String roomNumber, RoomType roomType) {
        super(roomNumber, 0.0, roomType);
    }

    @Override
    public String toString () {
        return "Room Number: " + super.getRoomNumber() +
                ", Price: Free, " +
                "Room Type: " + super.getRoomType();
    }
}
