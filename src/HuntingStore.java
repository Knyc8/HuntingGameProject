import java.util.*;
public class HuntingStore {
    public static
    /*variables*/
    double balance;
    int i = 5;
    static ArrayList<String> items = new ArrayList<String>();

    /*Constructors*/
    public HuntingStore()  //defaults the balance to $50
    {
        balance = 0;
    }

    public HuntingStore(double startingbal)  //defaults the balance to user's input
    {
        balance = startingbal;
    }

    /*methods*/
    public String toString()
    {
        return "Sell:" +
                "\nBird - $5" +
                "\nBear - $10" +
                "\nUnidentified object - $100" +
                "\n\nBuy:" +
                "\nFeather hat - $8" +
                "\nBear skin rug - $15";
    }

    public double getBalance() //returns the current balance of the user
    {
        return balance;
    }

    public List<String> getAllItems() {
        return items;
    }

    public static void addItems(String newItem) {
        items.add(newItem);
    }

    public boolean canSell(String hasItem)
    {
        for (int i = 0; i < items.size(); i++) {
            if ((items.get(i) + "").equalsIgnoreCase(hasItem)) {
                return true;
            }
        }
        return false;
    }
}
