package menupages;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.GameState;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OnCollisionMenuView
{
    private final static String BACKGROUND_IMAGE = "/resources/bg1.jpg";
    private static final int HEIGHT = 500;
    private static final int WIDTH = 600;
    private final String BUTTON_PATH = "-fx-background-color: transparent; -fx-background-image: url('/resources/";

    private AnchorPane onCollisionPane;
    private OnCollisionMenu onCollisionMenu;
    private Stage onCollisionStage;
    private Scene onCollisionScene;

    public OnCollisionMenuView(OnCollisionMenu onCollisionMenu)
    {
        onCollisionPane = new AnchorPane();
        onCollisionScene = new Scene(onCollisionPane, WIDTH, HEIGHT);
        onCollisionStage = new Stage();
        onCollisionStage.setScene(onCollisionScene);
        onCollisionStage.setResizable(false);
        onCollisionStage.initStyle(StageStyle.UNDECORATED);
        onCollisionStage.initModality(Modality.APPLICATION_MODAL);
        onCollisionStage.centerOnScreen();
        this.onCollisionMenu = onCollisionMenu;
        createButtons();
        createLabels();
        addBackground();
        addCollectedStars();
        addStar();
        onCollisionStage.show();
    }

    public void createButtons()
    {
        String idlePath = BUTTON_PATH + "green_button1.png');";
        System.out.println(idlePath);
        String pressedPath = BUTTON_PATH + "green_button2.png');";
        GameButton reviveButton = new GameButton("REVIVE", idlePath, pressedPath, 19);
        reviveButton.setLayoutX(200);
        reviveButton.setLayoutY(250);
        onCollisionPane.getChildren().add(reviveButton);
        reviveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Resume");
                if(onCollisionMenu.getGame().getGAME_STATE()== GameState.GAME_OVER)
                {
                    onCollisionMenu.reviveGame();
                }
            }
        });

        String idlePath2 = BUTTON_PATH + "red_button1.png');";
        System.out.println(idlePath);
        String pressedPath2 = BUTTON_PATH + "red_button2.png');";
        GameButton restartButton = new GameButton("RESTART", idlePath2, pressedPath2, 19);
        restartButton.setLayoutX(200);
        restartButton.setLayoutY(330);
        onCollisionPane.getChildren().add(restartButton);
        restartButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Restart");
                if(onCollisionMenu.getGame().getGAME_STATE()== GameState.GAME_OVER)
                {
                    onCollisionStage.hide();
                    onCollisionMenu.restartGame();
                }
            }
        });

        String idlePath1 = BUTTON_PATH + "blue_button1.png');";
        System.out.println(idlePath);
        String pressedPath1 = BUTTON_PATH + "blue_button2.png');";
        GameButton exitButton =  new GameButton("MAIN MENU", idlePath1, pressedPath1, 19);
        exitButton.setLayoutX(200);
        exitButton.setLayoutY(410);
        onCollisionPane.getChildren().add(exitButton);
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(onCollisionMenu.getGame().getGAME_STATE()== GameState.GAME_OVER) {
                    onCollisionStage.hide();
                    onCollisionMenu.getGame().getGameView().getGameStage().hide();
                    onCollisionMenu.exitToMainMenu();
                }
            }
        });

    }

    public void addStar()
    {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1500));
        Image image = new Image("/resources/star.png");
        ImageView img = new ImageView();
        img.setImage(image);
        img.setFitHeight(40);
        img.setFitWidth(40);
        img.setPreserveRatio(true);
        img.setLayoutY(130);
        img.setLayoutX(275);
        translateTransition.setNode(img);
        translateTransition.setByY(10);
        translateTransition.setCycleCount(Animation.INDEFINITE);
        translateTransition.setInterpolator(Interpolator.EASE_OUT);
        translateTransition.setAutoReverse(true);
        translateTransition.play();
        Group root = new Group(img);
        onCollisionPane.getChildren().add(root);
    }

    public void addCollectedStars()
    {
        long totalStars = onCollisionMenu.getGame().getCollectedStars().getTotalStars()+ onCollisionMenu.getGame().getScore();
        String str = Long.toString(totalStars);
        Text t1 = new Text();
        t1.setText("Total Stars: "+str);
        try
        {
            t1.setFont(Font.loadFont(new FileInputStream("src/menupages/resources/eroded.ttf"), 17));
        }
        catch (FileNotFoundException e)
        {
            t1.setFont(Font.font("Comic Sans", 17));
        }
        t1.setFill(Color.WHITE);
        HBox hbox = new HBox();
        hbox.getChildren().add(t1);
        hbox.setLayoutX(20);
        hbox.setLayoutY(25);
        hbox.setStyle("-fx-border-color: #ffffff;");
        onCollisionPane.getChildren().add(hbox);
    }

    public void createLabels()
    {
        Text t = new Text();
        t.setText("Game Over");
        try
        {
            t.setFont(Font.loadFont(new FileInputStream("src/menupages/resources/eroded.ttf"), 40));
        }
        catch (FileNotFoundException e)
        {
            t.setFont(Font.font("Comic Sans", 30));
        }
        t.setFill(Color.WHITE);
        t.setLayoutX(185);
        t.setLayoutY(100);
        onCollisionPane.getChildren().add(t);

        int nor = onCollisionMenu.getGame().getNumberOfRevivals();
        int requiredForRevival = (int) Math.pow(2,onCollisionMenu.getGame().getNumberOfRevivals());
        String str = Integer.toString(requiredForRevival);

        Text t1 = new Text();
        t1.setText("Revival: "+str+" Stars");
        try
        {
            t1.setFont(Font.loadFont(new FileInputStream("src/menupages/resources/eroded.ttf"), 17));
        }
        catch (FileNotFoundException e)
        {
            t1.setFont(Font.font("Comic Sans", 17));
        }
        t1.setFill(Color.WHITE);
        t1.setLayoutX(225);
        t1.setLayoutY(210);
        onCollisionPane.getChildren().add(t1);
    }

    public void addBackground()
    {
        Image bgImage = new Image("/resources/bg1.jpg", 800, 700, false, true);
        BackgroundImage bg = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        onCollisionPane.setBackground(new Background(bg));
    }

    public AnchorPane getOnCollisionPane() {
        return onCollisionPane;
    }

    public Scene getOnCollisionScene() {
        return onCollisionScene;
    }

    public Stage getOnCollisionStage() {
        return onCollisionStage;
    }

    public String getBUTTON_PATH() {
        return BUTTON_PATH;
    }

    public void setOnCollisionMenu(OnCollisionMenu onCollisionMenu) {
        this.onCollisionMenu = onCollisionMenu;
        onCollisionMenu.getGame().getGameView().getGamePane().requestFocus();
    }
}
