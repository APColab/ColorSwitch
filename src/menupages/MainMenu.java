package menupages;

import model.Game;
import menupages.SavedGames;

public class MainMenu
{
    public MainMenu()
    {
        MainMenuView mmv = new MainMenuView(this);
    }

    public static void newGame(String bg)
    {
        System.out.println("New Game Pressed");
        Game gm = new Game(bg);
        gm.getGameView().getGameStage().show();
        return;
    }

    public static void loadGame()
    {
        System.out.println("Load Game Pressed");
        SavedGames.loadSavedGames();
        return;
    }

    public static void exit()
    {
        System.out.println("Exit Game Pressed");
        System.exit(0);
    }

}

