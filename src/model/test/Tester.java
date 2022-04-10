package model.test;

import model.*;

import java.time.LocalDate;

public class Tester {
    public static void main (String[] args) {
        Customer customer = new Customer("e@email.com", "first", "second");
        System.out.println(customer);

        IRoom freeRoom = new FreeRoom("1001", RoomType.SINGLE);
        System.out.println(freeRoom);
        System.out.println("Room " + freeRoom.getRoomNumber() + " is Free: " + freeRoom.isFree());

        IRoom room = new Room("1003", 100.3, RoomType.DOUBLE);
        System.out.println(room);
        System.out.println("Room " + room.getRoomNumber() + " is Free: " + room.isFree());

        Reservation reservation = new Reservation(customer, room,
                LocalDate.parse("2031-01-12"), LocalDate.parse("2031-01-15"));
        System.out.println(reservation);

    }
}
