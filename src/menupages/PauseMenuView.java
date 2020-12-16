package menupages;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PauseMenuView extends SubScene
{
    private final static String BACKGROUND_IMAGE = "/resources/bg1.jpg";
    private AnchorPane pausepane;
    private PauseMenu pauseMenu;

    public PauseMenuView(Game game) {
        super(new AnchorPane(), 540, 300);
        {
            prefWidth(600);
            prefHeight(300);

            BackgroundImage bgimg = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 400, false, true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

            pauseMenu = new PauseMenu(game);
            pausepane = (AnchorPane)this.getRoot();
            pausepane.setBackground(new Background((bgimg)));
           // addButtons();
           // addLabel();
           // pausepane.setEffect(dropShadow);

        }
    }

    public void addButtons()
    {
        GameButton b = new GameButton("RESUME GAME", "-fx-background-color: transparent; -fx-background-image: url('/resources/blue_button1.png')", "-fx-background-color: transparent; -fx-background-image: url('/resources/blue_button2.png')", 15);
        b.setLayoutX(50);//80
        b.setLayoutY(180);
        b.setPrefWidth(190); //190
        b.setPrefHeight(45);
        pausepane.getChildren().add(b);

        GameButton b1 = new GameButton("MAIN MENU", "-fx-background-color: transparent; -fx-background-image: url('/resources/red_button1.png')", "-fx-background-color: transparent; -fx-background-image: url('/resources/red_button2.png')", 15);
        b1.setLayoutX(300);
        b1.setLayoutY(180);
        b1.setPrefWidth(190);
        b1.setPrefHeight(45);
        pausepane.getChildren().add(b1);
    }

    public void addLabel()
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
        t.setLayoutX(110);
        t.setLayoutY(100);
        pausepane.getChildren().add(t);
    }

    public void menuTransition()
    {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(0.4));
        translateTransition.setNode(this);
        translateTransition.setToX(-600);
        translateTransition.play();
    }
}
