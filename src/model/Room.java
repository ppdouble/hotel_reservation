package model;

import static java.lang.Double.compare;

public class Room implements IRoom {
    private String roomNumber;
    private Double price;
    private RoomType roomType;

    public Room (String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    public Room() {

    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        if (0 == compare(this.price, 0.0)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber +
                ", Price: " + price +
                ", Room Type: " + roomType;
    }
}
