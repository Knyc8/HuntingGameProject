public class HuntingGame {
    //private variables
    private static int numHunts = 0;
    private int playerHp = 10;
    private int bearHp = 20;
    private int cryptidHp = 40;
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
        String beastType = "cryptid";
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



    /*bird-related code: */
    public String huntBird(String skyGrid, int guessShot, int birdPos)
    {
        if (birdPos != guessShot)
        {
            return skyGrid.substring(0, 2 * guessShot - 1) + "X" + skyGrid.substring(2 * guessShot);
        }
        HuntingStore.addHuntItems("Bird");
        return skyGrid.substring(0, 2 * guessShot - 1) + "O" + skyGrid.substring(2 * guessShot);
    }



    /*player-related code: */
    public void resetStats() {
        playerHp = 10;
        bearHp = 20;
        cryptidHp = 40;
        isBlocking = false;
        playerDmg = 0;
        beastDmg = 0;
    }

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

    public int getBeastDmg() {
        return beastDmg;
    }



    /*bear-related code: */
    public String bearFight()
    {
        if (playerHp <= 0) {
            playerHp = 0;
            return "Bear health: " + HuntingClient.ANSI_GREEN + bearHp + HuntingClient.ANSI_RESET +
                    "\nYour health: " + HuntingClient.ANSI_GREEN + playerHp + HuntingClient.ANSI_RESET +
                    "\nYou collapse onto the floor." +
                    "\nThe bear runs away.";
        }
        else if (bearHp <= 0) {
            bearHp = 0;
            HuntingStore.addHuntItems("Bear");
            return "Bear health: " + HuntingClient.ANSI_GREEN + bearHp + HuntingClient.ANSI_RESET +
                    "\nYour health: " + HuntingClient.ANSI_GREEN + playerHp + HuntingClient.ANSI_RESET +
                    "\nThe bear collapses onto the floor." +
                    "\nA " + HuntingClient.ANSI_YELLOW + "bear" + HuntingClient.ANSI_RESET + " is added to your inventory.";
        }
        else {
            return "Bear health: " + HuntingClient.ANSI_GREEN + bearHp + HuntingClient.ANSI_RESET +
                    "\nYour health: " + HuntingClient.ANSI_GREEN + playerHp + HuntingClient.ANSI_RESET;
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





    /*Cryptid-related code: */
    public String cryptidFight()
    {
        if (playerHp <= 0) {
            playerHp = 0;
            HuntingStore.updateBal(-HuntingStore.getBalance() / 2);
            return "Cryptid health: " + HuntingClient.ANSI_GREEN + cryptidHp + HuntingClient.ANSI_RESET +
                    "\nYour health: " + HuntingClient.ANSI_GREEN + playerHp + HuntingClient.ANSI_RESET +
                    "\nYou collapse onto the floor." +
                    "\nThe cryptid consumes you." +
                    "\nYour balance is now halved.";
        }
        else if (cryptidHp <= 0) {
            cryptidHp = 0;
            HuntingStore.addHuntItems("Cryptid Evidence");
            return "Cryptid health: " + HuntingClient.ANSI_GREEN + cryptidHp + HuntingClient.ANSI_RESET +
                    "\nYour health: " + HuntingClient.ANSI_GREEN + playerHp + HuntingClient.ANSI_RESET +
                    "\nThe cryptid falls to the ground." +
                    "\nA piece of " + HuntingClient.ANSI_YELLOW + "cryptid evidence" + HuntingClient.ANSI_RESET + " is added to your inventory.";
        }
        else {
            return "Cryptid health: " + HuntingClient.ANSI_GREEN + cryptidHp + HuntingClient.ANSI_RESET +
                    "\nYour health: " + HuntingClient.ANSI_GREEN + playerHp + HuntingClient.ANSI_RESET;
        }
    }

    public void cryptidAttack() {
        int damage = (int)(Math.random() * 6) + 1;
        if (damage <= 5)
        {
            beastDmg = 0;
        }
        else
        {
            beastDmg = 5;
        }
    }

    public void updateCryptidStats()
    {
        cryptidHp -= playerDmg;
    }

    public int getCryptidHp() {
        return cryptidHp;
    }
}