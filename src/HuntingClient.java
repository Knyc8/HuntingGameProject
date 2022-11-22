import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HuntingClient {
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a name: ");
        String username = scan.nextLine();
        System.out.println("Welcome, " + username);
        System.out.println("Would you like to start with a default or custom balance? (D/C)");
        String startBal = scan.nextLine();
        HuntingGame hunt = new HuntingGame();
        while (!startBal.equalsIgnoreCase("d") && !startBal.equalsIgnoreCase("c")) {
            clearConsole();
            System.out.println("Would you like to start with a default or custom balance? (D/C)");
            startBal = scan.nextLine();
        }
        if (startBal.equalsIgnoreCase("d")) {
            hunt = new HuntingGame();
        }
        if (startBal.equalsIgnoreCase("c")) {
            System.out.println("Insert a custom balance: ");
            String cusBal = scan.nextLine();
            while (!isANumber(cusBal))
            {
                clearConsole();
                System.out.println("Insert a number: ");
                cusBal = scan.nextLine();
            }

            hunt = new HuntingGame(Double.parseDouble(cusBal));
        }

        System.out.println("(H) to begin the hunt. (S) to enter the shop. (B) to check your balance. (I) to check your inventory.");
        String event = scan.nextLine();
        event = chooseEvent(event);

        String exit = "y";
        while (exit.equalsIgnoreCase("y"))
        {
            if (event.equalsIgnoreCase("H")) {
                exit = "";
                while (!exit.equalsIgnoreCase("y")) {
                    clearConsole();
                    System.out.println("------------------------------");
                    System.out.println("You will now begin the hunt.");
                    Thread.sleep(1000);
                    clearConsole();
                    String beast = hunt.getBeast();
                    System.out.println("------------------------------");
                    System.out.println("You come across a wild " + beast + ".");
                    if (beast.equals("bird"))
                    {
                        int numShot = 3;
                        int birdPos = (int) (Math.random() * 5) + 1;
                        System.out.println("------------------------------");
                        System.out.println("Pick a number between 1 to 5.");
                        String skyGrid = "|?|?|?|?|?|";
                        System.out.println(skyGrid);
                        int guessShot = Integer.parseInt(scan.nextLine());
                        while (!(guessShot == birdPos) && numShot > 1) {
                            numShot--;
                            clearConsole();
                            skyGrid = hunt.huntBird(skyGrid, guessShot, birdPos);
                            System.out.println("------------------------------");
                            System.out.println(skyGrid);
                            System.out.println("You missed!");
                            System.out.println("You have " + numShot + " shot(s) left!");
                            guessShot = Integer.parseInt(scan.nextLine());
                        }
                        clearConsole();
                        if (guessShot == birdPos) {
                            System.out.println("------------------------------");
                            System.out.println(hunt.huntBird(skyGrid, guessShot, birdPos));
                            System.out.println("You shot down a bird!");
                            System.out.println("A bird is now placed in your inventory.");
                        }
                        else {
                            System.out.println("------------------------------");
                            System.out.println(hunt.huntBird(skyGrid, guessShot, birdPos));
                            System.out.println("You ran out of shots!");
                        }
                    }
                    if (beast.equals("bear"))
                    {
                        System.out.println("WIP Bear");
                    }
                    if (beast.equals("cryptid"))
                    {
                        System.out.println("WIP Cryptid");
                    }

                    System.out.println("\nLeave? (y)\nOr press any key to redo.");
                    exit = scan.nextLine();
                }
                event = "";
            }
            if (event.equalsIgnoreCase("S")) {
                exit = "";
                while (!exit.equalsIgnoreCase("y")) {
                    clearConsole();
                    System.out.println("------------------------------");
                    System.out.println("You make your way into the shop.");

                    System.out.println("\nLeave? (y)\nOr press any key to redo.");
                    exit = scan.nextLine();
                }
                event = "";
            }
            if (event.equalsIgnoreCase("B")) {
                exit = "";
                while (!exit.equalsIgnoreCase("y")) {
                    clearConsole();
                    System.out.println("------------------------------");
                    System.out.println("You open up your coin-sack.");
                    System.out.printf("You dig around and find $%.2f", hunt.getBalance());
                    System.out.println(".");

                    System.out.println("\nLeave? (y)\nOr press any key to redo.");
                    exit = scan.nextLine();
                }
                event = "";
            }
            if (event.equalsIgnoreCase("I")) {
                exit = "";
                while (!exit.equalsIgnoreCase("y"))
                {
                    clearConsole();
                    System.out.println("------------------------------");
                    System.out.println("You open up your knapsack.");
                    System.out.println(hunt.getAllItems());

                    System.out.println("\nLeave? (y)\nOr press any key to redo.");
                    exit = scan.nextLine();
                }
                event = "";
            }
            if (exit.equalsIgnoreCase("y")) {
                event = chooseEvent(event);
            }
        }
    }

    /***
     *
     * @param event a string that represents the initial event the user wants to choose
     * @return a string that is the inputted event choice
     */
    public static String chooseEvent(String event) {
        Scanner scan = new Scanner(System.in);
        clearConsole();
        while (!event.equalsIgnoreCase("H") && !event.equalsIgnoreCase("S") && !event.equalsIgnoreCase("B") && !event.equalsIgnoreCase("I")) {
            System.out.println("(H) to begin the hunt. (S) to enter the shop. (B) to check your balance. (I) to check your inventory.");
            event = scan.nextLine();
            clearConsole();
        }
        return event;
    }

    /***
     * simulates clearing console by skipping lines
     */
    public static void clearConsole()
    {
        for (int i = 0; i < 100; i++)
        {
            System.out.println();
            if (i == 50)
            {
                System.out.println("Nothing to see here \uD83D\uDC40");
            }
        }
    }

    /***
     *
     * @param cusBal is a string that represents the inputted custom starting balance
     * @return whether the string is numeric
     */
    public static boolean isANumber(String cusBal) {
        if (cusBal == null) {
            return false;
        }
        try {
            Double.parseDouble(cusBal);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    /***
     *
     * @param start is an int that represents the number to count down from
     * @throws InterruptedException allows the use of the Thread.sleep() method, which causes a delay
     */
    public static void countdown(int start) throws InterruptedException {
        Thread.sleep(1000);
        for (int i = start; i > 0; i--)
        {
            System.out.println(i);
            Thread.sleep(1000);
        }
    }

}