public class HuntingStore {
    public static
    /*variables*/
    double balance;
    static String items = "Hunt items: Bird, Bear, Cryptid Evidence, ";
    String boughtItems = "Bought items: ";

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
                ------------------------------
                Current balance: $"""+ String.format("%.2f", balance) +
                "\n" + items + "\n" + boughtItems +
                "\n------------------------------";
    }

    public double getBalance() //returns the current balance of the user
    {
        return balance;
    }

    public void updateBal(double value)
    {
        balance += value;
    }

    public String getAllItems() {
        return items + "\n" + boughtItems;
    }

    public static void addHuntItems(String newItem) {
        items = items + newItem + ", ";
    }

    public void addBoughtItems(String newItem) {
        boughtItems = boughtItems + newItem + ", ";
    }

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
        return false;
    }

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
    }
}
