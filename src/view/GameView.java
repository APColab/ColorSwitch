package view;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Ball;
import model.Game;

public class GameView {
    private Game game;

    private Pane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private AnchorPane[] obstaclePane;

    private final float HEIGHT = 800;
    private final float WIDTH = 600;

    public GameView(Game game){
        this.game = game;
        initializeStage();
    }


    public void initializeStage(){
        this.gamePane = new Pane();
        this.gameScene = new Scene(gamePane,WIDTH,HEIGHT);
        this.gameStage = new Stage();
        this.obstaclePane = new AnchorPane[2];

        for(int i=0;i<2;i++){
            obstaclePane[i] = new AnchorPane();
            obstaclePane[i].setPrefHeight(HEIGHT);
            obstaclePane[i].setPrefWidth(WIDTH);
            obstaclePane[i].setLayoutX(0);
        }
        obstaclePane[0].setLayoutY(-0.5*HEIGHT);
        obstaclePane[1].setLayoutY((obstaclePane[0].getLayoutY()-HEIGHT));
        obstaclePane[1].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(10))));
        obstaclePane[0].setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(10))));
        gamePane.getChildren().add(game.getBall().getBallView());
        gamePane.getChildren().addAll(obstaclePane);
        gameStage.setScene(gameScene);
    }

    public AnchorPane[] getObstaclePane() {
        return obstaclePane;
    }

    public Stage getGameStage() {
        return gameStage;
    }
}
