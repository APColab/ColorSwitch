package view;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Game;

public class GameView {
    private Game game;

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private final float HEIGHT = 800;
    private final float WIDTH = 600;

    public GameView(Game game){
        this.game = game;
        initializeStage();
    }



    public void initializeStage(){
        this.gamePane = new AnchorPane();
        this.gameScene = new Scene(gamePane,WIDTH,HEIGHT);
        this.gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

}
