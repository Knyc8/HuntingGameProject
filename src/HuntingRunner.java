import java.util.Scanner;

public class HuntingRunner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a name: ");
        String username = scan.nextLine();
        System.out.println("Welcome, " + username);
        HuntingGame hunt = new HuntingGame();
        HuntingStore store = new HuntingStore();

        System.out.println("(Hunt) to begin the hunt. (Shop) to enter the shop. (Bal) to check your balance. (Item) to check your inventory.");
        String event = scan.nextLine();
        event = chooseEvent(event);

        String exit = "y";
        while (exit.equalsIgnoreCase("y"))
        {
            if (event.equalsIgnoreCase("Hunt")) {
                System.out.println("You will now begin the hunt.");

                System.out.println("Leave? (y/n)");
                event = "";
            }
            if (event.equalsIgnoreCase("Shop")) {
                System.out.println("You make your way into the shop.");

                System.out.println("Leave? (y/n)");
                event = "";
            }
            if (event.equalsIgnoreCase("Bal")) {
                System.out.println("You open up your coin-sack.");
                System.out.printf("You dig around and find $%.2f", hunt.getBalance());
                System.out.println(".");

                System.out.println("Leave? (y/n)");
                event = "";
            }
            if (event.equalsIgnoreCase("Item")) {
                System.out.println("You open up your knapsack.");

                System.out.println("Leave? (y/n)");
                event = "";
            }
            exit = scan.nextLine();
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
        while (!event.equalsIgnoreCase("Hunt") && !event.equalsIgnoreCase("Shop") && !event.equalsIgnoreCase("Bal") && !event.equalsIgnoreCase("Item")) {
            System.out.println("\n(Hunt) to begin the hunt. (Shop) to enter the shop. (Bal) to check your balance. (Item) to check your inventory.");
            event = scan.nextLine();
        }
        return event;
    }
}