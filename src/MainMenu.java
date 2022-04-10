import api.HotelResource;
import model.*;
import util.Util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
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
        showMenu();
        boolean keepRunning = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (keepRunning) {
                try {
                    //Integer selection = util.inputSelection();
                    //Scanner scanner = new Scanner(System.in);
                    Integer selection = Integer.parseInt(util.mainMenuSelection(scanner));
                    String email = null;
                    switch (selection) {
                        // 1. Find and reserve a room
                        case 1:
                            LocalDate checkIn, checkOut;
                            System.out.println("Enter check-in Date");
                            checkIn = util.inputDate(scanner);
                            // input checkIn date, morning >= today, after noon > today
                            System.out.println("Enter check-out Date");
                            // check: checkOut > checkIn
                            checkOut = util.inputDate(scanner);
                            Collection<IRoom> roomArrayList = hotelResource.findARoom(checkIn, checkOut);
                            if (!roomArrayList.isEmpty() && null != roomArrayList) {
                                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                                for (IRoom room : roomArrayList) {
                                    System.out.println(room);
                                }
                                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                                System.out.println("Would you like to book a room? y/n");
                                String ynFlag = util.inputYesNo(scanner);
                                if (0 == ynFlag.compareTo("yes")) {

                                    System.out.println("Do you have an account with us? y/n");
                                    ynFlag = util.inputYesNo(scanner);
                                    if (0 == ynFlag.compareTo("no")) {
                                        boolean kR = false;
                                        System.out.println("Enter Email");
                                        do {
                                            kR = false;
                                            email = util.inputEmail(scanner);
                                            if (hotelResource.isEmailExists(email)) {
                                                System.out.println("Email already exists. Please re-input.");
                                                kR = true;
                                            }
                                        } while (kR);
                                        createAnAccount(scanner, email);
                                    }
                                    if (0 == ynFlag.compareTo("yes")){
                                        boolean kR = false;
                                        System.out.println("Enter Email");
                                        do {
                                            kR = false;
                                            email = util.inputEmail(scanner);
                                            if (!hotelResource.isEmailExists(email)) {
                                                System.out.println("Email not exists. Re-input");
                                                kR = true;
                                            }
                                        } while (kR);
                                    }

                                    System.out.println("Which room number would you like to reserve");
                                    boolean kR = false;
                                    IRoom room = null;
                                    do {
                                        kR = false;
                                        String roomNumber = util.inputRoomNumber(scanner);
                                        room = findAvailableRoomFromList(roomArrayList, roomNumber);
                                        if (null == room) {
                                            System.out.println("Wrong RoomNumber, Please Re-select");
                                            kR = true;
                                        }
                                    } while (kR);

                                    Reservation reservation = hotelResource.bookARoom(email, room, checkIn, checkOut);
                                    System.out.println(reservation);
                                }
                            } else {
                                System.out.println("No room available");
                            }
                            showMenu();
                            break;
                        // 2. See my reservations
                        case 2:
                            System.out.println("Enter Email");
                            email = util.inputEmail(scanner);

                            Collection<Reservation> myReservations = hotelResource.getCustomersReservations(email);
                            if (null != myReservations) {
                                if (myReservations.isEmpty()) {
                                    System.out.println("I don't have any reservations.");
                                } else {
                                    for (Reservation r : myReservations) {
                                        System.out.println(r);
                                    }
                                }
                            }
                            showMenu();
                            break;
                        // 3. Create an account
                        case 3:
                            System.out.println("Enter Email");
                            email = util.inputEmail(scanner);
                            if (hotelResource.isEmailExists(email)) {
                                System.out.println("Email already exists.");
                            } else {
                                createAnAccount(scanner, email);
                            }

                            showMenu();
                            break;
                        // 4. Admin (open the admin menu described below)
                        case 4:
                            adminMenu.deploy(scanner);
                            showMenu();
                            break;
                        // 5. Exit (exit the application)
                        case 5:
                            keepRunning = false;
                            break;
                        case 6:
                            showMenu();
                            break;
                        default:
                            System.out.println("Error - Invalid input.\n Select 1-6. 5 for Exit. 6 show MainMenu ");
                            break;
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getLocalizedMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public void createAnAccount (Scanner scanner, String email) {

            System.out.println("First Name");
            //String firstName = scanner.nextLine();
            String firstName = util.inputName(scanner);
            System.out.println("Last Name");
            //String lastName = scanner.nextLine();
            String lastName = util.inputName(scanner);
            hotelResource.createACustomer(email, firstName, lastName);
    }

    public boolean isRoomExistInList (Collection<IRoom> rooms, String roomNumber) {
        for (IRoom room : rooms) {
            if (0 == roomNumber.compareTo(room.getRoomNumber())) {
                return true;
            }
        }

        return false;
    }

    public IRoom findAvailableRoomFromList (Collection<IRoom> rooms, String roomNumber) {
        for (IRoom room : rooms) {
            if (0 == roomNumber.compareTo(room.getRoomNumber())) {
                return room;
            }
        }

        return null;
    }
}
