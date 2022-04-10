package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

public class HotelResource {

    private CustomerService customerService = new CustomerService();
    private ReservationService reservationService = new ReservationService();

    public Customer getCustomer (String email) {
        return customerService.getCustomer(email);
    }

    public void createACustomer (String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public boolean isEmailExists (String email) {
        return customerService.isEmailExists(email);
    }


    public IRoom getRoom (String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom (String customerEmail, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        return reservationService.reserveRoom (customerService.getCustomer(customerEmail), room, checkInDate, checkOutDate);
        // show reservation
    }

    public Collection<Reservation> getCustomersReservations (String customerEmail) {
        Customer customer = null;
        try {
            customer = customerService.getCustomer(customerEmail);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getLocalizedMessage());
            return null;
        }
        return reservationService.getCustomersReservation(customer);
    }

    public Collection<IRoom> findARoom (LocalDate checkIn, LocalDate checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }
}
