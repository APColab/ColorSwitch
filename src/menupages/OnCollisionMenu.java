package menupages;

import model.Game;

public class OnCollisionMenu {
    Game game;
    private OnCollisionMenuView oncv;

    public OnCollisionMenu(Game game)
    {
        this.game = game;
        oncv = new OnCollisionMenuView(this);
    }

    public void reviveGame()
    {

    }

    public void restartGame()
    {
        this.game.getGameView().getGameStage().close();
        this.game = new Game();
        oncv.getOnCollisionStage().hide();
        game.getGameView().getGameStage().show();
    }

    public void exitToMainMenu()
    {
        this.game.getGameView().getGameStage().close();
        this.game = null;
        MainMenu mainMenu = new MainMenu();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


}
