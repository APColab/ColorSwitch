package menupages;

import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainMenuView
{
    private final static int MENU_BUTTON_START_X = 150;
    private final static int MENU_BUTTON_START_Y = 150;
    private final String BUTTON_PATH = "-fx-background-color: transparent; -fx-background-image: url('/menupages/resources/";     //add button name ending with ');
    private ArrayList<GameButton> mainMenuButtons;

    private static final int HEIGHT = 600;
    private static final int WIDTH = 500;
    private AnchorPane menuPane;
    private Scene menuScene;
    private Stage menuStage;

    public MainMenuView()
    {
        mainMenuButtons = new ArrayList<>();
        menuPane = new AnchorPane();
        menuScene = new Scene(menuPane, WIDTH, HEIGHT);
        menuStage = new Stage();
        menuStage.setScene(menuScene);
        createButtons();
        menuStage.show();

        createName();
        createBackground();
    }

    public Stage getMenuStage()
    {
        return menuStage;
    }

    public ArrayList<GameButton> getMainMenuButtons() {
        return mainMenuButtons;
    }

    private void addMainMenuButton(GameButton button, int n)
    {
        button.setLayoutY(MENU_BUTTON_START_Y + n*100);
        button.setLayoutX(MENU_BUTTON_START_X);
        mainMenuButtons.add(button);
        menuPane.getChildren().add(button);
    }

    private void startButton()
    {
        String idlePath = BUTTON_PATH + "green_button1.png');";
        System.out.println(idlePath);
        String pressedPath = BUTTON_PATH + "green_button2.png');";
        GameButton startButton = new GameButton("NEW GAME", idlePath, pressedPath);
        addMainMenuButton(startButton, 1);
    }

    private void loadButton()
    {
        String idlePath = BUTTON_PATH + "red_button1.png');";
        System.out.println(idlePath);
        String pressedPath = BUTTON_PATH + "red_button2.png');";
        GameButton loadButton =  new GameButton("LOAD GAME", idlePath, pressedPath);
        addMainMenuButton(loadButton, 2);
    }

    private void exitButton()
    {
        String idlePath = BUTTON_PATH + "blue_button1.png');";
        System.out.println(idlePath);
        String pressedPath = BUTTON_PATH + "blue_button2.png');";
        GameButton exitButton =  new GameButton("EXIT GAME", idlePath, pressedPath);
        addMainMenuButton(exitButton, 3);
    }

    public void createButtons()
    {
        startButton();
        loadButton();
        exitButton();

    }

    public void createBackground()
    {
        Image bgImage = new Image("/menupages/resources/bg1.jpg", true);
        BackgroundImage bg = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        menuPane.setBackground(new Background(bg));
    }

    public void createName() {
        /**ImageView img = new ImageView("https://fontmeme.com/permalink/201122/0514574a31c5bdb8ea29d1fd19e2c41f.png");
        img.setLayoutX(100);
        img.setLayoutY(50);
        img.setOnMouseEntered(mouseEvent ->
        {
            Glow glow = new Glow();
            glow.setLevel(0.3);
            img.setEffect(glow);
        });

        img.setOnMouseMoved(mouseEvent -> img.setEffect(null));
        menuPane.getChildren().add(img);*/
        Text t = new Text();
        t.setText("Color Switch");
        try
        {
            t.setFont(Font.loadFont(new FileInputStream("src/menupages/resources/eroded.ttf"), 40));
        }
        catch (FileNotFoundException e)
        {
            t.setFont(Font.font("Comic Sans", 30));
        }
        t.setFill(Color.WHITE);
        t.setLayoutX(110);
        t.setLayoutY(100);
        menuPane.getChildren().add(t);
    }


}
