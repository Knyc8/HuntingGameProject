import java.util.Scanner;

public class HuntingRunner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a name: ");
        String username = scan.nextLine();
        System.out.println("Welcome, " + username);
        HuntingGame hunt =  new HuntingGame();

        String event = "";
        System.out.println("(Hunt) to begin the hunt. (Shop) to enter the shop. (Bal) to check your balance. (Item) to check your inventory.");
    }
}