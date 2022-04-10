import api.AdminResource;
import model.*;
import util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class AdminMenu {

    private final String line = "--------------------------------------------------\n";
    private final String tip = "Please select a number for the menu option\n";
    private final String welcome = "\nAdmin Menu\n";

    private Util util = new Util();
    private AdminResource adminResource =  new AdminResource();

    public String adminMenuText () {
        return welcome + line + "1. See all Customers\n" +
                "2. See all Rooms\n" +
                "3. See all Reservations\n" +
                "4. Add a Room\n" +
                "5. Add Test Data\n" +
                "6. Back to Main Menu\n" + line +
                tip;
    }

    public void showAdminMenu() {
        System.out.println(adminMenuText());
    }
    public void deploy (Scanner scanner) {
        boolean keepRunning = true;
        //try (Scanner scanner = new Scanner(System.in)) {
            while (keepRunning) {
                try {
                    showAdminMenu();
                    Integer selection = Integer.parseInt(util.adminMenuSelection(scanner));
                    switch (selection) {
                        // 1. See all Customers
                        case 1:
                            //System.out.println("See all Customers");
                            Collection<Customer> allCustomers = adminResource.getAllCustomers();
                            if (allCustomers.isEmpty()) {
                                System.out.println("No Customer");
                            } else {
                                for (Customer customer : allCustomers) {
                                    System.out.println(customer);
                                }
                            }
                            break;

                        // 2. See all Rooms
                        case 2:
                            //System.out.println("See all Rooms");
                            adminResource.displayAllRooms();
                            break;

                        // 3. See all Reservations
                        case 3:
                            //System.out.println("See all Reservations");
                            adminResource.displayAllReservations();
                            break;

                        // 4. Add a Room
                        case 4:
                            String roomNumber = "";
                            Double price = 0.0;
                            RoomType roomType = RoomType.SINGLE;
                            ArrayList<IRoom> rooms = new ArrayList<IRoom>();
                            boolean kR = false;
                            do {
                                System.out.println("Enter room number");
                                roomNumber = util.inputRoomNumber(scanner);
                                if (isAlreadyInput(rooms, roomNumber) || adminResource.isRoomExists(roomNumber)) {
                                    System.out.println("Error Room Exists.");
                                } else {

                                    //System.out.println("Enter price per night");
                                    price = util.inputPrice(scanner);
                                    System.out.println("Enter room type: ");
                                    roomType = util.inputRoomType(scanner);

                                    if (0 == Double.compare(price, 0.0)) {
                                        rooms.add(new FreeRoom(roomNumber, roomType));
                                    } else {
                                        rooms.add(new Room(roomNumber, price, roomType));
                                    }
                                }
                                    System.out.println("Would you like to add another room y/n");
                                    String ynFlag = util.inputYesNo(scanner);
                                    if (0 == ynFlag.compareTo("yes")) {
                                        kR = true;
                                    }
                                    if (0 == ynFlag.compareTo("no")) {
                                        kR = false;
                                    }

                            } while (kR);

                            adminResource.addRoom(rooms);
                            break;
                        // 5. Add Test Data
                        case 5:
                            api.test.ResourceTester.allTest();
                            break;
                        // 6. Back to Main Menu
                        case 6:
                            keepRunning = false;
                            break;
                        default:
                            System.out.println("Please enter a number between 1 and 5");
                            //keepRunning = false;
                            break;
                    }
                } catch (Exception ex) {
                    System.out.println("\nError - Invalid Input\n");
                }
            }
            /*
        } catch (Exception ex) {
            System.out.println(ex);
        }

             */
    }

    public boolean isAlreadyInput (ArrayList<IRoom> rooms, String roomNumber) {
        for (IRoom room : rooms) {
            if (roomNumber.equals(room.getRoomNumber())) {
                return true;
            }
        }
        return false;
    }
}
