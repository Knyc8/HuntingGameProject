public class HuntingGame {
    //private variables
    private int playerHp = 10;
    private int bearHp = 25;
    private int cryptidHp = 50;
    private int playerDmg = 0;
    private int beastDmg = 0;

    //default constructor

    /***
     * Simulates a random number generator between 1 and 15
     * @return either "bird", "bear", or "cryptid" based on the number chosen by the Random method
     */
    public String getBeast() {
        int beastChance = (int) (Math.random() * 15) + 1;
        String beastType;
        if (beastChance <= 10)
        {
            beastType = "bird";
        }
        else if (beastChance <= 14)
        {
            beastType = "bear";
        }
        else
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
        HuntingStore.addHuntItems("Bird");
        return skyGrid.substring(0, 2 * guessShot - 1) + "O" + skyGrid.substring(2 * guessShot);
    }

    public void attack(){
        int damage = (int)(Math.random() * 6) + 1;
        if (damage <= 3)
        {
            playerDmg = 1;
        }
        else if (damage <= 5)
        {
            playerDmg = 3;
        }
        else {
            playerDmg = 10;
        }
    }

    public void updatePlayerStats() {
        playerHp -= beastDmg;
    }

    //bear-related code:
    public String bearFight()
    {
        if (bearHp <= 0) {
            bearHp = 0;
            return "Bear health: " + bearHp +
                    "\nYour health: " + playerHp +
                    "\nThe bear collapses onto the floor." +
                    "\nA bear is added to your inventory.";
        }
        else if (playerHp <= 0) {
            playerHp = 0;
            return "Bear health: " + bearHp +
                    "\nYour health: " + playerHp +
                    "\nYou collapse onto the floor." +
                    "\nThe bear runs away.";
        }
        else {
            return "Bear health: " + bearHp +
                    "\nYour health: " + playerHp +
                    "\nThe bear dealt " + beastDmg + " damage!" +
                    "\nYou dealt " + playerDmg + "damage!";
        }
    }

    public void updateBearStats()
    {
        bearHp -= playerDmg;
    }
}