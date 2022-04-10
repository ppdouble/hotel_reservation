package model;

import java.time.LocalDate;
import java.util.Date;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation (Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getCheckInDate () {
        return checkInDate;
    }

    public LocalDate getCheckOutDate () {
        return checkOutDate;
    }

    public Customer getCustomer () {
        return customer;
    }

    public IRoom getRoom () {
        return room;
    }

    @Override
    public String toString () {
        return customer +
                " reserve " +
                room +
                " from Date: " + checkInDate +
                " to Date: " + checkOutDate;
    }
}
