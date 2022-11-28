/***
 * The HuntingStore class represents a store.
 * The store contains items that the player can buy or sell.
 * It also contains the player's balance and items owned.
 */
public class HuntingStore {
    /*variables*/
    private static double balance;
    private static String items = "Hunt items: ";
    private String boughtItems = "Bought items: ";

    /*Constructors*/

    /***
     * 0-parameter constructor for the HuntingStore class.
     * It creates an instance of a HuntingStore.
     * It defaults the player's balance to $0.
     */
    public HuntingStore()  //defaults the balance to $50
    {
        balance = 0;
    }

    /***
     * Second constructor for the HuntingStore class.
     * It creates an instance of a HuntingStore with the parameters below.
     * It sets the player's balance to a custom value of their choice.
     *
     * @param startingbal a double representing the custom balance inputted by the user.
     */
    public HuntingStore(double startingbal)
    {
        balance = startingbal;
    }

    /*methods*/

    /***
     * The toString method for the HuntingStore class.
     * It will return a string containing the information for the store and the player's inventory.
     *
     * @return a String in a formatted display that contains the items that can be sold, bought, and the player's current balance and items.
     */
    public String toString()
    {
        return """
                ------------------------------
                Sell:
                Bird - $5
                Bear - $10
                Cryptid Evidence - $100
                ------------------------------
                Buy:
                1. Feather hat - $8
                2. Bear-skin rug - $15
                3. Preserved Cryptid Remains - $200
                ------------------------------
                Current balance:\040""" + HuntingClient.ANSI_GREEN + String.format("$%.2f", balance) + HuntingClient.ANSI_RESET +
                "\n" + items + "\n" + boughtItems +
                "\n------------------------------";
    }

    /***
     * @return a double representing the player's current balance.
     */
    public static double getBalance() //returns the current balance of the user
    {
        return balance;
    }

    /***
     * The updateBal method adds value to balance if the player sells an item, or subtracts value to balance if the player buys an item.
     *
     * @param value a double representing how much money earned or spent/lost.
     */
    public static void updateBal(double value)
    {
        balance += value;
    }

    /***
     * @return a String representing the current items the player has in their inventory.
     */
    public String getAllItems() {
        return items + "\n" + boughtItems;
    }

    /***
     * The addHuntItems method adds the item obtained from the hunt to the player's inventory.
     *
     * @param newItem a String representing the item that the player obtained from hunting.
     */
    public static void addHuntItems(String newItem) {
        items = items + newItem + ", ";
    }

    /***
     * The addBoughtItems method adds the item bought by the player to their inventory.
     *
     * @param newItem a String representing the item that player bought.
     */
    public void addBoughtItems(String newItem) {
        boughtItems = boughtItems + newItem + ", ";
    }

    /***
     * The canSell method dictates whether the player can sell a specific item if the player has it in their inventory.
     *
     * @param hasItem a String representing the item that the player wants to sell.
     * @return a boolean representing whether the player can sell said item or not.
     */
    public boolean canSell(String hasItem)
    {
        for (int i = 0; i < items.length() - hasItem.length(); i++) {
            String item = items.substring(i, i + hasItem.length());
            if (item.equalsIgnoreCase(hasItem))
            {
                return true;
            }
        }
        return false;
    }

    /***
     * The canBuy method dictates whether the player can buy the item based on if they have enough money.
     *
     * @param buyItem a String representing the item that the player wants to buy.
     * @return a boolean representing whether the player can buy said item or not.
     */
    public boolean canBuy(String buyItem)
    {
        if (buyItem.equals("1"))
        {
            return (balance >= 8);
        }
        if (buyItem.equals("2"))
        {
            return  (balance >= 15);
        }
        if (buyItem.equals("3"))
        {
            return  (balance >= 200);
        }
        return false;
    }

    /***
     * The sell method removes the item that the player wants to sell from their inventory.
     *
     * @param sellItem a String representing the item that the player wants to sell.
     */
    public void sell(String sellItem)
    {
        if (sellItem.equalsIgnoreCase("bird")) {
            updateBal(5);
            for (int i = 0; i < items.length() - sellItem.length(); i++) {
                String item = items.substring(i, i + sellItem.length());
                if (item.equalsIgnoreCase(sellItem))
                {
                    items = items.substring(0, i) + items.substring(i + sellItem.length() + 2);
                    break;
                }
            }
        }
        if (sellItem.equalsIgnoreCase("bear")) {
            updateBal(10);
            for (int i = 0; i < items.length() - sellItem.length(); i++) {
                String item = items.substring(i, i + sellItem.length());
                if (item.equalsIgnoreCase(sellItem))
                {
                    items = items.substring(0, i) + items.substring(i + sellItem.length() + 2);
                    break;
                }
            }
        }
        if (sellItem.equalsIgnoreCase("Cryptid Evidence")) {
            updateBal(100);
            for (int i = 0; i < items.length() - sellItem.length(); i++) {
                String item = items.substring(i, i + sellItem.length());
                if (item.equalsIgnoreCase(sellItem))
                {
                    items = items.substring(0, i) + items.substring(i + sellItem.length() + 2);
                    break;
                }
            }
        }
    }

    /***
     * The buy method adds the item that the player buys to their inventory.
     *
     * @param buyItem a String representing the item that the player wants to buy.
     */
    public void buy(String buyItem)
    {
        if (buyItem.equals("1"))
        {
            addBoughtItems("Feather hat");
            updateBal(-8);
        }
        if (buyItem.equals("2"))
        {
            addBoughtItems("Bear-skin rug");
            updateBal(-15);
        }
        if (buyItem.equals("3"))
        {
            addBoughtItems("Preserved Cryptid Remains");
            updateBal(-200);
        }
    }
}
