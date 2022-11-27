public class HuntingGame {
    //private variables
    private static int numHunts = 0;
    private int playerHp = 10;
    private int bearHp = 20;
    private int cryptidHp = 50;
    private boolean isBlocking = false;
    private int playerDmg = 0;
    private int beastDmg = 0;

    //default constructor

    public int getNumHunts()
    {
        return numHunts;
    }

    public void updateNumhunts()
    {
        numHunts++;
    }

    /***
     * Simulates a random number generator between 1 and 15
     * @return either "bird", "bear", or "cryptid" based on the number chosen by the Random method
     */
    public String getBeast() {
        int beastChance = (int) (Math.random() * 15) + 1;
        String beastType = "bear";
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

    /*bird-related code:*/
    public String huntBird(String skyGrid, int guessShot, int birdPos)
    {
        if (birdPos != guessShot)
        {
            return skyGrid.substring(0, 2 * guessShot - 1) + "X" + skyGrid.substring(2 * guessShot);
        }
        HuntingStore.addHuntItems("Bird");
        return skyGrid.substring(0, 2 * guessShot - 1) + "O" + skyGrid.substring(2 * guessShot);
    }

    /*player-related code:*/
    public void attack() {
        isBlocking = false;
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

    public void block() {
        isBlocking = true;
    }

    public void updatePlayerStats() {
        if (isBlocking == true)
        {
            playerDmg = 0;
        }
        playerHp -= beastDmg;
    }

    public int getPlayerHp() {
        return playerHp;
    }

    public int getPlayerDmg() {
        return playerDmg;
    }

    //bear-related code:
    public void resetStats() {
        playerHp = 10;
        bearHp = 20;
        isBlocking = false;
        playerDmg = 0;
        beastDmg = 0;
    }
    public String bearFight()
    {
        if (playerHp <= 0) {
            playerHp = 0;
            return "Bear health: " + bearHp +
                    "\nYour health: " + playerHp +
                    "\nYou collapse onto the floor." +
                    "\nThe bear runs away.";
        }
        else if (bearHp <= 0) {
            bearHp = 0;
            HuntingStore.addHuntItems("Bear");
            return "Bear health: " + bearHp +
                    "\nYour health: " + playerHp +
                    "\nThe bear collapses onto the floor." +
                    "\nA bear is added to your inventory.";
        }
        else {
            return "Bear health: " + bearHp +
                    "\nYour health: " + playerHp;
        }
    }

    public void bearAttack() {
        int damage = (int)(Math.random() * 3) + 1;
        if (damage <= 2)
        {
            beastDmg = 1;
        }
        else
        {
            beastDmg = 3;
        }
    }

    public void updateBearStats()
    {
        bearHp -= playerDmg;
    }

    public int getBearHp() {
        return bearHp;
    }

    public int getBeastDmg() {
        return beastDmg;
    }
}