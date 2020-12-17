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
        this.game = new Game();
        oncv.getOnCollisionStage().hide();
        game.getGameView().getGameStage().show();
    }

    public void exitToMainMenu()
    {
        System.out.println("Exit");
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


}
