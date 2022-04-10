import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import util.Util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenuClass {
    private final String line = "--------------------------------------------------\n";
    private final String tip = "Please select a number for the menu option\n";
    private final String welcome = "\nWelcome to the Hotel Reservation Application\n\n";
    private Util util = new Util();

    private HotelResource hotelResource = new HotelResource();

    public String mainMenuText () {
        return  welcome + line + "1. Find and reserve a room\n" +
                "2. See my reservations\n" +
                "3. Create an account\n" +
                "4. Admin\n" +
                "5. Exit\n" + line +
                tip;
    }



    public void showMenu () {
        System.out.println(mainMenuText());
    }

    public void deploy () {
        AdminMenu adminMenu = new AdminMenu();
        boolean keepRunning = true;
        while (keepRunning) {
            try {
                showMenu();
                //Integer selection = util.inputSelection();
                Integer selection = Integer.parseInt(util.inputSelection());
                String email = "";
                switch (selection) {
                    // 1. Find and reserve a room
                    case 1:
                        LocalDate checkIn, checkOut;
                        System.out.println("Enter check-in Date");
                        checkIn = util.inputDate();
                        // input checkIn date, morning >= today, after noon > today
                        System.out.println("Enter check-out Date");
                        // check: checkOut > checkIn
                        checkOut = util.inputDate();
                        Collection<IRoom> roomArrayList = hotelResource.findARoom(checkIn, checkOut);
                        Room room = (Room)roomArrayList;

                        System.out.println("Would you like to book a room? y/n");
                        System.out.println("Do you have an account with us? y/n");
                        String customerEmail = "";
                        System.out.println("Enter Email");
                        //customerEmail = util.inputEmail();
                        System.out.println("What room number would you like to reserve");
                        Reservation reservation = hotelResource.bookARoom(customerEmail, room, checkIn, checkOut);
                        System.out.println(reservation);
                        break;
                    // 2. See my reservations
                    case 2:
                        System.out.println("Enter Email");
                        //email = util.inputEmail();
                        hotelResource.getCustomersReservations(email);
                        break;
                    // 3. Create an account
                    case 3:
                        System.out.println("First Name");
                        //String firstName = scanner.nextLine();
                        String firstName = "";
                        System.out.println("Last Name");
                        //String lastName = scanner.nextLine();
                        String lastName = "";
                        //email = util.inputEmail();
                        hotelResource.createACustomer(email, firstName, lastName);
                        break;
                    // 4. Admin (open the admin menu described below)
                    case 4:
                        //adminMenu.deploy();
                        break;
                    // 5. Exit (exit the application)
                    case 5:
                        keepRunning = false;
                        break;
                    default:
                        System.out.println("Please enter a number between 1 and 5: ");
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }
}
