import java.util.*;

public class HuntingGame {
    /*variables*/
    double balance;
    int i = 5;
    ArrayList<String> items = new ArrayList<String>();

    /*Constructors*/
    public HuntingGame()  //defaults the balance to $50
    {
        balance  = 50;
    }

    public double getBalance() //returns the current balance of the user
    {
        return balance;
    }

    public void updateBal(double cashEarned)
    {
        balance += cashEarned;
    }

    public List<String> getItems()
    {
        return items;
    }

    public void updateItems(ArrayList items)
    {
        items.add("cow");
    }

    //public string getFish() //returns the list of user's fish
}