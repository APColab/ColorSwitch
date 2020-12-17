package menupages;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
    private final String BUTTON_PATH = "-fx-background-color: transparent; -fx-background-image: url('/resources/";
    private SavedGames savedGames;
    private static final int HEIGHT = 800;
    private static final int WIDTH = 600;

    private AnchorPane loadPane, innerPane;
    private ScrollPane loadSubPane;
    private Stage loadStage;
    private Scene loadScene;
    ToggleGroup tg = new ToggleGroup();

    private String SelectedButton;

    ArrayList<String> gamesList;
    ArrayList<Game> games;


    public String getSelectedButton() {
        return SelectedButton;
    }

    public LoadGameMenuView(ArrayList<Game> games)
    {
        this.games = games;
        gamesList = new ArrayList<>();
        for(Game g:games){
            gamesList.add(g.getGameID().toString());
        }
        System.out.println(gamesList.size());
        loadPane = new AnchorPane();
        innerPane = new AnchorPane();
        loadSubPane = new ScrollPane();
        loadScene = new Scene(loadPane, WIDTH, HEIGHT);
        loadStage = new Stage();
        loadStage.setScene(loadScene);
        loadStage.setResizable(false);
        addBackground();
        createLabel();
        createButtons();
        initalizeSubPane();
        loadStage.show();
    }

    public void initalizeSubPane()
    {
        loadSubPane.setPrefSize(500, 400);
        loadSubPane.setLayoutX(50);
        loadSubPane.setLayoutY(200);
        if(gamesList!=null)
            innerPane.setPrefSize(500, (gamesList.size())*50+500);
        else
            innerPane.setPrefSize(500, 200);
        loadSubPane.setFitToWidth(true);
        if(gamesList!=null)
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
       int count=0;
        Iterator<String> iter = gamesList.listIterator();
        while(iter.hasNext())
        {
            toRadioButton(iter.next(), count);
            count++;
        }

    }

    public void toRadioButton(String s, int count)  {
        RadioButton r = new RadioButton(s);
        r.setToggleGroup(tg);
        try
        {
            r.setFont(Font.loadFont(new FileInputStream("src/menupages/resources/eroded.ttf"), 20));
            r.setTextFill(Color.WHITE);
        }
        catch (FileNotFoundException e)
        {
            r.setFont(Font.font("Comic Sans", 20));
            r.setTextFill(Color.WHITE);
        }
        //t.setFill(Color.WHITE);
        r.setLayoutX(50);
        r.setLayoutY(30+50*count);
        innerPane.getChildren().add(r);

        r.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                RadioButton rb = (RadioButton)tg.getSelectedToggle();
                if (rb != null && rb.isSelected()) {
                    SelectedButton = rb.getText();
                    System.out.println(SelectedButton);
                }

            }
        }
        );
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

    private void createButtons() {
        String idlePath = BUTTON_PATH + "yellow_button1.png');";
        System.out.println(idlePath);
        String pressedPath = BUTTON_PATH + "yellow_button2.png');";
        GameButton resumeButton = new GameButton("PLAY GAME", idlePath, pressedPath, 19);
        resumeButton.setLayoutX(200);
        resumeButton.setLayoutY(650);
        loadPane.getChildren().add(resumeButton);
        resumeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(getSelectedButton() != null)
                {
                    for(Game g:games){
                        if(g.getGameID().toString().equals(getSelectedButton())){
                            loadStage.hide();
                            g.getGameView().getGameStage().show();
                            break;
                        }
                    }
                }
                System.out.println("SELCETD "+SelectedButton);
                //loadStage.hide();
            }
        });



        GameButton goBackButton = new GameButton("MAIN MENU",idlePath,pressedPath,19);
        goBackButton.setLayoutX(200);
        goBackButton.setLayoutY(700);
        loadPane.getChildren().add(goBackButton);

        goBackButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                loadStage.close();
                MainMenu mainMenu = new MainMenu();
            }
        });



    }


}
