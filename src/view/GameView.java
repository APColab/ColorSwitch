package view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import menupages.GameButton;
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


        GameButton b = new GameButton("P", "-fx-background-color: transparent; -fx-background-image: url('/resources/green_button1.png')", "-fx-background-color: transparent; -fx-background-image: url('/resources/green_button2.png')");
        b.setLayoutX(WIDTH-100);
        b.setLayoutY(20);
        b.setPrefWidth(80);
        b.setPrefHeight(45);
        //b.setMaxWidth(80);
        //b.setMaxHeight(80);
        gamePane.getChildren().add(b);

        GameButton b1 = new GameButton("S", "-fx-background-color: transparent; -fx-background-image: url('/resources/red_button1.png')", "-fx-background-color: transparent; -fx-background-image: url('/resources/red_button2.png')");
        b1.setLayoutX(WIDTH-100);
        b1.setLayoutY(75);
        b1.setPrefWidth(80);
        b1.setPrefHeight(45);
        //b.setMaxWidth(80);
        //b.setMaxHeight(80);
        gamePane.getChildren().add(b1);
        gamePane.requestFocus();
        gameStage.setScene(gameScene);
    }

    public AnchorPane[] getObstaclePane() {
        return obstaclePane;
    }

    public Stage getGameStage() {
        return gameStage;
    }
}
