package util;

import model.RoomType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Util {

  public boolean checkFormat (String str, String regex) {
      Pattern pattern = Pattern.compile(regex);
      if (!pattern.matcher(str).matches()) {
          return false;
      }
      return true;
  }

    public boolean isAlphabets (String alphabets) {
        String alphabetsRegex = "^[a-zA-Z]+$";
        if (!checkFormat(alphabets, alphabetsRegex)) {
            return false;
        }
        return true;
    }
    public boolean isEmail (String email) {
        String emailRegex = "^(.+)@(.+).com$";
        if (!checkFormat(email, emailRegex)) {
            return false;
        }
        return true;
    }

    public boolean isDate (String dateString) {
        String dateRegex = "^[0-9]{4}\\-(1[0-2]|0[1-9])\\-(3[01]|[12][0-9]|0[1-9])$";
        if (!checkFormat(dateString, dateRegex)) {
            return false;
        }
        return true;
    }

    public boolean isSelection (String selection, String selRegex) {
        if (!checkFormat(selection, selRegex)) {
            return false;
        }
        return true;
    }

    public String inputSelection () {
        String selection = "0";
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                System.out.println("Input: 1-3");
                selection = scanner.nextLine();
            } while (!isSelection(selection, "^[0-9]$"));
        } catch (Exception ex) {
            System.out.println("\nSystem Error - Invalid. Input selection fail\n");
        }
        return selection;
    }
    public String inputSelection (Scanner scanner, String selectionRegex, String exceptionInfo) {
        String selection = "0";

        selection = scanner.nextLine();
        if (!isSelection(selection, selectionRegex)) {
            throw new IllegalArgumentException(exceptionInfo);
        }
        return selection;
    }

    public String mainMenuSelection (Scanner scanner) {
        String selection = "0";

        selection = inputSelection(scanner, "^[1-6]$", "Error - Invalid input.\n Select 1-6. 5 for Exit. 6 show MainMenu");
        return selection;
    }
    /*
    public String roomTypeFlag (Scanner scanner) {
      String selection = "0";

      selection = inputSelection(scanner, "^[12]$", )
    }
*/
    public String adminMenuSelection (Scanner scanner) {
        String selection = "0";

        selection = inputSelection(scanner, "^[1-6]$", "Error - Invalid input.\n Select 1-6. 6 for Back to Main Menu.");
        return selection;
    }

    /*
    public String inputSelection () {
      String selection = "0";
        try (Scanner scanner = new Scanner(System.in)) {
            scanner.hasNextLine();
            scanner.hasNextLine();
            scanner.hasNext();
            scanner.skip("\\R?");
            selection = scanner.nextLine();
            scanner.skip("\\R?");
            if (!isSelection(selection, "^[0-9]$")) {
                throw new IllegalArgumentException("");
            }
        } catch (Exception ex) {
            throw ex;
        }
      return selection;
    }

     */
