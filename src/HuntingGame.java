public class HuntingGame {
    /*variables*/
    double balance;

    /*Constructors*/
    public HuntingGame()  //defaults the balance to $50
    {
        balance  = 50;
    }

    public double getBalance() //returns the current balance of the user
    {
        return balance;
    }

    public double updateBal(double cashEarned)
    {
        balance += cashEarned;
        return balance;
    }

    //public string getFish() //returns the list of user's fish
}