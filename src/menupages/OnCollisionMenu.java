package menupages;

import model.Game;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
        int nor = game.getNumberOfRevivals();
        int requiredForRevival = (int) Math.pow(2,game.getNumberOfRevivals());
        if(game.getCollectedStars().getTotalStars()+game.getScore()>requiredForRevival){
            if(requiredForRevival>=game.getScore()){
                requiredForRevival -= game.getScore();
                game.setScore(0);
                game.getCollectedStars().setTotalStars(game.getCollectedStars().getTotalStars()-requiredForRevival);
            }else{
                game.setScore(game.getScore()-requiredForRevival);
            }
            Long score = game.getScore();
            this.game.getGameView().getGameStage().close();
            this.game = new Game();
            this.game.setScore(score);
            this.game.setNumberOfRevivals(nor+1);
            oncv.getOnCollisionStage().hide();
            game.getGameView().getGameStage().show();
        }

    }

    public void restartGame()
    {
        try {
            this.game.exitGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.game.getGameView().getGameStage().close();
        this.game = new Game();
        oncv.getOnCollisionStage().hide();
        game.getGameView().getGameStage().show();
    }

    public void exitToMainMenu()
    {
        try {
            this.game.exitGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