/*
    public String inputSelection () {
      String selection = "0";
      try (Scanner scanner = new Scanner(System.in)) {
          selection = scanner.nextLine();
          scanner.close();
          scanner.remove();
          if (!isSelection(selection, "^[0-9]$")) {
              throw new IllegalArgumentException("");
          }
      } catch (Exception ex) {
          throw ex;
      }
      return selection;
    }
*/
    /*
    public Integer inputSelection () {
        Integer selection = 0;
        try (Scanner scanner = new Scanner(System.in)){
            scanner.nextLine();
            selection = scanner.nextInt();  // reads only one int. does not finish the line.
            scanner.nextLine(); // consume '\n' to finish the line
        } catch (NoSuchElementException ex) {
            throw ex;
        }

        return selection;
    }
    */

    public String inputName (Scanner scanner) {
        String name = null;
        do {
            System.out.println("Only Alphabets, upper case or lower case");
            name = scanner.nextLine();
        } while (!isAlphabets(name));

        return name;
    }

    public String inputEmail (Scanner scanner) {
        String email = null;
            do {
                System.out.println("Format: name@domain.com");
                email = scanner.nextLine();
            } while (!isEmail(email));

        return email;
    }

    public LocalDate inputDate () {
        String dateString;
        LocalDate localDate = null;
        //try(Scanner scanner = new Scanner(System.in)) {
        Scanner scanner = new Scanner(System.in);
            do {
                System.out.println("Format: yyyy-MM-dd, example 2031-01-31");
                dateString = scanner.nextLine();
            } while (!isDate(dateString));
            localDate = LocalDate.parse(dateString);

       // } catch (Exception ex) {
        //    System.out.println("\nSystem Error - Input Date Fail\n");
        //}

        return localDate;
    }

    public LocalDate inputDate (Scanner scanner) {
        String dateString;
        LocalDate localDate = null;
        //try(Scanner scanner = new Scanner(System.in)) {
        do {
        System.out.println("Format: yyyy-MM-dd, example 2031-01-31");
        dateString = scanner.nextLine();
        } while (!isDate(dateString));
        /*
        if (!isDate(dateString)) {
            throw new IllegalArgumentException("Error - Invalid input\nFormat: yyyy-MM-dd, example 2031-01-31");
        }
         */
        try {
            localDate = LocalDate.parse(dateString);
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Error - Invalid input\nFormat: yyyy-MM-dd, example 2031-01-31");
        }

        //} catch (Exception ex) {
        //    System.out.println("\nSystem Error - Input Date Fail\n");
        //}

        return localDate;
    }

    public boolean isYesNo (String input) {
        if (!checkFormat(input, "^[yYnY]$")) {
            return false;
        }

        return true;
    }
    public String inputYesNo (Scanner scanner) {
        String inputString = "N";
        do {
            System.out.println("Please enter y/Y (Yes) or n/N (No)");
            inputString = scanner.nextLine();
        } while (!isYesNo(inputString));
        if (0 == inputString.compareTo("Y") || 0 == inputString.compareTo("y")) {
            return "yes";
        } else {
            return "no";
        }
    }

    public boolean isRoomNumber (String input) {
        if (!checkFormat(input, "^[0-9]{4}$")) {
            return false;
        }
        return true;
    }
    public String inputRoomNumber (Scanner scanner) {
        String inputString = "";
        do {
            System.out.print("Four number: ");
            inputString = scanner.nextLine();
        } while (!isRoomNumber(inputString));
        return inputString;
    }

    /*
    public boolean isPrice (String input, ) {
        boolean result = true;
        try {
            Double.parseDouble(input);
        } catch (Exception ex) {
            result = false;
        }

        return result;
    }

     */
    public Double inputPrice (Scanner scanner) {
        String inputString = "";
        Double price = -8.0;
        boolean keepRunning = false;
        do {
            keepRunning = false;
            System.out.println("Enter price per night");
            inputString = scanner.nextLine();

            try {
                price = Double.parseDouble(inputString);
            } catch (Exception ex) {
                System.out.println("Invalid price");
                keepRunning = true;
            }
            if (0 == Double.compare(price, 0.0)) {
                System.out.println("free");
            }
            if (Double.compare(price, 0.0) < 0) {
                System.out.println("Invalid price");
                keepRunning = true;
            }
        } while (keepRunning);

        return price;
    }

    public RoomType inputRoomType (Scanner scanner) {
        RoomType roomType = RoomType.SINGLE;
        Integer roomTypeInt = 0;
        String selection = "0";
        do {
            System.out.println("1 for single bed, 2 for double bed");
            selection = scanner.nextLine();
        } while (!isSelection(selection, "^[12]$"));

        try {
            roomTypeInt = Integer.parseInt(selection);
        } catch (Exception ex) {
            System.out.println("Invalid input");;
        }

        if (0 == Integer.compare(1, roomTypeInt)) {
            roomType = RoomType.SINGLE;
        }
        if (0 == Integer.compare(2, roomTypeInt)) {
            roomType = RoomType.DOUBLE;
        }
        return roomType;
    }
}
