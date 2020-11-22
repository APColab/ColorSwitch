package menupages;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.MouseButton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainMenuView
{
    private final static int MENU_BUTTON_START_X = 150;
    private final static int MENU_BUTTON_START_Y = 150;
    private final String BUTTON_PATH = "-fx-background-color: transparent; -fx-background-image: url('/resources/";     //add button name ending with ');
    private ArrayList<GameButton> mainMenuButtons;

    private static final int HEIGHT = 600;
    private static final int WIDTH = 500;
    private AnchorPane menuPane;
    private Scene menuScene;
    private Stage menuStage;

    private MainMenu mainmenu;

    public MainMenuView(MainMenu menu)
    {
        mainmenu = menu;
        mainMenuButtons = new ArrayList<>();
        menuPane = new AnchorPane();
        menuScene = new Scene(menuPane, WIDTH, HEIGHT);
        menuStage = new Stage();
        menuStage.setScene(menuScene);
        menuStage.setResizable(false);
        createButtons();
        createName();
        addRocket();
        createBackground();
        menuStage.show();
    }

    public Stage getMenuStage()
    {
        return menuStage;
    }

    public MainMenu getMainmenu()
    {
        return mainmenu;
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
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getMainmenu().newGame();
                menuStage.hide();
            }
        });
    }

    private void loadButton()
    {
        String idlePath = BUTTON_PATH + "red_button1.png');";
        System.out.println(idlePath);
        String pressedPath = BUTTON_PATH + "red_button2.png');";
        GameButton loadButton =  new GameButton("LOAD GAME", idlePath, pressedPath);
        addMainMenuButton(loadButton, 2);
        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getMainmenu().loadGame();
                //menuStage.hide();
            }
        });
    }

    private void exitButton()
    {
        String idlePath = BUTTON_PATH + "blue_button1.png');";
        System.out.println(idlePath);
        String pressedPath = BUTTON_PATH + "blue_button2.png');";
        GameButton exitButton =  new GameButton("EXIT GAME", idlePath, pressedPath);
        addMainMenuButton(exitButton, 3);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getMainmenu().exit();
                //menuStage.hide();
            }
        });
    }

    public void createButtons()
    {
        startButton();
        loadButton();
        exitButton();

    }

    //public void rocketTransition(ImageView img, RotateTransition )

    public void addRocket()
    {
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(2000));
        Image image = new Image("/resources/rocket.png");
        ImageView img = new ImageView();
        img.setImage(image);
        img.setFitHeight(80);
        img.setFitWidth(80);
        img.setPreserveRatio(true);
        img.setLayoutY(140);
        img.setLayoutX(230);
        rotateTransition.setNode(img);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setAutoReverse(false);
        rotateTransition.play();
        Group root = new Group(img);
        menuPane.getChildren().add(root);
    }

    public void createName()
    {
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

    public void createBackground()
    {
        Image bgImage = new Image("/resources/bg1.jpg", true);
        BackgroundImage bg = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        menuPane.setBackground(new Background(bg));
    }


}
