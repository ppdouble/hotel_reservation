package util;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TestInput {
    public static void main (String[] args) {
        boolean keepRunning = true;
        while (keepRunning) {
            try {
                System.out.println("Start --- ");
                Integer selection = inputSelection();
                //Integer selection = Integer.parseInt(selectionString);
                String email;
                switch (selection) {
                    case 1:
                        // inputDate();
                        System.out.println("Do 1");

                        break;
                    case 2:
                        //inputEmail();
                        System.out.println("Do 2");
                        break;
                    case 3:
                        //inputName();
                        System.out.println("Do 3");

                        break;
                    case 4:
                        // Exit
                        keepRunning = false;
                        break;
                    default:
                        System.out.println("Please enter a number between 1 and 4: ");
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static Integer inputSelection () {
        Integer selection = 0;
        try {
            Scanner scanner = new Scanner(System.in);
            //if (!scanner.hasNextLine()) {
                //scanner.hasNextLine();
                selection = scanner.nextInt();  // reads only one int. does not finish the line.
                scanner.nextLine(); // consume '\n' to finish the line
            //}
            //scanner.close();
        } catch (NoSuchElementException ex) {
            throw ex;
        }
        return selection;
    }
}
