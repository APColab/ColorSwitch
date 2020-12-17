package menupages;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;

public class LoadGameMenuView
{
    private final static String BACKGROUND_IMAGE = "/resources/bg1.jpg";
    private SavedGames savedGames;
    private static final int HEIGHT = 800;
    private static final int WIDTH = 600;

    private AnchorPane loadPane, innerPane;
    private ScrollPane loadSubPane;
    private Stage loadStage;
    private Scene loadScene;

    ArrayList<Game> gamesList;

    public LoadGameMenuView(ArrayList<Game> games)
    {
        gamesList = games;
        loadPane = new AnchorPane();
        innerPane = new AnchorPane();
        loadSubPane = new ScrollPane();
        loadScene = new Scene(loadPane, WIDTH, HEIGHT);
        loadStage = new Stage();
        loadStage.setScene(loadScene);
        loadStage.setResizable(false);
        addBackground();
        createLabel();
        initalizeSubPane();
        loadStage.show();
    }

    public void initalizeSubPane()
    {
        loadSubPane.setPrefSize(500, 400);
        loadSubPane.setLayoutX(50);
        loadSubPane.setLayoutY(250);
        innerPane.setPrefSize(500, (gamesList.size()+2)*100);
        loadSubPane.setFitToWidth(true);
        addGames();
        loadSubPane.setContent(innerPane);
        loadPane.getChildren().add(loadSubPane);
    }

    public void addBackground()
    {
        Image bgImage = new Image("/resources/bg1.jpg", 800, 1000, false,true);
        BackgroundImage bg = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        loadPane.setBackground(new Background(bg));
        innerPane.setBackground(new Background(bg));
    }

    public void addGames()
    {
       /** int count=0;
        Iterator<File> iter = gamesList.listIterator();
        while(iter.hasNext())
        {
            toText(iter.next(), count);
        }*/
    }

    public void toText(String s, int count)
    {
        Text t = new Text();
        t.setText(s);
        try
        {
            t.setFont(Font.loadFont(new FileInputStream("src/menupages/resources/eroded.ttf"), 15));
        }
        catch (FileNotFoundException e)
        {
            t.setFont(Font.font("Comic Sans", 15));
        }
        t.setFill(Color.WHITE);
        t.setLayoutX(50);
        t.setLayoutY(100);
        innerPane.getChildren().add(t);
    }

    public void createLabel()
    {
        Text t = new Text();
        t.setText("Saved Games");
        try
        {
            t.setFont(Font.loadFont(new FileInputStream("src/menupages/resources/eroded.ttf"), 40));
        }
        catch (FileNotFoundException e)
        {
            t.setFont(Font.font("Comic Sans", 30));
        }
        t.setFill(Color.WHITE);
        t.setLayoutX(150);
        t.setLayoutY(100);
        loadPane.getChildren().add(t);
    }

}
