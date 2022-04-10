package api.test;

import api.AdminResource;
import api.HotelResource;
import model.*;
import service.ReservationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ResourceTester {
    public static void main (String[] args) {
        allTest();
    }

    public static void allTest () {
        HotelResource hotelResource = new HotelResource();
        AdminResource adminResource = new AdminResource();

        System.out.println("Call HotelResource createACustomer");
        hotelResource.createACustomer("n@email.com", "fn", "ln");
        hotelResource.createACustomer("l@email.com", "fnl", "lnl");

        System.out.println("Call AdminResource getCustomer");
        Customer customer = adminResource.getCustomer("n@email.com");
        System.out.println(customer);
        System.out.println("Call AdminResource getAllCustomers");
        Collection<Customer> customers = adminResource.getAllCustomers();
        for (Customer c : customers) {
            System.out.println(c);
        }

        System.out.println("Call AdminResource addRoom");
        ArrayList<IRoom> roomArrayList = new ArrayList<IRoom>(
                List.of(
                        new Room("1001", 100.3, RoomType.DOUBLE),
                        new Room("1002", 50.3, RoomType.SINGLE),
                        new Room("1003", 100.3, RoomType.DOUBLE),
                        new Room("1004", 100.3, RoomType.DOUBLE),
                        new FreeRoom("1005", RoomType.SINGLE)
                )
        );
        adminResource.addRoom(roomArrayList);
        // roomId daterange customer
        // 1001 01-23 01-25 A111 // not support 1001 01-26 01-27 A111 // already filter 1001 01-23 01-25 A111
        // 1002 01-23 01-25 A111
        // 1001 01-26 01-27 A112 // already filter 1001 01-23 01-25 A112
        System.out.println("Call HotelResource bookARoom");

        Reservation reservation1 = hotelResource.bookARoom("n@email.com", roomArrayList.get(0),
                LocalDate.parse("2031-01-23"), LocalDate.parse("2031-01-26"));
        // Reservation reservation2 = hotelResource.bookARoom("n@email.com", roomArrayList.get(1),
        //        LocalDate.parse("2031-01-23"), LocalDate.parse("2031-01-26"));    // never happen
        /*
        Reservation reservation3 = hotelResource.bookARoom("l@email.com", roomArrayList.get(0),
                LocalDate.parse("2031-01-24"), LocalDate.parse("2031-01-26"));  // possible happen. will replace add new item in reservationHashMap
        */
        Reservation reservation4 = hotelResource.bookARoom("l@email.com", roomArrayList.get(1),
                LocalDate.parse("2031-01-27"), LocalDate.parse("2031-02-23"));
        System.out.println(reservation1);
       // System.out.println(reservation3);

        System.out.println("Call HotelResource findARoom");
        Collection<IRoom> availableRooms = hotelResource.findARoom(
                LocalDate.parse("2031-01-26"), LocalDate.parse("2031-01-27"));
        for (IRoom a : availableRooms) {
            System.out.println(a.getRoomNumber());
        }
        availableRooms = hotelResource.findARoom(
                LocalDate.parse("2031-01-24"), LocalDate.parse("2031-01-25"));
        for (IRoom a : availableRooms) {
            System.out.println(a.getRoomNumber());
        }
        System.out.println("Call AdminResource displayAllReservations");
        adminResource.displayAllReservations();
    }
}
