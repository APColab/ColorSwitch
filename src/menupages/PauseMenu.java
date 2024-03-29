package menupages;

import model.Game;
import model.GameState;
import model.Obstacle;


//make a subpane for this
public class PauseMenu
{
    private Game game;
    private PauseMenu pausemenu;
    private PauseMenuView pmv;
    public PauseMenu(Game _game)
    {
        pmv = new PauseMenuView();
        game = _game;
    }

    public void setMenu(PauseMenu p)
    {
        pausemenu=p;
        pmv.setPauseMenu(p);
    }

    public PauseMenu getPausemenu() {
        return pausemenu;
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
        game.resumeGame();
        game.getGameView().getGamePane().requestFocus();
    }

    public void exitToMainMenu()
    {
        MainMenu mm = new MainMenu();
    }
}
