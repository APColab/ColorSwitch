package menupages;

import model.Game;

public class MainMenu
{
    public MainMenu()
    {
        MainMenuView mmv = new MainMenuView(this);
    }

    public void newGame()
    {
        System.out.println("New Game Pressed");
        Game gm = new Game();
        gm.getGameView().getGameStage().show();
        return;
    }

    public void loadGame()
    {
        System.out.println("Load Game Pressed");
        return;
    }

    public void exit()
    {
        System.out.println("Exit Game Pressed");
        System.exit(0);
    }

}

