import java.util.Scanner;

public class HuntingRunner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a name: ");
        String username = scan.nextLine();
        System.out.println("Welcome, " + username);
        HuntingGame hunt = new HuntingGame();
        HuntingStore store = new HuntingStore();

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

                    System.out.println("Leave? (y)");
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

                    System.out.println("Leave? (y)");
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

                    System.out.println("Leave? (y)");
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

                    System.out.println("Leave? (y)");
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
}