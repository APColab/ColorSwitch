package menupages;

import model.Game;

public class OnCollisionMenu {
    Game game;
    private OnCollisionMenu onCollisionMenu;
    private OnCollisionMenuView oncv;

    public OnCollisionMenu(Game game)
    {
        this.game = game;
        oncv = new OnCollisionMenuView();
    }

    public void reviveGame()
    {

    }

    public void restartGame()
    {

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

    public void setOnCollisionMenu(OnCollisionMenu onCollisionMenu) {
        this.onCollisionMenu = onCollisionMenu;
        oncv.setOnCollisionMenu(onCollisionMenu);
    }

}
