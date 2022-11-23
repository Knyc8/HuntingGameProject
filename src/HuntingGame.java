public class HuntingGame {
    //default constructor

    public String getBeast() {
        int beastChance = (int) (Math.random() * 15) + 1;
        String beastType = "";
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
        HuntingStore.addItems("Bird");
        return skyGrid.substring(0, 2 * guessShot - 1) + "O" + skyGrid.substring(2 * guessShot);
    }
}