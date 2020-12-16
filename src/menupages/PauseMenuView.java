package menupages;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PauseMenuView
{
    private final static String BACKGROUND_IMAGE = "/resources/bg1.jpg";
    private ArrayList<GameButton> pauseGameButtons;

    private static final int HEIGHT = 300;
    private static final int WIDTH = 600;
    private final String BUTTON_PATH = "-fx-background-color: transparent; -fx-background-image: url('/resources/";

    private AnchorPane pausePane;
    private PauseMenu pauseMenu;
    private Stage pauseStage;
    private Scene pauseScene;

    public PauseMenuView()
    {
        pauseGameButtons = new ArrayList<>(2);
        pausePane = new AnchorPane();
        pauseScene = new Scene(pausePane, WIDTH, HEIGHT);
        pauseStage = new Stage();
        pauseStage.setScene(pauseScene);
        pauseStage.setResizable(false);
        pauseStage.initStyle(StageStyle.UNDECORATED);
        pauseStage.initModality(Modality.APPLICATION_MODAL);
        pauseStage.centerOnScreen();
        createButtons();
        createLabel();
        addBackground();
        pauseStage.show();
    }

    public Stage getPauseStage() {
        return pauseStage;
    }

    public AnchorPane getPausePane() {
        return pausePane;
    }

    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }

    public void setPauseMenu(PauseMenu pauseMenu) {
        this.pauseMenu = pauseMenu;
    }

    public ArrayList<GameButton> getPauseGameButtons() {
        return pauseGameButtons;
    }


    private void createButtons()
    {
        String idlePath = BUTTON_PATH + "red_button1.png');";
        System.out.println(idlePath);
        String pressedPath = BUTTON_PATH + "red_button2.png');";
        GameButton resumeButton = new GameButton("RESUME GAME", idlePath, pressedPath, 19);
        resumeButton.setLayoutX(80);
        resumeButton.setLayoutY(200);
        pausePane.getChildren().add(resumeButton);
        resumeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Resume");
            }
        });

        String idlePath1 = BUTTON_PATH + "blue_button1.png');";
        System.out.println(idlePath);
        String pressedPath1 = BUTTON_PATH + "blue_button2.png');";
        GameButton exitButton =  new GameButton("MAIN MENU", idlePath1, pressedPath1, 19);
        exitButton.setLayoutX(350);
        exitButton.setLayoutY(200);
        pausePane.getChildren().add(exitButton);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 pauseStage.hide();
                 pauseMenu.getGame().getGameView().getGameStage().hide();
                 pauseMenu.exitToMainMenu();
            }
        });

    }

    public void addBackground()
    {
        Image bgImage = new Image("/resources/bg1.jpg", 600, 400, false, true);
        BackgroundImage bg = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        pausePane.setBackground(new Background(bg));
    }

    public void createLabel()
    {
        Text t = new Text();
        t.setText("Game Paused");
        try
        {
            t.setFont(Font.loadFont(new FileInputStream("src/menupages/resources/eroded.ttf"), 40));
        }
        catch (FileNotFoundException e)
        {
            t.setFont(Font.font("Comic Sans", 30));
        }
        t.setFill(Color.WHITE);
        t.setLayoutX(140);
        t.setLayoutY(110);
        pausePane.getChildren().add(t);
    }
}
