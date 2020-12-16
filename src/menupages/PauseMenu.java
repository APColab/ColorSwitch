package menupages;

import model.Game;


//make a subpane for this
public class PauseMenu
{
    Game game;
    public PauseMenu(Game _game)
    {
        PauseMenuView pmv = new PauseMenuView(_game);
        game = _game;
    }

    public Game getGame()
    {
        return game;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    public void resumeGame()
    {
        //resume paused game
        return;
    }

    public void exitToMainMenu()
    {
        MainMenu mm = new MainMenu();
    }
}
