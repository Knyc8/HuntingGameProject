import java.util.Scanner;

public class HuntingClient {
    /*Colored text*/
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a name: ");
        String username = scan.nextLine();
        System.out.println("Welcome, " + username);
        System.out.println("Would you like to start with a default or custom balance? (D/C)");
        String startBal = scan.nextLine();
        HuntingGame hunt = new HuntingGame();
        HuntingStore store =  new HuntingStore();
        while (!startBal.equalsIgnoreCase("d") && !startBal.equalsIgnoreCase("c")) {
            clearConsole();
            System.out.println("Would you like to start with a default or custom balance? (D/C)");
            startBal = scan.nextLine();
        }
        if (startBal.equalsIgnoreCase("d")) {
            store = new HuntingStore();
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

            store = new HuntingStore(Double.parseDouble(cusBal));
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
                        hunt.updateNumhunts();
                        int numShot = 3;
                        int birdPos = (int) (Math.random() * 5) + 1;
                        System.out.println("------------------------------");
                        System.out.println("Pick a number between 1 to 5.");
                        String skyGrid = "|?|?|?|?|?|";
                        System.out.println(skyGrid);
                        String guess = scan.nextLine();
                        while (!guess.equals("1") && !guess.equals("2") && !guess.equals("3") && !guess.equals("4") && !guess.equals("5"))
                        {
                            clearConsole();
                            System.out.println("------------------------------");
                            System.out.println("Please enter an integer between 1 to 5.");
                            System.out.println(skyGrid);
                            guess = scan.nextLine();
                        }
                        int guessShot = Integer.parseInt(guess);
                        while (!(guessShot == birdPos) && numShot > 1) {
                            numShot--;
                            clearConsole();
                            skyGrid = hunt.huntBird(skyGrid, guessShot, birdPos);
                            System.out.println("------------------------------");
                            System.out.println(skyGrid);
                            System.out.println("You " + ANSI_RED + "missed" + ANSI_RESET + "!");
                            System.out.println("You have " + numShot + " shot(s) left!");
                            guess = scan.nextLine();
                            while (!guess.equals("1") && !guess.equals("2") && !guess.equals("3") && !guess.equals("4") && !guess.equals("5"))
                            {
                                clearConsole();
                                System.out.println("------------------------------");
                                System.out.println("Please enter an integer between 1 to 5.");
                                System.out.println(skyGrid);
                                guess = scan.nextLine();
                            }
                            guessShot = Integer.parseInt(guess);
                            }
                        clearConsole();
                        if (guessShot == birdPos) {
                            System.out.println("------------------------------");
                            System.out.println(hunt.huntBird(skyGrid, guessShot, birdPos));
                            System.out.println("You shot down a bird!");
                            System.out.println("A " + ANSI_YELLOW + "bird" + ANSI_RESET + " is now placed in your inventory.");
                        }
                        else {
                            System.out.println("------------------------------");
                            System.out.println(hunt.huntBird(skyGrid, guessShot, birdPos));
                            System.out.println("You ran out of shots!");
                        }
                    }
                    if (beast.equals("bear"))
                    {
                        System.out.println("""
                        Info:
                        You have 10 hit points.
                        You have a 1/2 chance of dealing 1 damage, 1/3 chance of dealing 3, and a 1/6 chance of dealing 10.
                        The bear has 20 hit points.
                        It has a 2/3 chance of dealing 1 damage, and a 1/3 chance of dealing 2.
                        """);
                        hunt.updateNumhunts();
                        hunt.resetStats();
                        while (hunt.getBearHp() > 0 && hunt.getPlayerHp() > 0) {
                            System.out.println(hunt.bearFight());
                            System.out.println("Attack " + ANSI_RED + "(A)" + ANSI_RESET + " or Defend " + ANSI_CYAN + "(D)" + ANSI_RESET + "?");
                            String playerMove = scan.nextLine();
                            while (!playerMove.equalsIgnoreCase("D") && !playerMove.equalsIgnoreCase("A"))
                            {
                                clearConsole();
                                System.out.println("------------------------------");
                                System.out.println(hunt.bearFight());
                                System.out.println("Attack " + ANSI_RED + "(A)" + ANSI_RESET + " or Defend " + ANSI_CYAN + "(D)" + ANSI_RESET + "?");
                                playerMove = scan.nextLine();
                            }
                            if (playerMove.equalsIgnoreCase("D")) {
                                hunt.bearAttack();

                                clearConsole();
                                System.out.println("------------------------------");
                                hunt.block();
                                System.out.println("You blocked " + ANSI_CYAN + hunt.getBeastDmg() + ANSI_RESET + " damage!");
                            }
                            else {
                                hunt.attack();
                                hunt.bearAttack();
                                hunt.updateBearStats();
                                hunt.updatePlayerStats();

                                clearConsole();
                                System.out.println("------------------------------");
                                System.out.println("The bear dealt " + ANSI_RED + hunt.getBeastDmg() + ANSI_RESET + " damage!");
                                System.out.println("You dealt " + ANSI_RED + hunt.getPlayerDmg()+ ANSI_RESET + " damage!");
                            }
                        }
                        System.out.println(hunt.bearFight());
                    }
                    if (beast.equals("cryptid"))
                    {
                        System.out.println("""
                        Info:
                        The player has 10 hit points.
                        You have a 1/2 chance of dealing 1 damage, 1/3 chance of dealing 3, and a 1/6 chance of dealing 10.
                        The cryptid has 40 hit points.
                        It has a 1/6 chance of dealing 5 damage to the player.
                        """);
                        hunt.updateNumhunts();
                        hunt.resetStats();
                        while (hunt.getCryptidHp() > 0 && hunt.getPlayerHp() > 0) {
                            System.out.println(hunt.cryptidFight());
                            System.out.println("Attack " + ANSI_RED + "(A)" + ANSI_RESET + " or Defend " + ANSI_CYAN + "(D)" + ANSI_RESET + "?");
                            String playerMove = scan.nextLine();
                            while (!playerMove.equalsIgnoreCase("D") && !playerMove.equalsIgnoreCase("A"))
                            {
                                clearConsole();
                                System.out.println("------------------------------");
                                System.out.println(hunt.cryptidFight());
                                System.out.println("Attack " + ANSI_RED + "(A)" + ANSI_RESET + " or Defend " + ANSI_CYAN + "(D)" + ANSI_RESET + "?");
                                playerMove = scan.nextLine();
                            }
                            if (playerMove.equalsIgnoreCase("D")) {
                                hunt.cryptidAttack();

                                clearConsole();
                                System.out.println("------------------------------");
                                hunt.block();
                                System.out.println("You blocked " + ANSI_CYAN + hunt.getBeastDmg() + ANSI_RESET + " damage!");
                            }
                            else {
                                hunt.attack();
                                hunt.cryptidAttack();
                                hunt.updateCryptidStats();
                                hunt.updatePlayerStats();

                                clearConsole();
                                System.out.println("------------------------------");
                                System.out.println("The cryptid dealt " + ANSI_RED + hunt.getBeastDmg() + ANSI_RESET + " damage!");
                                System.out.println("You dealt " + ANSI_RED + hunt.getPlayerDmg() + ANSI_RESET + " damage!");
                            }
                        }
                        System.out.println(hunt.cryptidFight());
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
                    System.out.println(store);
                    System.out.println("Enter the name of the item to sell or enter a number to buy. \nWhat would you like to buy or sell?");
                    String transaction = scan.nextLine();
                    if (store.canSell(transaction) && !transaction.equals("")) {
                        store.sell(transaction);
                        System.out.println(ANSI_GREEN + "***************************");
                        System.out.println("* Transaction Successful! *");
                        System.out.println("***************************" + ANSI_RESET);
                    }
                    else if (store.canBuy(transaction) && !transaction.equals(""))
                    {
                        store.buy(transaction);
                        System.out.println(ANSI_GREEN + "***************************");
                        System.out.println("* Transaction Successful! *");
                        System.out.println("***************************" + ANSI_RESET);
                    }
                    else
                    {
                        System.out.println(ANSI_RED + "***********************");
                        System.out.println("* Transaction failed! *");
                        System.out.println("***********************" + ANSI_RESET);
                    }

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
                    System.out.printf("You dig around and find " + ANSI_GREEN + "$%.2f", store.getBalance());
                    System.out.println(ANSI_RESET + ".");

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
                    System.out.println("Number of hunts completed: " + hunt.getNumHunts());
                    System.out.println("You open up your knapsack.");
                    System.out.println(store.getAllItems());

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
     * @param testStr is a string that represents the inputted custom starting balance
     * @return whether the string is numeric
     */
    public static boolean isANumber(String testStr) {
        if (testStr == null) {
            return false;
        }
        try {
            Double.parseDouble(testStr);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}