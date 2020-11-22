package view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.*;

public class GameView {
    private Game game;

    private final String GAME_BACKGROUND_URL = "resources/gamebg.png";
    private BackgroundImage gameBackground;

    private Pane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private AnchorPane[] obstaclePane;
    private ScoreView scoreView;

    private final float HEIGHT = 800;
    private final float WIDTH = 600;

    public GameView(Game game){
        this.game = game;
        initializeStage();
        gameStage.setResizable(false);
        needToDeleteThisAndDoThisTheProperWay();
    }

    private void needToDeleteThisAndDoThisTheProperWay() {
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                CircularObstacle c = new CircularObstacle();
                c.setPos_X(WIDTH/2-c.getRadius());
                c.setPos_Y(j*4*c.getRadius());
                obstaclePane[i].getChildren().add(c.getCircularObstacleView());
                Star s = new Star(c);
                obstaclePane[i].getChildren().add(s.getStarView());
                ColorSwitch cs = new ColorSwitch(c);
                obstaclePane[i].getChildren().add(cs.getColorSwitchView());
            }
        }
    }


    public void initializeStage(){
        this.gamePane = new Pane();
        this.gameScene = new Scene(gamePane,WIDTH,HEIGHT);
        this.gameStage = new Stage();
        this.obstaclePane = new AnchorPane[2];

        gameBackground = new BackgroundImage(new Image(GAME_BACKGROUND_URL),
                BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);

        gamePane.setBackground(new Background(gameBackground));


        for(int i=0;i<2;i++){
            obstaclePane[i] = new AnchorPane();
            obstaclePane[i].setPrefHeight(HEIGHT);
            obstaclePane[i].setPrefWidth(WIDTH);
            obstaclePane[i].setLayoutX(0);
        }
        obstaclePane[0].setLayoutY(-0.5*HEIGHT);
        obstaclePane[1].setLayoutY((obstaclePane[0].getLayoutY()-HEIGHT));
        scoreView = new ScoreView();
        gamePane.getChildren().add(scoreView);
//        obstaclePane[1].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(10))));
//        obstaclePane[0].setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(10))));
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
