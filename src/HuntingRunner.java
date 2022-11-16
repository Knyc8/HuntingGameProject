import java.util.Scanner;

public class HuntingRunner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a name: ");
        String username = scan.nextLine();
        System.out.println("Welcome, " + username);
        HuntingGame hunt =  new HuntingGame();

        System.out.println("(Hunt) to begin the hunt. (Shop) to enter the shop. (Bal) to check your balance. (Item) to check your inventory.");
        String event = scan.nextLine();
        event = chooseEvent(event);

        String exit = "";
        while (!exit.equalsIgnoreCase("y")) {
            if (event.equalsIgnoreCase("Hunt")) {
                System.out.println("You will now begin the hunt.");

                System.out.println("Leave? (y/n)");
                exit = scan.nextLine();
                if (exit.equalsIgnoreCase("y")) {
                    event = chooseEvent(event);
                }
            }
            if (event.equalsIgnoreCase("Shop")) {
                System.out.println("You make your way into the shop.");

                System.out.println("Leave? (y/n)");
            }
            if (event.equalsIgnoreCase("Bal")) {
                System.out.println("You open up your coin-sack.");

                System.out.println("Leave? (y/n)");
            }
            if (event.equalsIgnoreCase("Item")) {
                System.out.println("You open up your knapsack.");

                System.out.println("Leave? (y/n)");
            }
        }
    }

    public static String chooseEvent(String event) {
        Scanner scan = new Scanner(System.in);
        while (!event.equalsIgnoreCase("Hunt") && !event.equalsIgnoreCase("Shop") && !event.equalsIgnoreCase("Bal") && !event.equalsIgnoreCase("Item")) {
            System.out.println("(Hunt) to begin the hunt. (Shop) to enter the shop. (Bal) to check your balance. (Item) to check your inventory.");
            event = scan.nextLine();
        }
        return event;
    }
}