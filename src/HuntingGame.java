/***
 * The HuntingGame class is responsible for the logic behind the mini-games for the bird, bear, and cryptid choices
 */
public class HuntingGame {
    //private variables
    private static int numHunts = 0;
    private int playerHp = 10;
    private int bearHp = 20;
    private int cryptidHp = 40;
    private boolean isBlocking = false;
    private int playerDmg = 0;
    private int beastDmg = 0;

    /*default constructor*/

    /***
     * Returns the number of hunts the user has completed.
     *
     * @return the current number of hunts the user has completed as an integer.
     */
    public int getNumHunts()
    {
        return numHunts;
    }

    /***
     * Updates the number of hunts the user has completed.
     *
     * Adds 1 to the number of Hunts every time the player inputs "H" for hunt.
     */
    public void updateNumHunts()
    {
        numHunts++;
    }

    /***
     * Chooses the beast that the user hunts.
     *
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
    /***
     * The huntBird method will return the display for the sky in which the mini-game is played.
     *
     * @param skyGrid a String representing the original layout of the sky.
     * @param guessShot an integer representing the user's inputted guess.
     * @param birdPos an integer representing the bird's position in the sky.
     * @return a String that representing either a hit or a miss by the player.
     */
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

    /***
     * The resetStats method defaults the player health, beasts' health, player damage, beast damage, and whether the player is blocking to its default value.
     */
    public void resetStats() {
        playerHp = 10;
        bearHp = 20;
        cryptidHp = 40;
        isBlocking = false;
        playerDmg = 0;
        beastDmg = 0;
    }

    /***
     * The attack method will pick a random number between 1 and 6.
     * Using that value, it decides whether the player does 1, 3, or 10 damage by setting playerDmg to the value.
     */
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

    /***
     * The block method sets isBlocking to true so any damage done by the beast is mitigated.
     */
    public void block() {
        isBlocking = true;
    }

    /***
     * The updatePlayerStats updates the health of the player.
     * If isBlocking is true, the player's health remains unchanged.
     * If isBlocking is false, the player's health is subtracted by the value of beastDmg.
     */
    public void updatePlayerStats() {
        if (isBlocking)
        {
            beastDmg = 0;
        }
        playerHp -= beastDmg;
    }

    /***
     * Returns the player's health.
     *
     * @return an integer representing the player's health.
     */
    public int getPlayerHp() {
        return playerHp;
    }

    /***
     * Returns the player's damage.
     *
     * @return an integer representing the player's damage.
     */
    public int getPlayerDmg() {
        return playerDmg;
    }

    /***
     * Returns the beast's damage.
     *
     * @return an integer representing the beast's damage.
     */
    public int getBeastDmg() {
        return beastDmg;
    }



    /*bear-related code: */

    /***
     * The bearFight method contains the display of the stats of the player and the bear, which the client class prints.
     * It also determines whether the player has won or lost based on the health of the player or the bear.
     *
     * @return a String representing a formatted display of the bear's health, the player's health, and whether the player won or lost.
     */
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

    /***
     * The bearAttack method will pick a number between 1 and 3.
     * Using that number, it will decide whether the bear does 1 or 3 damage by setting beastDmg to the values.
     */
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

    /***
     * The updateBearStats subtracts the bear's health by the damage dealt by the player.
     */
    public void updateBearStats()
    {
        bearHp -= playerDmg;
    }

    /***
     * Returns the bear's health.
     *
     * @return an integer representing the bear's health.
     */
    public int getBearHp() {
        return bearHp;
    }





    /*Cryptid-related code: */

    /***
     * The cryptidFight method contains the display of the stats of the player and the cryptid, which the client class prints.
     * It also determines whether the player has won or lost based on the health of the player or the cryptid.
     *
     * @return a String representing a formatted display of the cryptid's health, the player's health, and whether the player won or lost.
     */
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

    /***
     * The crytidAttack method picks a number between 1 and 6.
     * Using that value, it decides whether the cryptid deals 0 or 5 damage by setting beastDmg to the values.
     */
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

    /***
     * The updateCryptidStats method subtracts the cryptid's health by the damage dealt by the player.
     */
    public void updateCryptidStats()
    {
        cryptidHp -= playerDmg;
    }

    /***
     * Returns the cryptid's health.
     *
     * @return an integer representing the cryptid's health.
     */
    public int getCryptidHp() {
        return cryptidHp;
    }
}