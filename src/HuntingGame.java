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

    public HuntingGame(double startingbal)  //defaults the balance to user's input
    {
        balance  = startingbal;
    }

    public double getBalance() //returns the current balance of the user
    {
        return balance;
    }

    public void updateBal(double cashEarned)
    {
        balance += cashEarned;
    }

    public List<String> getAllItems()
    {
        return items;
    }

    public String getItemsAtIndex(int index)
    {
        String itemIndex = items.get(index);
        return itemIndex;
    }

    public void updateItems(String newItem)
    {
        items.add(newItem);
    }

    public String getBeast() {
        int beastChance = (int) (Math.random() * 12) + 1;
        String beastType = "";
        if (beastChance <= 10)
        {
            beastType = "bird";
        }
        if (beastChance == 11)
        {
            beastType = "bear";
        }
        if (beastChance == 12)
        {
            beastType = "cryptid";
        }
        return beastType;
    }

    //bird-related code:
    public String huntBird(String skyGrid, int guessShot, int birdPos)
    {
        if (birdPos != guessShot)
        {
            return skyGrid.substring(0, 2 * guessShot - 1) + "X" + skyGrid.substring(2 * guessShot);
        }
        items.add("bird");
        return skyGrid.substring(0, 2 * guessShot - 1) + "O" + skyGrid.substring(2 * guessShot);
    }
}