package model;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import menupages.MainMenu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.UUID;

public class ObstacleCreator {
    private Pane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private Pane drawPane;
    private Label text;

    private Line red;
    private Line green;
    private Line blue;
    private Line yellow;


    private Button resetButton;
    private Button goBackButton;

    private int step = 0;

    private final float HEIGHT = 350;
    private final float WIDTH = 300;

    public ObstacleCreator(){
        this.mainPane = new Pane();
        this.mainScene = new Scene(mainPane,WIDTH,HEIGHT);
        this.mainStage = new Stage();
        mainStage.setScene(mainScene);
        this.drawPane = new AnchorPane();
        drawPane.prefHeight(200);
        drawPane.prefWidth(200);
        drawPane.setMinHeight(200);
        drawPane.setMinWidth(200);
        drawPane.setLayoutX(50);
        drawPane.setLayoutY(100);
        drawPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,new BorderWidths(4))));
        mainPane.getChildren().add(drawPane);

        text = new Label();
        text.setText("Click 1st point for red line");
        text.setLayoutX(50);
        text.setLayoutY(50);
        mainPane.getChildren().add(text);
        mainStage.show();

        red = new Line();
        red.setStroke(Color.RED);
        red.setStrokeWidth(5);
        green = new Line();
        green.setStroke(Color.GREEN);
        green.setStrokeWidth(5);
        blue = new Line();
        blue.setStroke(Color.BLUE);
        blue.setStrokeWidth(5);
        yellow = new Line();
        yellow.setStroke(Color.YELLOW);
        yellow.setStrokeWidth(5);

        resetButton = new Button("Reset");
        resetButton.setLayoutX(50);
        resetButton.setLayoutY(305);

        goBackButton = new Button("Go to Main Menu");
        goBackButton.setLayoutX(50);
        goBackButton.setLayoutY(20);
        mainPane.getChildren().add(goBackButton);

        drawPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                switch(step){
                    case 0->{
                        text.setText("Click 2nd point for red line");
                        red.setStartX(mouseEvent.getX());
                        red.setStartY(mouseEvent.getY());
                        step++;
                    }
                    case 1->{
                        text.setText("Click 1st point for green line");
                        red.setEndX(mouseEvent.getX());
                        red.setEndY(mouseEvent.getY());
                        step++;
                        drawPane.getChildren().add(red);
                    }
                    case 2->{
                        text.setText("Click 2nd point for green line");
                        green.setStartX(mouseEvent.getX());
                        green.setStartY(mouseEvent.getY());
                        step++;
                    }
                    case 3->{
                        text.setText("Click 1st point for blue line");
                        green.setEndX(mouseEvent.getX());
                        green.setEndY(mouseEvent.getY());
                        step++;
                        drawPane.getChildren().add(green);
                    }
                    case 4->{
                        text.setText("Click 2nd point for blue line");
                        blue.setStartX(mouseEvent.getX());
                        blue.setStartY(mouseEvent.getY());
                        step++;
                    }
                    case 5->{
                        text.setText("Click 1st point for yellow line");
                        blue.setEndX(mouseEvent.getX());
                        blue.setEndY(mouseEvent.getY());
                        step++;
                        drawPane.getChildren().add(blue);
                    }
                    case 6->{
                        text.setText("Click 2nd point for yellow line");
                        yellow.setStartX(mouseEvent.getX());
                        yellow.setStartY(mouseEvent.getY());
                        step++;
                    }
                    case 7->{
                        text.setText("All done. Click Reset button to reset\n and click in the box to save");
                        yellow.setEndX(mouseEvent.getX());
                        yellow.setEndY(mouseEvent.getY());
                        step++;
                        drawPane.getChildren().add(yellow);
                        mainPane.getChildren().add(resetButton);
                    }
                    default -> {
                        String name = UUID.randomUUID().toString();
                        ObjectOutputStream oos = null;
                        try {
                            oos = new ObjectOutputStream(new FileOutputStream("./src/savedObstacles/"+name));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            oos.writeDouble(red.getStartX());
                            oos.writeDouble(red.getStartY());
                            oos.writeDouble(red.getEndX());
                            oos.writeDouble(red.getEndY());
                            oos.writeDouble(green.getStartX());
                            oos.writeDouble(green.getStartY());
                            oos.writeDouble(green.getEndX());
                            oos.writeDouble(green.getEndY());
                            oos.writeDouble(blue.getStartX());
                            oos.writeDouble(blue.getStartY());
                            oos.writeDouble(blue.getEndX());
                            oos.writeDouble(blue.getEndY());
                            oos.writeDouble(yellow.getStartX());
                            oos.writeDouble(yellow.getStartY());
                            oos.writeDouble(yellow.getEndX());
                            oos.writeDouble(yellow.getEndY());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            oos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        step = 0;
                        drawPane.getChildren().removeAll(red,green,blue,yellow);
                        mainPane.getChildren().remove(resetButton);
                        text.setText("Click 1st point for red line");
                    }
                }
            }
        });



        resetButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                step = 0;
                drawPane.getChildren().removeAll(red,green,blue,yellow);
                mainPane.getChildren().remove(resetButton);
                text.setText("Click 1st point for red line");
            }
        });

        goBackButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainStage.close();
                new MainMenu();
            }
        });

    }

    public Stage getMainStage() {
        return mainStage;
    }
}
