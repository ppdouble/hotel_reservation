package service.test;

import model.*;
import service.CustomerService;
import service.ReservationService;

import java.time.LocalDate;
import java.util.Collection;

public class ReservationServiceTester {
    public static void main (String[] args) {
        ReservationService reservationService = new ReservationService();

        IRoom room = new Room("1003", 100.3, RoomType.DOUBLE);
        IRoom freeRoom = new FreeRoom("1001", RoomType.SINGLE);
        System.out.println("Call addRoom");
        reservationService.addRoom(room);
        reservationService.addRoom(freeRoom);
        System.out.println(reservationService.getARoom(room.getRoomNumber()));
        System.out.println(reservationService.getARoom(freeRoom.getRoomNumber()));

        CustomerService customerService = new CustomerService();
        customerService.addCustomer ("n@email.com", "fn", "ln");

        System.out.println("Call reserveRoom");
        reservationService.reserveRoom (customerService.getCustomer("n@email.com"), room,
                LocalDate.parse("2031-01-26"), LocalDate.parse("2031-01-28"));
        reservationService.reserveRoom(customerService.getCustomer("n@email.com"), freeRoom,
                LocalDate.parse("2031-01-27"), LocalDate.parse("2031-01-31"));

        System.out.println("Call getCustomersReservation");
        Collection<Reservation> customersReservation = reservationService.getCustomersReservation(
                customerService.getCustomer("n@email.com"));
        for (Reservation reservation : customersReservation) {
            System.out.println(reservation);
        }
        System.out.println("Call printAllReservation");
        reservationService.printAllReservation();
    }
}
