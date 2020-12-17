package menupages;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameChooseView
{
    private final static int MENU_BUTTON_START_X = 200;
    private final static int MENU_BUTTON_START_Y = 250;
    private final String BUTTON_PATH = "-fx-background-color: transparent; -fx-background-image: url('/resources/";     //add button name ending with ');
    private ArrayList<GameButton> mainMenuButtons;

    private static final int HEIGHT = 800;
    private static final int WIDTH = 600;
    private AnchorPane choosePane;
    private Scene chooseScene;
    private Stage chooseStage;

    public GameChooseView()
    {
        choosePane = new AnchorPane();
        chooseScene = new Scene(choosePane, WIDTH, HEIGHT);
        chooseStage = new Stage();
        chooseStage.setScene(chooseScene);
        chooseStage.setResizable(false);
        chooseStage.centerOnScreen();
        createButtons();
        createName();
        createBackground();
        chooseStage.show();
    }

    public void createButtons()
    {
        String idlePath = BUTTON_PATH + "green_button1.png');";
        System.out.println(idlePath);
        String pressedPath = BUTTON_PATH + "green_button2.png');";
        GameButton jungleButton = new GameButton("JUNGLE MODE", idlePath, pressedPath, 19);
        jungleButton.setLayoutX(200);
        jungleButton.setLayoutY(300);
        choosePane.getChildren().add(jungleButton);
        jungleButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("jungle");
                chooseStage.hide();
                MainMenu.newGame("resources/jungle2.jpg");
            }
        });

        String idlePath1 = BUTTON_PATH + "blue_button1.png');";
        System.out.println(idlePath);
        String pressedPath1 = BUTTON_PATH + "blue_button2.png');";
        GameButton spaceButton = new GameButton("SPACE MODE", idlePath1, pressedPath1, 19);
        spaceButton.setLayoutX(200);
        spaceButton.setLayoutY(400);
        choosePane.getChildren().add(spaceButton);
        spaceButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Space");
                MainMenu.newGame("resources/gamebg.png");
                chooseStage.hide();
            }
        });

        String idlePath2 = BUTTON_PATH + "red_button1.png');";
        System.out.println(idlePath);
        String pressedPath2 = BUTTON_PATH + "red_button2.png');";
        GameButton retroButton = new GameButton("RETRO MODE", idlePath2, pressedPath2, 19);
        retroButton.setLayoutX(200);
        retroButton.setLayoutY(500);
        choosePane.getChildren().add(retroButton);
        retroButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Retro");
                MainMenu.newGame("resources/retrobg.jpg");
                chooseStage.hide();
            }
        });

    }

    public void createBackground()
    {
        Image bgImage = new Image("/resources/bg1.jpg", 800, 1000, false, true);
        BackgroundImage bg = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        choosePane.setBackground(new Background(bg));
    }

    public void createName()
    {
        Text t = new Text();
        t.setText("Select Game Mode");
        try
        {
            t.setFont(Font.loadFont(new FileInputStream("src/menupages/resources/eroded.ttf"), 40));
        }
        catch (FileNotFoundException e)
        {
            t.setFont(Font.font("Comic Sans", 30));
        }
        t.setFill(Color.WHITE);
        t.setLayoutX(100);
        t.setLayoutY(200);
        choosePane.getChildren().add(t);
    }
}
