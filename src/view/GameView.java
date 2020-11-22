package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import menupages.GameButton;
import model.Ball;
import model.Game;

import java.util.ArrayList;

public class GameView {
    private Game game;

    private Pane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private AnchorPane[] obstaclePane;
    private StackPane stackPane;

    private final float HEIGHT = 800;
    private final float WIDTH = 600;

    private ArrayList<GameButton> inGameButtons;
    private final String BUTTON_PATH = "-fx-background-color: transparent; -fx-background-image: url('/menupages/resources/";

    public GameView(Game game){
        stackPane = new StackPane();
        stackPane.setAlignment(Pos.TOP_CENTER);
        this.game = game;
        inGameButtons = new ArrayList<>();
        initializeStage();
        createButtons();
    }

    public ArrayList<GameButton> getInGameButtons()
    {
        return inGameButtons;
    }

    private void addInGameButtons(GameButton button, int n)
    {
        button.setLayoutY(750);
        button.setLayoutX(500 + n*50);
        button.setMaxHeight(10);
        button.setMaxWidth(10);
        inGameButtons.add(button);
        stackPane.getChildren().add(button);
    }

    private void pauseButton()
    {
        String idlePath = BUTTON_PATH + "pause.png');";
        GameButton pauseButton = new GameButton("", idlePath, idlePath);
        addInGameButtons(pauseButton, 0);
        //pause button event handler
    }

    private void saveGameButton()
    {
        String idlePath = BUTTON_PATH + "save.png');";
        GameButton saveButton = new GameButton("", idlePath, idlePath);
        addInGameButtons(saveButton, 1);
    }

    public void createButtons()
    {
        pauseButton();
        saveGameButton();
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
