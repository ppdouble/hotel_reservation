package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    private CustomerService customerService = new CustomerService();
    private ReservationService reservationService = new ReservationService();

    public Customer getCustomer (String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom (List<IRoom> rooms) {
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }

    public boolean isRoomExists(String roomNumber) {
        return reservationService.isRoomExists(roomNumber);
    }

    public Collection<Customer> getAllCustomers () {
        return customerService.getAllCustomer();
    }

    public void displayAllRooms () {
        reservationService.printAllRoom();
    }

    public void displayAllReservations () {
        reservationService.printAllReservation();
    }
}
